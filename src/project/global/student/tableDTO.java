package project.global.student;

public class tableDTO { //�ð�ǥ ������û���̺�
	
	private String classname; //���Ǹ�
	private String homeroompro; //��米��
	private int score; //����
	private String classroom; //���ǽ�
	private String classtime; //���ǽð�
	private String date1; //�⵵
	private String semester; //�б�
	private String building; //�ǹ���
	private String day; //����
	private String classnum; //�����ڵ�
	private String part; //�а�

	
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public void setHomeroompro(String homeroompro) {
		this.homeroompro = homeroompro;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public void setClasstime(String classtime) {
		this.classtime = classtime;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}
	public void setPart(String part) {
		this.part = part;
	}


	
	public String getClassname(){
		return classname;
	}
	public String getHomeroompro() {
		return homeroompro;
	}
	public int getScore() {
		return score;
	}
	public String getClassroom() {
		return classroom;
	}
	public String getClasstime(){
		return classtime;
	}
	public String getDate1() {
		return date1;
	}
	public String getSemester() {
		return semester;
	}
	public String getBuilding() {
		return building;
	}
	public String getDay() {
		return day;
	}
	public String getClassnum() {
		return classnum;
	}
	public String getPart() {
		return part;
	}

}
