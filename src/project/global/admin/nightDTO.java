package project.global.admin;

import java.sql.Timestamp;

public class nightDTO {
	private int no; //������
	private String stnum; //�й�
	private int allow; //����
	private String name; //�̸�
	private String dreamroom; //��Ȱ��
	private String ho; //ȣ
	private String part; //�а�
	private Timestamp reg_date; //��û��¥
	private String startday; //������
	private String comeback; //�Ͱ���
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getStnum() {
		return stnum;
	}
	public void setStnum(String stnum) {
		this.stnum = stnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDreamroom() {
		return dreamroom;
	}
	public void setDreamroom(String dreamroom) {
		this.dreamroom = dreamroom;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public int getAllow() {
		return allow;
	}
	public void setAllow(int allow) {
		this.allow = allow;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getStartday() {
		return startday;
	}
	public void setStartday(String startday) {
		this.startday = startday;
	}
	public String getComeback() {
		return comeback;
	}
	public void setComeback(String comeback) {
		this.comeback = comeback;
	}
	
}
