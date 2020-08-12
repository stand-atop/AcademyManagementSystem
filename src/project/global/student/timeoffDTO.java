package project.global.student;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

// table : status / �޺��� ��û dto
public class timeoffDTO {
	private String name;		// �̸�
	private String stnum;		// �й�
	private String couse;		// ���л���
	private String day;			// ��û����
	private String year;		// ����⵵
	private String semester;	// �����б�
	private String zon;			// �����ȣ
	private String street;		// ���θ��ּ�
	private String addr;		// ���ּ�
	private int approval;	// ���ο���

	public void setZon(String zon) {
		this.zon = zon;
	}
	
	public String getZon() {
		return zon;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public 	String getAddr() {
		return addr;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setStnum(String stnum) {
		this.stnum = stnum;
	}
	
	public String getStnum() {
		return stnum;
	}
	
	public void setCouse(String couse) {
		this.couse =couse;
	}
	
	public String getCouse() {
		return couse;
	}
	
	public void setDay() {
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		int nYear = calendar.get(Calendar.YEAR);
		int nMonth = calendar.get(Calendar.MONTH) + 1;
		int nDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		String year = Integer.toString(nYear);
		String mon = Integer.toString(nMonth);
		String day = String.format("%02d", nDay);

		this.day = year+"-0"+mon+"-"+day;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public String getDay() {
		return day;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public void setYear() {
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		int nYear = calendar.get(Calendar.YEAR);
		int nMonth = calendar.get(Calendar.MONTH) + 1;
		
		int num = nYear;	// ���س⵵
		if(nMonth > 7) {	// ���� ���� 7���� ũ�� ���� �⵵ 1 �߰� 
			num++;			// : 8~12 ���� ���� ��û�� ���� 1�б� ���� ��û ����. 
		}
		String year = Integer.toString(num);
		this.year = year;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public String getSemester() {
		return semester;
	}
	
	public void setApproval(int approval){
		this.approval = approval;
	}
	
	public int getApproval() {
		return approval;
	}

}
