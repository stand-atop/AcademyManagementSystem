package project.global.admin;
//�Խ� ���̺� ��� : enter 
//����� �հ��� �� ���� ������ ������
public class roomDTO {
	//private int no; //������ ��ȣ
	private int allow; //����� �Խ� ���� ����
	private String stnum;//�й�
	private String gender; //����
	private String dreamroom; //��Ȱ��(��)
	private String name; //����
	private String part; //�а�
	private String ho; //ȣ�� ����
	private int ok; //�����Ϸ� ���ι�ư
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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

	public int getOk() {
		return ok;
	}
	public void setOk(int ok) {
		this.ok = ok;
	}
	public int getAllow() {
		return allow;
	}
	public void setAllow(int allow) {
		this.allow = allow;
	}


}
