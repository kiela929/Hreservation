package reserve.reserve;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReserveDTO {
	
	private int reservenum;

	private String id;
	private int doctornum;
	private Date reserve_date;
	private String reserve_date_string;
	private String str_time;
	private Date reg_date;
	private String reg_date_string;
	
	private String name;
	private String jumin1;
	
	
	public int getReservenum() {
		return reservenum;
	}
	public void setReservenum(int reservenum) {
		this.reservenum = reservenum;
	}

	public int getDoctornum() {
		return doctornum;
	}
	public void setDoctornum(int doctornum) {
		this.doctornum = doctornum;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJumin1() {
		return jumin1;
	}
	public void setJumin1(String jumin1) {
		this.jumin1 = jumin1;
	}
	public Date getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(Date reserve_date) {
		
		this.reserve_date = reserve_date;
	}

	public String getStr_time() {
		return str_time;
	}
	public void setStr_time(String str_time) {
		this.str_time = str_time;
	}
	public String getReserve_date_string() {
		return reserve_date_string;
	}
	public void setReserve_date_string(String reserve_date_string) {
		this.reserve_date_string = reserve_date_string;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getReg_date_string() {
		return reg_date_string;
	}
	public void setReg_date_string(String reg_date_string) {
		this.reg_date_string = reg_date_string;
	}
	
	
	
	
	
//	public String getStr_time() {
//	return DateToString_Time(str_time);
//}
//public void setStr_time(String str_time) {
//	this.str_time = StringToDate(str_time);
//}
//public String getReserve_date() {
//	return DateToString(reserve_date);
//}
//public void setReserve_date(String reserve_date) {
//	//날짜 타입 통일하기 
//	
//	this.reserve_date = StringToDate(reserve_date);
//}



//
//public Date StringToDate(String textDate){
//	Date date = null; 
//	try {
//		   
//		// "2007-07-22" 이란 문자열로 2007년 7월 22일의 정보를 갖는 Date객체를 만들어보자
////		   String textDate = "2007-07-22";
//		// 입력할 날짜의 문자열이 yyyy-MM-dd 형식이므로 해당 형식으로 포매터를 생성한다.
//		 
//		 
//		 
//		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//Date 로 하나 생성 
//		//SimpleDateFormat.parse()메소드를 통해 Date객체를 생성한다.
//		   
//		//SimpleDateFormat.parse()메소드는 입력한 문자열 형식의 날짜가 포맷과 다를경우 java.text.ParseException을 발생한다.
//		   date = format.parse(textDate); //입력받은 String 을 Date 타입으로 바꿈 
//		      
////		//위에서 만든 date객체가 정말 7월22일인지 확인 해보자.
////		   java.text.SimpleDateFormat format1 = new java.text.SimpleDateFormat("yyyy년MM월dd일 HH시mm분ss초");
////		   String dateString = format1.format(date);
////		   
////		//Date객체의 날자를 확인한다..  결과 : 2007년07월22일 00시00분00초
////		   System.out.println(dateString);
//		  } catch (java.text.ParseException ex) {
//		   ex.printStackTrace();
//		  }
//		   
//		 
//	return date;
//}
//
//public String DateToString(Date date){
//	
//	// 현재 일시 정보를 갖는 Date 객체를 생성한다.
//	 Date currentDate = new Date();  
//	  
//	//Date객체로부터 특정한 형식의 문자열로 일시를 만들어내기 위한 포매터를 생성한다.
//	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//	  
//	//포매터를 이용하여 Date객체로부터 문자열을 만들어낸다.
//	  String dateString = format.format(date);
//	  
//	//변환된 문자열을 확인한다.  결과 : 2007년07월22일 02시21분42초
//	  System.out.println(dateString); 
//	return dateString;
//}
//
//public Date StringToDate_Time(String textDate){
//	Date date = null; 
//	try {
//		   
//		// "2007-07-22" 이란 문자열로 2007년 7월 22일의 정보를 갖는 Date객체를 만들어보자
////		   String textDate = "2007-07-22";
//		// 입력할 날짜의 문자열이 yyyy-MM-dd 형식이므로 해당 형식으로 포매터를 생성한다.
//		 
//		 
//		 
//		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");//Date 로 하나 생성 
//		//SimpleDateFormat.parse()메소드를 통해 Date객체를 생성한다.
//		   
//		//SimpleDateFormat.parse()메소드는 입력한 문자열 형식의 날짜가 포맷과 다를경우 java.text.ParseException을 발생한다.
//		   date = format.parse(textDate); //입력받은 String 을 Date 타입으로 바꿈 
//		      
////		//위에서 만든 date객체가 정말 7월22일인지 확인 해보자.
////		   java.text.SimpleDateFormat format1 = new java.text.SimpleDateFormat("yyyy년MM월dd일 HH시mm분ss초");
////		   String dateString = format1.format(date);
////		   
////		//Date객체의 날자를 확인한다..  결과 : 2007년07월22일 00시00분00초
////		   System.out.println(dateString);
//		  } catch (java.text.ParseException ex) {
//		   ex.printStackTrace();
//		  }
//		   
//		 
//	return date;
//}
//
//public String DateToString_Time(Date date){
//	
//	// 현재 일시 정보를 갖는 Date 객체를 생성한다.
//	 Date currentDate = new Date();  
//	  
//	//Date객체로부터 특정한 형식의 문자열로 일시를 만들어내기 위한 포매터를 생성한다.
//	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	  
//	//포매터를 이용하여 Date객체로부터 문자열을 만들어낸다.
//	  String dateString = format.format(date);
//	  
//	//변환된 문자열을 확인한다.  결과 : 2007년07월22일 02시21분42초
//	  System.out.println(dateString); 
//	return dateString;
//}

	
	
}
