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
//	//��¥ Ÿ�� �����ϱ� 
//	
//	this.reserve_date = StringToDate(reserve_date);
//}



//
//public Date StringToDate(String textDate){
//	Date date = null; 
//	try {
//		   
//		// "2007-07-22" �̶� ���ڿ��� 2007�� 7�� 22���� ������ ���� Date��ü�� ������
////		   String textDate = "2007-07-22";
//		// �Է��� ��¥�� ���ڿ��� yyyy-MM-dd �����̹Ƿ� �ش� �������� �����͸� �����Ѵ�.
//		 
//		 
//		 
//		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//Date �� �ϳ� ���� 
//		//SimpleDateFormat.parse()�޼ҵ带 ���� Date��ü�� �����Ѵ�.
//		   
//		//SimpleDateFormat.parse()�޼ҵ�� �Է��� ���ڿ� ������ ��¥�� ���˰� �ٸ���� java.text.ParseException�� �߻��Ѵ�.
//		   date = format.parse(textDate); //�Է¹��� String �� Date Ÿ������ �ٲ� 
//		      
////		//������ ���� date��ü�� ���� 7��22������ Ȯ�� �غ���.
////		   java.text.SimpleDateFormat format1 = new java.text.SimpleDateFormat("yyyy��MM��dd�� HH��mm��ss��");
////		   String dateString = format1.format(date);
////		   
////		//Date��ü�� ���ڸ� Ȯ���Ѵ�..  ��� : 2007��07��22�� 00��00��00��
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
//	// ���� �Ͻ� ������ ���� Date ��ü�� �����Ѵ�.
//	 Date currentDate = new Date();  
//	  
//	//Date��ü�κ��� Ư���� ������ ���ڿ��� �Ͻø� ������ ���� �����͸� �����Ѵ�.
//	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//	  
//	//�����͸� �̿��Ͽ� Date��ü�κ��� ���ڿ��� ������.
//	  String dateString = format.format(date);
//	  
//	//��ȯ�� ���ڿ��� Ȯ���Ѵ�.  ��� : 2007��07��22�� 02��21��42��
//	  System.out.println(dateString); 
//	return dateString;
//}
//
//public Date StringToDate_Time(String textDate){
//	Date date = null; 
//	try {
//		   
//		// "2007-07-22" �̶� ���ڿ��� 2007�� 7�� 22���� ������ ���� Date��ü�� ������
////		   String textDate = "2007-07-22";
//		// �Է��� ��¥�� ���ڿ��� yyyy-MM-dd �����̹Ƿ� �ش� �������� �����͸� �����Ѵ�.
//		 
//		 
//		 
//		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");//Date �� �ϳ� ���� 
//		//SimpleDateFormat.parse()�޼ҵ带 ���� Date��ü�� �����Ѵ�.
//		   
//		//SimpleDateFormat.parse()�޼ҵ�� �Է��� ���ڿ� ������ ��¥�� ���˰� �ٸ���� java.text.ParseException�� �߻��Ѵ�.
//		   date = format.parse(textDate); //�Է¹��� String �� Date Ÿ������ �ٲ� 
//		      
////		//������ ���� date��ü�� ���� 7��22������ Ȯ�� �غ���.
////		   java.text.SimpleDateFormat format1 = new java.text.SimpleDateFormat("yyyy��MM��dd�� HH��mm��ss��");
////		   String dateString = format1.format(date);
////		   
////		//Date��ü�� ���ڸ� Ȯ���Ѵ�..  ��� : 2007��07��22�� 00��00��00��
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
//	// ���� �Ͻ� ������ ���� Date ��ü�� �����Ѵ�.
//	 Date currentDate = new Date();  
//	  
//	//Date��ü�κ��� Ư���� ������ ���ڿ��� �Ͻø� ������ ���� �����͸� �����Ѵ�.
//	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	  
//	//�����͸� �̿��Ͽ� Date��ü�κ��� ���ڿ��� ������.
//	  String dateString = format.format(date);
//	  
//	//��ȯ�� ���ڿ��� Ȯ���Ѵ�.  ��� : 2007��07��22�� 02��21��42��
//	  System.out.println(dateString); 
//	return dateString;
//}

	
	
}
