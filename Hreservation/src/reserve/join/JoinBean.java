package reserve.join;

import java.io.Reader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import reserve.reserve.ReserveDTO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

@Controller
public class JoinBean {
	
	public static Reader reader;
	public static SqlMapClient sqlMapper;

	public JoinBean()throws Exception{
		reader=Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper=SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	

	@RequestMapping("/join.do")
	public ModelAndView join(){
		
		ModelAndView mv= new ModelAndView();
		mv.addObject("BODY_PATH","/join/joinForm.jsp");
		mv.setViewName("/main/main.jsp");
		return mv;
	}
	
	@RequestMapping("/doctorJoin.do")
	public ModelAndView doctorJoin(){
		ModelAndView mv= new ModelAndView();
		mv.addObject("BODY_PATH","/join/doctorJoinForm.jsp");
		mv.setViewName("/main/main.jsp");
		return mv;
	}
	
	@RequestMapping("/joinPro.do")
	public ModelAndView joinPro(MemberDTO dto){//,HttpSession session){ //세션도 따로 지정없이 그냥 가지고 올 수 있음. 
//		int check=0;
		 try {
			 dto.setGrade(3);
			 sqlMapper.insert("joinMember",dto);//insert
		/*check= (Integer)sqlMapper.queryForObject("userCheck",dto);
		if(check==1){
			session.setAttribute("memId", dto.getId());
		}*/
		} catch (SQLException e) {}
		 
		 
		ModelAndView mv= new ModelAndView();
		mv.addObject("dto",dto);
		
		
		mv.addObject("BODY_PATH","/join/joinPro.jsp");
		mv.setViewName("/main/main.jsp");
		
		return mv;
	}
	
	@RequestMapping("/doctorJoinPro.do")
	public ModelAndView doctorJoinPro(DoctorDTO dto){//,HttpSession session){ //세션도 따로 지정없이 그냥 가지고 올 수 있음. 
//		int check=0;
		 try {
			 dto.setGrade(2);
			 
			 System.out.println("의사등급: "+dto.getGrade());
			 sqlMapper.insert("joinMember_doctor",dto);
//			 int last_num= (int)sqlMapper.queryForObject("checkLastMembernum",seq_name);
			 
//			 dto.setMembernum(last_num);
			 sqlMapper.insert("joinDoctor",dto);//insert
		/*check= (Integer)sqlMapper.queryForObject("userCheck",dto);
		if(check==1){
			session.setAttribute("memId", dto.getId());
		}*/
		} catch (SQLException e) {}
		 
		 
		ModelAndView mv= new ModelAndView();
		mv.addObject("dto",dto);
		mv.addObject("BODY_PATH","/join/doctorJoinPro.jsp");
		mv.setViewName("/main/main.jsp");
		
		return mv;
	}
	
	//로그인 뷰 
	@RequestMapping("/login.do")
	public ModelAndView login(){
		
		ModelAndView mv= new ModelAndView();
		mv.addObject("BODY_PATH","/join/login.jsp");
		mv.setViewName("/main/main.jsp");
		return mv;
	}
	
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session){
		
		session.removeAttribute("id");
		session.removeAttribute("grade");
		System.out.println(session.getAttribute("id"));
		System.out.println(session.getAttribute("grade"));
		ModelAndView mv= new ModelAndView();
		mv.setViewName("/main.do");
		return mv;
	}

	//로그인 처리
	@RequestMapping("/loginPro.do")
	public ModelAndView loginPro(MemberDTO dto, HttpSession session){ //세션도 따로 지정없이 그냥 가지고 올 수 있음. 
		/*1. 아이디,pw 받아오기 
		 * 2. 회원테이블에서 아이디가 있는지 확인
		 * 3. 아이디로 조회해서 비밀번호 가져오기
		 * 4. 비밀번호가 일치하는지 확인 
		 * */
		
		System.out.println("loginPro 들어옴");
		int check=0;
		int grade=0;
		boolean flag=false;
		String real_pw="";
		List<MailDTO> mailList = new ArrayList<MailDTO>();
		String id = dto.getId();
		String pw= dto.getPw();
		ModelAndView mv= new ModelAndView();
		try{
		real_pw= (String)sqlMapper.queryForObject("havaMember",id);
		grade=(int)sqlMapper.queryForObject("findGrade",id);
		System.out.println("grade: "+grade);
		
		if(grade==1){
			//관리자로 로그인
			//오늘 날짜로 DB 찾음
			//없으면 메일보내기
			//있으면 보내지않기
			Date today=new Date();
			try {	
			ReserveDTO re= new ReserveDTO();
			re.setReserve_date(getDate(today));
			
			
				int check_today=(int)sqlMapper.queryForObject("today",re);
			if(check_today==0){
				System.out.println(getDate(today));
		
				System.out.println("찾을날짜:"+re.getReserve_date());
				try{
				mailList=(List<MailDTO>)sqlMapper.queryForList("today_reserve",re);
				//오늘날짜인 예약 중에서 정보가져오기
			
				
				}catch(Exception e){
					System.out.println("m: "+mailList.size());
					System.out.println("현재예약정보가 없음");}
				
				if(mailList.size()!=0){
			
				for(MailDTO m : mailList){
					m.setReserve_date_string(returnDateString(m.getReserve_date()));
					reserveCheck(m);//메일보내기
					System.out.println("check");
				}
				System.out.println("check2");}
				sqlMapper.insert("todayInsert",re);
				System.out.println("check3");
		}
			} catch (Exception e) {
				System.out.println("flag = null");
			}
			
		}
		}
		catch(Exception e){
			
			//회원 아이디가 없을 경우 
			//로그인 실패 보여주기 		
			System.out.println("아이디 없음");
			mv.addObject("BODY_PATH","/join/loginFail.jsp");
			mv.setViewName("/main/main.jsp");
			return mv;
		}
		
		if(dto.getPw().equals(real_pw)){
			//로그인 성공 
			//세션부여 
			session.setAttribute("id", id);
			session.setAttribute("grade", grade);
			mv.addObject("id",id);
			mv.addObject("grade",grade);
			mv.addObject("BODY_PATH","/join/loginSu.jsp");
			mv.setViewName("/main/main.jsp");
			return mv;
		}
		else{//로그인 실패 
			//로그인 실패 보여주기 
			System.out.println("비번틀림");
			mv.addObject("BODY_PATH","/join/loginFail.jsp");
			mv.setViewName("/main/main.jsp");
			return mv;
		}
	
	}
	
	

	//로그인 중복
	@RequestMapping("/checkID.do")
	public ModelAndView checkID(HttpServletRequest request){ //세션도 따로 지정없이 그냥 가지고 올 수 있음. 
	
		boolean check=false;
		String id = request.getParameter("id");
		
		try {
			int count= (int)sqlMapper.queryForObject("havaMemberID",id);
			if(count==0){
				check=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv= new ModelAndView();
		mv.addObject("check",check);
		mv.addObject("id",id);
		mv.setViewName("/join/confirmId.jsp");
		return mv;
	
	}
	
		
			
				
		//회원뷰_아이디중복확인
		@RequestMapping("/confirmId.do")
		public ModelAndView confirmId(MemberDTO m, HttpServletRequest request){ 
			 	ModelAndView mv = new ModelAndView(); //model에서 처리된 것을 view로 보내기 위해서 
				boolean check=false;
				String id = m.getId();//request.getParameter("id");
				
				try{
					int count= (int)sqlMapper.queryForObject("havaMemberID",id);
					if(count==0){
						check=true;
					}
					}catch(Exception e){ 
						
					}

				mv.addObject("check", check); //이거 값을 넘겨줘야지
				mv.addObject("id",id);
				mv.setViewName("/join/confirmId.jsp");
				return mv;
			}	 
	
	
	
	@RequestMapping("/mailService.do")
	public ModelAndView mailService(MailDTO m){
		String code =""; 
		
		String host = "smtp.gmail.com";//smtp 서버
		String subject = m.getId()+"님"+m.getReserve_date_string() +" "+m.getStr_time()+"시 예약입니다.";
		String fromName = "관리자";
		String from = "pino8282@gmail.com";//보내는 메일	
		String to1 = m.getEmail();
//				memberDTO.getEmail();		
//		code ="메일을 보냈습니다. :)";// code(8);
		String content = m.getReserve_date_string() +" "+m.getStr_time()+"시에 "
				+ "병원 예약이 있습니다. ";//"아이디 : "+memberDTO.getId()+"<br/> 인증번호 : "+code;
		System.out.println(content);

		try{
		//세션생성
		Properties props = new Properties();
        // G-Mail SMTP 사용시
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.auth", "true");
		
        Session mailSession = Session.getInstance(props,
        		  new javax.mail.Authenticator() {
        			protected PasswordAuthentication getPasswordAuthentication() {
        				return new PasswordAuthentication("shska03", "taeseok11");
        			}
        });
        
        //메세지 작성
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B")));//보내는 사람 설정
        
        InternetAddress[] address1 = {new InternetAddress(to1)};
        msg.setRecipients(Message.RecipientType.TO, address1);//받는 사람설정1
        msg.setSubject(subject);// 제목 설정
        msg.setSentDate(new java.util.Date());// 보내는 날짜 설정
        msg.setContent(content,"text/html;charset=euc-kr"); // 내용 설정 (HTML 형식)
        
        Transport.send(msg); // 메일 전송
		}catch(MessagingException e) {
//			result = false;
			e.printStackTrace();
		}catch(Exception e) {
//			result = false;
			e.printStackTrace();
		}
//		result = true;	
		
		
		ModelAndView mv = new ModelAndView();
		return mv;
		
	}
	public String code(int length)
	{
		int index = 0;
		char[] charSet = new char[]{
				'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				'o','p','q','r','s','t','u','v','w','x','y','z'};
					
		StringBuffer sb = new StringBuffer();		
			for(int i = 1; i<length; i++)
			{
				index = (int)(charSet.length*Math.random());
				sb.append(charSet[index]);
			}
			return sb.toString();
	}
	
	
	
	public void reserveCheck(MailDTO m){
	String code =""; 
		
		String host = "smtp.gmail.com";//smtp 서버
String subject = m.getId()+"님"+m.getReserve_date_string() +" "+m.getStr_time()+"시 예약입니다.";
		String fromName = "관리자";
		String from = "pino8282@gmail.com";//보내는 메일	
		String to1 = m.getEmail();
//				memberDTO.getEmail();		
//		code ="메일을 보냈습니다. :)";// code(8);
		String content = m.getReserve_date_string() +" "+m.getStr_time()+"시에 "
				+ "병원 예약이 있습니다. 예약시간 15분 전에 도착하여 대기하여주시기 바랍니다. "
				+ "문의:070-000-0000/ 070-111-1111";//"아이디 : "+memberDTO.getId()+"<br/> 인증번호 : "+code;
		System.out.println(content);

		try{
		//세션생성
		Properties props = new Properties();
        // G-Mail SMTP 사용시
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.auth", "true");
		
        Session mailSession = Session.getInstance(props,
        		  new javax.mail.Authenticator() {
        			protected PasswordAuthentication getPasswordAuthentication() {
        				return new PasswordAuthentication("shska03", "taeseok11");
        			}
        });
        
        //메세지 작성
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B")));//보내는 사람 설정
        
        InternetAddress[] address1 = {new InternetAddress(to1)};
        msg.setRecipients(Message.RecipientType.TO, address1);//받는 사람설정1
        msg.setSubject(subject);// 제목 설정
        msg.setSentDate(new java.util.Date());// 보내는 날짜 설정
        msg.setContent(content,"text/html;charset=euc-kr"); // 내용 설정 (HTML 형식)
        
        Transport.send(msg); // 메일 전송
		}catch(MessagingException e) {
//			result = false;
			e.printStackTrace();
		}catch(Exception e) {
//			result = false;
			e.printStackTrace();
		}
//		result = true;	
		
	}
	
	
	//날짜 설정하는 메소드 
	public static Date getDate(Date thisDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDate);
		cal.set(cal.get(cal.YEAR),cal.get(cal.MONTH),cal.get(cal.DATE), 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		System.out.println("날짜는"+cal.getTime());
		return cal.getTime();
	}
	
	
	public String returnDateString(Date date){
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        
	        String today = df.format(date);
	        System.out.println(today);
	        
	        return today;
		
	}
	
}