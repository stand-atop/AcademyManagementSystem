package project.global.student;

public class plan2DTO {
	private String classnum;//�����ڵ�
	private String classname;//���Ǹ�
	private String date1;//�⵵
	private String semester;//�б�
	private String part;//�а�
	private String homeroompro;//��米��
	private int score;//����
	private String building;//�ǹ�
	private String roomnum;//���ǽ�
	private String day;//����
	private int time;//����
	private int can; //����
	private int allow;//������ȿ��

	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public void setPart(String part) {
		 this.part = part;
	}
	public void setHomeroompro(String homeroompro) {
		this.homeroompro = homeroompro;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void setCan(int can) {
		this.can = can;
	}
	public void setAllow(int allow) {
		this.allow = allow;
	}
	
	
	public String getBuilding() {
		return building;
	}
	public String getRoomnum() {
		return roomnum;
	}
	public String getDay() {
		return day;
	}
	public int getTime() {
		return time;
	}
	public int getCan() {
		return can;
	}
	public String getClassnum() {
		return classnum;
	}
	public String getClassname() {
		return classname;
	}
	public String getDate1() {
		return date1;
	}
	public String getSemester() {
		return semester;
	}
	public String getPart() {
		return part;
	}
	public String getHomeroompro() {
		return homeroompro;
	}
	public int getScore() {
		return score;
	}
	public int getallow() {
		return allow;
	}
	
}
