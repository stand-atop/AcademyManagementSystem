package project.global.admin;

import java.sql.Timestamp;

//���̺� �̸� : sexnight 

public class sleepoutDTO {
	//�ܹ� ���� ������ ������
	private int no; //������
	private String part; //�а�
	private String stnum; //�й�
	private String name; //����
	private int allow; //����
	//�ܹ� ���� ���������� ���� Ŭ���� sleeplistPro.jsp���� ���̴� ������
	private String outsleep; //�ܹڻ���
	private Timestamp startday; //�ܹ� ������
	private Timestamp comeback; //�ܹ� �Ͱ���
	private String secret; //�ܹ� ����
	
	
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
	public int getAllow() {
		return allow;
	}
	public void setAllow(int allow) {
		this.allow = allow;
	}
	public String getOutsleep() {
		return outsleep;
	}
	public void setOutsleep(String outsleep) {
		this.outsleep = outsleep;
	}
	public Timestamp getStartday() {
		return startday;
	}
	public void setStartday(Timestamp startday) {
		this.startday = startday;
	}
	public Timestamp getComeback() {
		return comeback;
	}
	public void setComeback(Timestamp comeback) {
		this.comeback = comeback;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
}
