package project.global.professor;

public class gradeDTO { //������ȸ - �������̺�
	private String stnum; //�й�
	private String name; //�̸�
	private String lecturecode; //�����ڵ�
	private String classname; //���Ǹ�
	private int record; //����
	private String grade; //�г�
	private int score; //����
	private String part; //�а�
	private String proid;//�����й� 

	public void setStnum(String stnum){
		this.stnum = stnum;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLecturecode(String lecturecode) {
		this.lecturecode = lecturecode;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public void setRecord(int record) {
		this.record = record;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public void setProid(String proid) {
		this.proid = proid;
	}
	
	public String getStnum() {
		return stnum;
	}
	public String getName() {
		return name;
	}
	public String getLecturecode() {
		return lecturecode;
	}
	public String getClassname() {
		return classname;
	}
	public int getRecord() {
		return record;
	}
	public String getGrade() {
		return grade;
	}
	public int getScore() {
		return score;
	}
	public String getPart() {
		return part;
	}
	public String getProid() {
		return proid;
	}
}
