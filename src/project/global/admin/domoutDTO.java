package project.global.admin;

//��� ���̺� �̸�: leaving
//domoutForm, domoutPro / domOkForm ���� ������ ���
//��� ���� ������ ������
public class domoutDTO {
	private int no; //��������ȣ
	private String stnum; //�й�
	private String part; //�а�
	private String name; //����
	private String dormitory; //��Ȱ��
	private String ho; //ȣ
	private String reason; //��ǻ���
	private String dday; //��ǿ�����
	private int allow; //���ι�ư

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getStnum() {
		return stnum;
	}
	public void setStnum(String stnum) {
		this.stnum = stnum;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDday() {
		return dday;
	}
	public void setDday(String dday) {
		this.dday = dday;
	}
	public int getAllow() {
		return allow;
	}
	public void ssetAllow(int allow) {
		this.allow = allow;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getDormitory() {
		return dormitory;
	}
	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}
	
}
