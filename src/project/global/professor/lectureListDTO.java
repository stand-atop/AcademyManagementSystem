package project.global.professor;

public class lectureListDTO {
	private String building; //�ǹ���
	private String roomnum; //���ǽ�
	private String day; //����
	private int time; //����
	private int can; //����
	private String classcode; //���ǽ��ڵ�
	private String part; //�а�
	private String classname; //���Ǹ�
	private String score; //����
	
	
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
	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public void setClassname(String classname) {
		
		this.classname = classname;
	}
	public void setScore(String score) {
		this.score = score;
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
	public String getClasscode() {
		return classcode;
	}
	public String getPart() {
		
		return part;
	}
	public String getClassname() {
		
		return classname;
	}
	public String getScore() {
		return score;
	}

}
