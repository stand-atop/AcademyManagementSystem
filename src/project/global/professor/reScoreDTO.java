package project.global.professor;

import java.sql.Timestamp;

public class reScoreDTO {

	private String stname;	// �̸�
	private int year1;		// ��
	private int mon;		// ��
	private int day1;		// ��
	private String stnum;	// �й�
	private String part;	// �а�
	private String phone;	// �ڵ�����ȣ
	private String zon;		// �����ȣ
	private String street;	// ���θ��ּ�
	private String addr;	// ���ּ�
	private String bank;	// ��������
	private String acnt;	// ����
	private int grade;		// �г�
	private String image;	// �̹���
	private String floor;	// �ź�
	private Timestamp acsday;	// ���ӳ�¥
	private String sex;		// ����
	private String domiuse;	// ����� ��뿩��
	private String pw; 		// ��й�ȣ
	
		
	public Timestamp getAcsday() {
		return acsday;
	}

	public void setAcsday(Timestamp acsday) {
		this.acsday = acsday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDomiuse() {
		return domiuse;
	}

	public void setDomiuse(String domiuse) {
		this.domiuse = domiuse;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}
	
	public String getFloor() {
		return floor;
	}	
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setStname(String stname) {
		this.stname = stname;
	}
	
	public String getStname() {
		return stname;
	}
	
	public void setYear1(int year1) {
		this.year1 = year1;
	}
	
	public int getYear1() {
		return year1;
	}
	
	public void setMon(int mon) {
		this.mon = mon;
	}
	
	public int getMon() {
		return mon;
	}
	
	public void setDay1(int day1) {
		this.day1 = day1;
	}
	
	public int getDay1() {
		return day1;
	}
	
	public void setStnum(String stnum) {
		this.stnum = stnum;
	}
	
	public String getStnum() {
		return stnum;
	}
	
	public void setPart(String part) {
		this.part = part;
	}
	
	public String getPart() {
		return part;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
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
	
	public String getAddr() {
		return addr;
	}
	
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public String getBank() {
		return bank;
	}
	
	public void setAcnt (String acount){
		this.acnt = acount;
	}
	
	public String getAcnt() {
		return acnt;
	}
}
