package project.global.student;

public class gradeDTO { //������ȸ - �������̺�
	private String stnum; //�й�
	private String stname; //�л��̸�
	private String lecturecode; //�����ڵ�
	private String classname; //���Ǹ�
	private int record; //����
	private String grade; //�г�
	private int score; //����
	private String part; //�а�

	public void setStnum(String stnum){
		this.stnum = stnum;
	}
	public void setStname(String stname) {
		this.stname = stname;
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
	
	public String getStnum() {
		return stnum;
	}
	public String getStame() {
		return stname;
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
}
