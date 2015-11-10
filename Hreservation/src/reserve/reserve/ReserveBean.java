package reserve.reserve;

import java.io.Reader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import reserve.join.DoctorDTO;
import reserve.join.MemberDTO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

@Controller
public class ReserveBean {
	
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	private static List<ReserveDTO> reserve_list=null;
	private static List<PartDTO> reserve_part=null;//진료과 정보 리스트 
	
	private int totalCount; 		// 총 게시물의 수
	private int blockCount = 10;	// 한 페이지의  게시물의 수
	private int blockPage = 5; 	// 한 화면에 보여줄 페이지 수
	private String pagingHtml; 	//페이징을 구현한 HTML
	private pagingAction page; 	// 페이징 클래스
	
	
	public ReserveBean()throws Exception{
		reader=Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper=SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	

	@RequestMapping("/reserve.do")
	public ModelAndView reserve(HttpSession session){
		
		MemberDTO member=null;
		String id= (String) session.getAttribute("id");
		System.out.println("아이디세션 :"+ id);
		try{
		member= (MemberDTO)sqlMapper.queryForObject("findMember",id);//현재 로그인된 회원아이디로 정보 찾아오기
		reserve_part=(List<PartDTO>)sqlMapper.queryForList("findPartInfo");
		
		}catch(Exception e){}
		
		ModelAndView mv= new ModelAndView();
		
		
		mv.addObject("dto",member);
		mv.addObject("partDTO", reserve_part);
		mv.addObject("BODY_PATH", "/reserve/reservForm.jsp");
		mv.setViewName("/main/main.jsp");
		return mv;
	}

	
	

	@RequestMapping("/reservePro.do")
	public ModelAndView reservePro(ReserveDTO dto){//,HttpSession session){ //세션도 따로 지정없이 그냥 가지고 올 수 있음. 
		int check=0;
		ModelAndView mv= new ModelAndView();
		/*
		 * 이미 예약이 되어있으면 실패 알려줘야함. 
		 * */
		try {
			check=(int)sqlMapper.queryForObject("canReserve", dto);
			System.out.println("check"+check);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(check==0){
			
		//예약가능
		
		PartDTO part=null;
		 DoctorDTO doctor=null;
		 MemberDTO member=null;
		 try {
			 System.out.println("의사:"+dto.getDoctornum());
			 System.out.println("예약일:"+dto.getReserve_date());
			 System.out.println("예약시간:"+dto.getStr_time());
			 System.out.println("아이디:"+dto.getId());
			 
			 
			 sqlMapper.insert("addReserve",dto);//insert
			 System.out.println("성공");
			 
			
			 
		} catch (SQLException e) {System.out.println("예약실패");}
		 
		 try {
			 member=(MemberDTO)sqlMapper.queryForObject("findMember",dto.getId());
		} catch (Exception e) {
			System.out.println("회원찾기 실패");
		}
		 try {

			 doctor=(DoctorDTO)sqlMapper.queryForObject("findDoctor",dto.getDoctornum());
		} catch (Exception e) {
			System.out.println("의사찾기 실패");
		}
		
		 try {

			 part=(PartDTO)sqlMapper.queryForObject("findPart",doctor.getPartnum());
		} catch (Exception e) {
			System.out.println("진료과찾기 실패");
		}
		 
		 dto.setReserve_date_string(returnDateString(dto.getReserve_date()));
			mv.addObject("ReserveDTO",dto);
			mv.addObject("memberDTO",member);
			mv.addObject("doctorDTO",doctor);
			mv.addObject("partDTO",part);
			mv.addObject("BODY_PATH", "/reserve/reserveView.jsp");
			mv.setViewName("/main/main.jsp");
			
		
		}
		else{
			mv.addObject("BODY_PATH", "/reserve/reserveFail.jsp");
			mv.setViewName("/main/main.jsp");
		}
		

		return mv;
	}
	
	
	
	//예약목록보기 
	
	@RequestMapping("/myReserveList.do")
	public ModelAndView myReserveList(HttpServletRequest request, HttpSession session)throws Exception{
		
		List<myListDTO> mylist_list=new ArrayList<myListDTO>();
		
		ModelAndView mv=new ModelAndView();
		String id= (String)session.getAttribute("id");
		reserve_list=(List<ReserveDTO>)sqlMapper.queryForList("reserve_list",id);
		
		for(ReserveDTO r : reserve_list){
			myListDTO mylist=new myListDTO();
			
			r.setReg_date_string(returnDateString(r.getReg_date()));
			r.setReserve_date_string(returnDateString(r.getReserve_date()));
			
			/*
			 * 기본 예약정보를 가지고 doctor(의사id로 member 조회) -> 의사이름, 의사전화번호
			 * part 
			 * 
			 * 1) doctornum 으로 doctor 테이블 가져오기 
			 * 2) 의사 id로 member 가져오기 
			 * 3) partnum(doctor테이블의)으로 part 테이블가져오기 -> 진료과이름 
			 * */
			DoctorDTO doc=new DoctorDTO();
			doc=(DoctorDTO)sqlMapper.queryForObject("findDoctorAndPart",r.getDoctornum());
			
			MemberDTO docmem=new MemberDTO();
			docmem=(MemberDTO)sqlMapper.queryForObject("findMember",doc.getId());//의사의 정보
			
			
			mylist.setReservenum(r.getReservenum());
			mylist.setId(r.getId());
			mylist.setReserve_date_string(r.getReserve_date_string());
			mylist.setStr_time(r.getStr_time());
			mylist.setReg_date_string(r.getReg_date_string());
			mylist.setDoctornum(r.getDoctornum());
			mylist.setDoc_name(docmem.getName());
			mylist.setPhone1(docmem.getPhone1());
			mylist.setPhone2(docmem.getPhone2());
			mylist.setPhone3(docmem.getPhone3());
			mylist.setPart_name(doc.getPart_name());
			
			mylist_list.add(mylist);
			
			
		}
		
		
		String cp = request.getParameter("currentPage");
		int currentPage;
		if(cp==null){
			currentPage = 1;
		}else{
			currentPage=Integer.parseInt(cp);
		}
		totalCount = reserve_list.size(); // 전체 글 갯수를 구한다.
		page = new pagingAction(currentPage, totalCount, blockCount, blockPage); // pagingAction 객체 생성.
		pagingHtml = page.getPagingHtml().toString(); // 페이지 HTML 생성.

		// 현재 페이지에서 보여줄 마지막 글의 번호 설정.
		int lastCount = totalCount;

		// 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;
		// 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
		reserve_list = reserve_list.subList(page.getStartCount(), lastCount);
		int number = totalCount;
		
		mv.addObject("currentPage",currentPage);
		mv.addObject("totalCount",totalCount);
		mv.addObject("blockCount",blockCount);
		mv.addObject("blockPage",blockPage);
		mv.addObject("number",number);
		mv.addObject("pagingHtml",pagingHtml);
		mv.addObject("page",page);
		mv.addObject("mylist_list", mylist_list);
		mv.addObject("BODY_PATH","/reserve/myReserveList.jsp");
		mv.setViewName("/main/main.jsp");
		return mv;
	}
	
	@RequestMapping("reserveDel.do")
	public ModelAndView reserveDel(int reservenum)throws Exception{
		sqlMapper.delete("reserveDel",reservenum);

		ModelAndView mv= new ModelAndView();
		mv.addObject("BODY_PATH", "/reserve/deleteReserve.jsp");
		mv.setViewName("/myReserveList.do");
		return mv;
	}
	
	
	//reserve 폼에서 셀렉트박스의 선택값에 따른 값 보내기 
	@RequestMapping("reserveSelect.do")
	public ModelAndView reserveSelect(HttpServletRequest request){
		List<DoctorDTO> doctor_list=new ArrayList<DoctorDTO>();
		String partString= request.getParameter("region");//1번째 선택값을 가져옴->진료과 코드
		int partnum= Integer.parseInt(partString);
		System.out.println("진료과 코드(String):"+partString);
		System.out.println("진료과 코드(int):"+partnum);
		try{
			doctor_list=(List<DoctorDTO>)sqlMapper.queryForList("doctor_list",partnum);
			//진료과에 속해있는 의사 정보 모두 가져오기 
			System.out.println("총갯수: "+doctor_list.size());
			for(int i=0;i<doctor_list.size();i++){
				System.out.println(doctor_list.get(i).getName());
			}
		}catch(Exception e){
			System.out.println("reserveSelect.do 실패");		}
		
		ModelAndView mv= new ModelAndView();
		mv.addObject("doctorInPart",doctor_list);
		mv.setViewName("/reserve/reserveSelect.jsp");
		return mv;
	}
	
	public String returnDateString(Date date){
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        String today = df.format(date);
	        System.out.println(today);
	        return today;
		
	}
	
	
}
