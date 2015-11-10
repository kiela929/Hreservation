package reserve.chart;

import java.util.Date;

public class PieDTO {
private Date this_month_date;
private Date today;
private String part_name;
private int partnum;
private Date reserve_date;
private int reservenum; 


public Date getThis_month_date() {
	return this_month_date;
}
public void setThis_month_date(Date this_month_date) {
	this.this_month_date = this_month_date;
}
public String getPart_name() {
	return part_name;
}
public void setPart_name(String part_name) {
	this.part_name = part_name;
}

public Date getToday() {
	return today;
}
public void setToday(Date today) {
	this.today = today;
}
public int getPartnum() {
	return partnum;
}
public void setPartnum(int partnum) {
	this.partnum = partnum;
}
public Date getReserve_date() {
	return reserve_date;
}
public void setReserve_date(Date reserve_date) {
	this.reserve_date = reserve_date;
}
public int getReservenum() {
	return reservenum;
}
public void setReservenum(int reservenum) {
	this.reservenum = reservenum;
}


}
