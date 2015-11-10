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
	private static List<PartDTO> reserve_part=null;//����� ���� ����Ʈ 
	
	private int totalCount; 		// �� �Խù��� ��
	private int blockCount = 10;	// �� ��������  �Խù��� ��
	private int blockPage = 5; 	// �� ȭ�鿡 ������ ������ ��
	private String pagingHtml; 	//����¡�� ������ HTML
	private pagingAction page; 	// ����¡ Ŭ����
	
	
	public ReserveBean()throws Exception{
		reader=Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper=SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	

	@RequestMapping("/reserve.do")
	public ModelAndView reserve(HttpSession session){
		
		MemberDTO member=null;
		String id= (String) session.getAttribute("id");
		System.out.println("���̵𼼼� :"+ id);
		try{
		member= (MemberDTO)sqlMapper.queryForObject("findMember",id);//���� �α��ε� ȸ�����̵�� ���� ã�ƿ���
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
	public ModelAndView reservePro(ReserveDTO dto){//,HttpSession session){ //���ǵ� ���� �������� �׳� ������ �� �� ����. 
		int check=0;
		ModelAndView mv= new ModelAndView();
		/*
		 * �̹� ������ �Ǿ������� ���� �˷������. 
		 * */
		try {
			check=(int)sqlMapper.queryForObject("canReserve", dto);
			System.out.println("check"+check);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(check==0){
			
		//���డ��
		
		PartDTO part=null;
		 DoctorDTO doctor=null;
		 MemberDTO member=null;
		 try {
			 System.out.println("�ǻ�:"+dto.getDoctornum());
			 System.out.println("������:"+dto.getReserve_date());
			 System.out.println("����ð�:"+dto.getStr_time());
			 System.out.println("���̵�:"+dto.getId());
			 
			 
			 sqlMapper.insert("addReserve",dto);//insert
			 System.out.println("����");
			 
			
			 
		} catch (SQLException e) {System.out.println("�������");}
		 
		 try {
			 member=(MemberDTO)sqlMapper.queryForObject("findMember",dto.getId());
		} catch (Exception e) {
			System.out.println("ȸ��ã�� ����");
		}
		 try {

			 doctor=(DoctorDTO)sqlMapper.queryForObject("findDoctor",dto.getDoctornum());
		} catch (Exception e) {
			System.out.println("�ǻ�ã�� ����");
		}
		
		 try {

			 part=(PartDTO)sqlMapper.queryForObject("findPart",doctor.getPartnum());
		} catch (Exception e) {
			System.out.println("�����ã�� ����");
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
	
	
	
	//�����Ϻ��� 
	
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
			 * �⺻ ���������� ������ doctor(�ǻ�id�� member ��ȸ) -> �ǻ��̸�, �ǻ���ȭ��ȣ
			 * part 
			 * 
			 * 1) doctornum ���� doctor ���̺� �������� 
			 * 2) �ǻ� id�� member �������� 
			 * 3) partnum(doctor���̺���)���� part ���̺������� -> ������̸� 
			 * */
			DoctorDTO doc=new DoctorDTO();
			doc=(DoctorDTO)sqlMapper.queryForObject("findDoctorAndPart",r.getDoctornum());
			
			MemberDTO docmem=new MemberDTO();
			docmem=(MemberDTO)sqlMapper.queryForObject("findMember",doc.getId());//�ǻ��� ����
			
			
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
		totalCount = reserve_list.size(); // ��ü �� ������ ���Ѵ�.
		page = new pagingAction(currentPage, totalCount, blockCount, blockPage); // pagingAction ��ü ����.
		pagingHtml = page.getPagingHtml().toString(); // ������ HTML ����.

		// ���� ���������� ������ ������ ���� ��ȣ ����.
		int lastCount = totalCount;

		// ���� �������� ������ ���� ��ȣ�� ��ü�� ������ �� ��ȣ���� ������ lastCount�� +1 ��ȣ�� ����.
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;
		// ��ü ����Ʈ���� ���� ��������ŭ�� ����Ʈ�� �����´�.
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
	
	
	//reserve ������ ����Ʈ�ڽ��� ���ð��� ���� �� ������ 
	@RequestMapping("reserveSelect.do")
	public ModelAndView reserveSelect(HttpServletRequest request){
		List<DoctorDTO> doctor_list=new ArrayList<DoctorDTO>();
		String partString= request.getParameter("region");//1��° ���ð��� ������->����� �ڵ�
		int partnum= Integer.parseInt(partString);
		System.out.println("����� �ڵ�(String):"+partString);
		System.out.println("����� �ڵ�(int):"+partnum);
		try{
			doctor_list=(List<DoctorDTO>)sqlMapper.queryForList("doctor_list",partnum);
			//������� �����ִ� �ǻ� ���� ��� �������� 
			System.out.println("�Ѱ���: "+doctor_list.size());
			for(int i=0;i<doctor_list.size();i++){
				System.out.println(doctor_list.get(i).getName());
			}
		}catch(Exception e){
			System.out.println("reserveSelect.do ����");		}
		
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
