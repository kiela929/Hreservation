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
	public ModelAndView joinPro(MemberDTO dto){//,HttpSession session){ //���ǵ� ���� �������� �׳� ������ �� �� ����. 
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
	public ModelAndView doctorJoinPro(DoctorDTO dto){//,HttpSession session){ //���ǵ� ���� �������� �׳� ������ �� �� ����. 
//		int check=0;
		 try {
			 dto.setGrade(2);
			 
			 System.out.println("�ǻ���: "+dto.getGrade());
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
	
	//�α��� �� 
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

	//�α��� ó��
	@RequestMapping("/loginPro.do")
	public ModelAndView loginPro(MemberDTO dto, HttpSession session){ //���ǵ� ���� �������� �׳� ������ �� �� ����. 
		/*1. ���̵�,pw �޾ƿ��� 
		 * 2. ȸ�����̺��� ���̵� �ִ��� Ȯ��
		 * 3. ���̵�� ��ȸ�ؼ� ��й�ȣ ��������
		 * 4. ��й�ȣ�� ��ġ�ϴ��� Ȯ�� 
		 * */
		
		System.out.println("loginPro ����");
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
			//�����ڷ� �α���
			//���� ��¥�� DB ã��
			//������ ���Ϻ�����
			//������ �������ʱ�
			Date today=new Date();
			try {	
			ReserveDTO re= new ReserveDTO();
			re.setReserve_date(getDate(today));
			
			
				int check_today=(int)sqlMapper.queryForObject("today",re);
			if(check_today==0){
				System.out.println(getDate(today));
		
				System.out.println("ã����¥:"+re.getReserve_date());
				try{
				mailList=(List<MailDTO>)sqlMapper.queryForList("today_reserve",re);
				//���ó�¥�� ���� �߿��� ������������
			
				
				}catch(Exception e){
					System.out.println("m: "+mailList.size());
					System.out.println("���翹�������� ����");}
				
				if(mailList.size()!=0){
			
				for(MailDTO m : mailList){
					m.setReserve_date_string(returnDateString(m.getReserve_date()));
					reserveCheck(m);//���Ϻ�����
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
			
			//ȸ�� ���̵� ���� ��� 
			//�α��� ���� �����ֱ� 		
			System.out.println("���̵� ����");
			mv.addObject("BODY_PATH","/join/loginFail.jsp");
			mv.setViewName("/main/main.jsp");
			return mv;
		}
		
		if(dto.getPw().equals(real_pw)){
			//�α��� ���� 
			//���Ǻο� 
			session.setAttribute("id", id);
			session.setAttribute("grade", grade);
			mv.addObject("id",id);
			mv.addObject("grade",grade);
			mv.addObject("BODY_PATH","/join/loginSu.jsp");
			mv.setViewName("/main/main.jsp");
			return mv;
		}
		else{//�α��� ���� 
			//�α��� ���� �����ֱ� 
			System.out.println("���Ʋ��");
			mv.addObject("BODY_PATH","/join/loginFail.jsp");
			mv.setViewName("/main/main.jsp");
			return mv;
		}
	
	}
	
	

	//�α��� �ߺ�
	@RequestMapping("/checkID.do")
	public ModelAndView checkID(HttpServletRequest request){ //���ǵ� ���� �������� �׳� ������ �� �� ����. 
	
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
	
		
			
				
		//ȸ����_���̵��ߺ�Ȯ��
		@RequestMapping("/confirmId.do")
		public ModelAndView confirmId(MemberDTO m, HttpServletRequest request){ 
			 	ModelAndView mv = new ModelAndView(); //model���� ó���� ���� view�� ������ ���ؼ� 
				boolean check=false;
				String id = m.getId();//request.getParameter("id");
				
				try{
					int count= (int)sqlMapper.queryForObject("havaMemberID",id);
					if(count==0){
						check=true;
					}
					}catch(Exception e){ 
						
					}

				mv.addObject("check", check); //�̰� ���� �Ѱ������
				mv.addObject("id",id);
				mv.setViewName("/join/confirmId.jsp");
				return mv;
			}	 
	
	
	
	@RequestMapping("/mailService.do")
	public ModelAndView mailService(MailDTO m){
		String code =""; 
		
		String host = "smtp.gmail.com";//smtp ����
		String subject = m.getId()+"��"+m.getReserve_date_string() +" "+m.getStr_time()+"�� �����Դϴ�.";
		String fromName = "������";
		String from = "pino8282@gmail.com";//������ ����	
		String to1 = m.getEmail();
//				memberDTO.getEmail();		
//		code ="������ ���½��ϴ�. :)";// code(8);
		String content = m.getReserve_date_string() +" "+m.getStr_time()+"�ÿ� "
				+ "���� ������ �ֽ��ϴ�. ";//"���̵� : "+memberDTO.getId()+"<br/> ������ȣ : "+code;
		System.out.println(content);

		try{
		//���ǻ���
		Properties props = new Properties();
        // G-Mail SMTP ����
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
        
        //�޼��� �ۼ�
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B")));//������ ��� ����
        
        InternetAddress[] address1 = {new InternetAddress(to1)};
        msg.setRecipients(Message.RecipientType.TO, address1);//�޴� �������1
        msg.setSubject(subject);// ���� ����
        msg.setSentDate(new java.util.Date());// ������ ��¥ ����
        msg.setContent(content,"text/html;charset=euc-kr"); // ���� ���� (HTML ����)
        
        Transport.send(msg); // ���� ����
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
		
		String host = "smtp.gmail.com";//smtp ����
String subject = m.getId()+"��"+m.getReserve_date_string() +" "+m.getStr_time()+"�� �����Դϴ�.";
		String fromName = "������";
		String from = "pino8282@gmail.com";//������ ����	
		String to1 = m.getEmail();
//				memberDTO.getEmail();		
//		code ="������ ���½��ϴ�. :)";// code(8);
		String content = m.getReserve_date_string() +" "+m.getStr_time()+"�ÿ� "
				+ "���� ������ �ֽ��ϴ�. ����ð� 15�� ���� �����Ͽ� ����Ͽ��ֽñ� �ٶ��ϴ�. "
				+ "����:070-000-0000/ 070-111-1111";//"���̵� : "+memberDTO.getId()+"<br/> ������ȣ : "+code;
		System.out.println(content);

		try{
		//���ǻ���
		Properties props = new Properties();
        // G-Mail SMTP ����
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
        
        //�޼��� �ۼ�
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B")));//������ ��� ����
        
        InternetAddress[] address1 = {new InternetAddress(to1)};
        msg.setRecipients(Message.RecipientType.TO, address1);//�޴� �������1
        msg.setSubject(subject);// ���� ����
        msg.setSentDate(new java.util.Date());// ������ ��¥ ����
        msg.setContent(content,"text/html;charset=euc-kr"); // ���� ���� (HTML ����)
        
        Transport.send(msg); // ���� ����
		}catch(MessagingException e) {
//			result = false;
			e.printStackTrace();
		}catch(Exception e) {
//			result = false;
			e.printStackTrace();
		}
//		result = true;	
		
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
	
	
	public String returnDateString(Date date){
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        
	        String today = df.format(date);
	        System.out.println(today);
	        
	        return today;
		
	}
	
}