package project.global.admin;

import java.sql.Timestamp;

//table : classroom (���ǽ� ���� ���̺�)/ ���ǽ� ��û���� / procheckForm

public class procheckDTO {
	
	private String can; // �ο�
	private String classnum; //�����ڵ�
	private int allow; // ����/����
	private String classname; //���Ǹ�
	private int score; //���� 
	private Timestamp reg_date; //��û ��¥ 
	
	public String getCan() {
		return can;
	}
	public void setCan(String can) {
		this.can = can;
	}
	public String getClassnum() {
		return classnum;
	}
	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}
	public int getAllow() {
		return allow;
	}
	public void setAllow(int allow) {
		this.allow = allow;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
		
	
	
}
