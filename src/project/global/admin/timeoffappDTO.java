package project.global.admin;

// table:status / �޺��н�û ��ȸ/���� ������

public class timeoffappDTO {
	private String stnum;		//�й�
	private String name;		//�̸�
	private String couse;		//���л���
	private String day;		//��û����
	private String year;		//����⵵
	private String semester;	//�����б�
	private int approval;			//����
	
	public void setStnum(String stnum) {
		this.stnum = stnum;
	}
	
	public String getStnum() {
		return stnum;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCouse(String couse) {
		this.couse = couse;
	}
	
	public String getCouse() {
		return couse;
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
	
	public String getYear() {
		return year;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public String getSemester() {
		return semester;
	}
	
	public void setApproval(int approval) {
		this.approval = approval;
	}
	
	public int getApproval() {
		return approval;
	}
}
