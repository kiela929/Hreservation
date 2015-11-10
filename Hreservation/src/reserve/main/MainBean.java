package reserve.main;

import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import reserve.join.MemberDTO;
import reserve.reserve.ReserveDTO;
import reserve.reserve.doctorListDTO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
@Controller
public class MainBean {
	public static Reader reader;
	public static SqlMapClient sqlMapper;

	
	private int totalCount; 		// �� �Խù��� ��
	private int blockCount = 10;	// �� ��������  �Խù��� ��
	private int blockPage = 5; 	// �� ȭ�鿡 ������ ������ ��
	private String pagingHtml; 	//����¡�� ������ HTML
	private pagingAction page; 	// ����¡ Ŭ����
	
	
	
	public MainBean() throws Exception{
		reader=Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper=SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	
	@RequestMapping("main.do")
	public ModelAndView main(){
		List<ReserveDTO> list= new ArrayList<ReserveDTO>();
		
		String event="";
		try {
			list=(List<ReserveDTO>)sqlMapper.queryForList("all_reserve");
			
			for(ReserveDTO dto:list){
				
				if(dto.getStr_time().equals("9")){
					dto.setStr_time("09");
				}
				event+=	" { title: '<"+dto.getReservenum()+">' , start: '"+returnDateString(dto.getReserve_date())+"T"+
			dto.getStr_time()+":00:00' } ,";
			}
			
			event=event.substring(1, event.length()-1 );
			event="[ "+event+" ]";
//			event+="{ title: '"+list.get(0).getReservenum()+"��', start: '"+
//					"2015-05-12T14:30:00' }, { title: '"+list.get(1).getReservenum()+"��', start: '"+
//					"2015-05-12T14:30:00' }";
//			event="[ "+event+" ]";
		} catch (Exception e) {
			System.out.println("������������");
		}
//		event+="]";
		
		ModelAndView mv= new ModelAndView();
		mv.addObject("eventString", event);
		mv.setViewName("/main/main.jsp");
		return mv;
	}
	
	
		//�ǻ� �������� 
		@RequestMapping("/scheduleList.do")
		public ModelAndView scheduleList(HttpServletRequest request, HttpSession session)throws Exception{
			

			List<doctorListDTO> mylist_list2=new ArrayList<doctorListDTO>();
			
			List<ReserveDTO> reserve_list_doctor=null;
			ModelAndView mv=new ModelAndView();
			String id= (String)session.getAttribute("id");
			reserve_list_doctor=sqlMapper.queryForList("reserve_list_doctor",id);
			
			for(ReserveDTO r: reserve_list_doctor){
				doctorListDTO mylist2=new doctorListDTO();
				
				r.setReg_date_string(returnDateString(r.getReg_date()));
				r.setReserve_date_string(returnDateString(r.getReserve_date()));
				
				/*
				 * 1)�������� ID�� ȸ����ȸ
				 * 
				 * 
				 * */
//				DoctorDTO doc=new DoctorDTO();
//				doc=(DoctorDTO)sqlMapper.queryForObject("findDoctorAndPart",r.getDoctornum());
				
				MemberDTO mem=new MemberDTO();
				mem=(MemberDTO)sqlMapper.queryForObject("findMember",r.getId());//�ǻ��� ����
				
				
				mylist2.setReservenum(r.getReservenum());
				mylist2.setId(r.getId());
				mylist2.setReserve_date_string(r.getReserve_date_string());
				mylist2.setStr_time(r.getStr_time());
				mylist2.setReg_date_string(r.getReg_date_string());
				mylist2.setDoctornum(r.getDoctornum());
				
				mylist2.setName(mem.getName());
				mylist2.setAge(mem.getAge());
				mylist2.setJumin1(mem.getJumin1());
				mylist2.setJumin2(mem.getJumin2());
				
				mylist2.setPhone1(mem.getPhone1());
				mylist2.setPhone2(mem.getPhone2());
				mylist2.setPhone3(mem.getPhone3());
				
				mylist_list2.add(mylist2);
				
					}
			
			
			String cp = request.getParameter("currentPage");
			int currentPage;
			if(cp==null){
				currentPage = 1;
			}else{
				currentPage=Integer.parseInt(cp);
			}
			totalCount = reserve_list_doctor.size(); // ��ü �� ������ ���Ѵ�.
			page = new pagingAction(currentPage, totalCount, blockCount, blockPage); // pagingAction ��ü ����.
			pagingHtml = page.getPagingHtml().toString(); // ������ HTML ����.

			// ���� ���������� ������ ������ ���� ��ȣ ����.
			int lastCount = totalCount;

			// ���� �������� ������ ���� ��ȣ�� ��ü�� ������ �� ��ȣ���� ������ lastCount�� +1 ��ȣ�� ����.
			if (page.getEndCount() < totalCount)
				lastCount = page.getEndCount() + 1;
			// ��ü ����Ʈ���� ���� ��������ŭ�� ����Ʈ�� �����´�.
			reserve_list_doctor = reserve_list_doctor.subList(page.getStartCount(), lastCount);
			int number = totalCount;
			
			mv.addObject("currentPage",currentPage);
			mv.addObject("totalCount",totalCount);
			mv.addObject("blockCount",blockCount);
			mv.addObject("blockPage",blockPage);
			mv.addObject("number",number);
			mv.addObject("pagingHtml",pagingHtml);
			mv.addObject("page",page);
			mv.addObject("mylist_list2", mylist_list2);
			mv.addObject("BODY_PATH","/reserve/scheduleList.jsp");
			mv.setViewName("/main/main.jsp");
			return mv;
			
			
//			List<ReserveDTO> list= new ArrayList<ReserveDTO>();
//			
//			String event="";
//			try {
//				list=(List<ReserveDTO>)sqlMapper.queryForList("all_reserve");
//				
//				for(ReserveDTO dto:list){
//					
//					if(dto.getStr_time().equals("9")){
//						dto.setStr_time("09");
//					}
//					event+=	" { title: '<"+dto.getReservenum()+">' , start: '"+returnDateString(dto.getReserve_date())+"T"+
//				dto.getStr_time()+":00:00' } ,";
//				}
//				
//				event=event.substring(1, event.length()-1 );
//				event="[ "+event+" ]";
////				event+="{ title: '"+list.get(0).getReservenum()+"��', start: '"+
////						"2015-05-12T14:30:00' }, { title: '"+list.get(1).getReservenum()+"��', start: '"+
////						"2015-05-12T14:30:00' }";
////				event="[ "+event+" ]";
//			} catch (Exception e) {
//				System.out.println("������������");
//			}
////			event+="]";
//			
//			ModelAndView mv= new ModelAndView();
////			mv.addObject("eventString", event);
//			mv.setViewName("/main/main.jsp");
//			return mv;
		}
		
		
		
		
	//��¥ �����ϴ� �޼ҵ� 
	public static Date getDate(Date thisDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDate);
		cal.set(cal.get(cal.YEAR),cal.get(cal.MONTH),cal.get(cal.DATE), 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		System.out.println("��¥��"+cal.getTime());
		return cal.getTime();
	}
	
	public Date afterOneDay(Date d){
		//d ���س�¥�� �Է��Ѵ�.
		long dd=d.getTime();
		//�и�������*60��*60��*24�ð�==�Ϸ�
		return new Date(dd+1000*60*60*24);
	}
	
	public String returnDateString(Date date){
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        
//	        Date date1 = new Date();
	        String today = df.format(date);
	        System.out.println(today);
	        
//	        Calendar c = Calendar.getInstance();
//	        String today2 = df.format(c.getTime());
//	        System.out.println(today2);
	        return today;
		
	}
	

}
