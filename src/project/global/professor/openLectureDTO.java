package project.global.professor;

public class openLectureDTO {
	
	private String date1; //�⵵
	private String semester; //�б� 
	private String homeroompro; //������
	private String id; //����ID
	private String part; //�а�
	private String classname; //���Ǹ�
	private int score; //����
	private String proemail;//�ٸ����̺��� ������ - ����email
	private String classcode; //���ǽ��ڵ�
	private String proemail1; //
	private String who; //�ο�
	
	
	
	public void setDate1(String date1) {
		
		this.date1 = date1;
	}
	public void setSemester(String semester) {
		
		this.semester = semester;
	}
	public void setHomeroompro(String homeroompro) {
		
		this.homeroompro = homeroompro;
	}
	public void setId(String id) {
		
		this.id = id;
	}
	
	public void setProemail1(String proemail1) {
		
		this.proemail1 = proemail1;
	}
	
	public void setPart(String part) {
		
		this.part = part;
	}
	
	public void setClassname(String classname) {
		
		this.classname = classname;
	}
		
	public void setScore(int score) {
		
		this.score = score;
	}
		
	public void setProemail(String proemail) {
		
		this.proemail = proemail;
	}

	public void setClasscode(String classcode) {
		
		this.classcode = classcode;
	}
	
	
	public String getDate1() {
		
		return date1;
	}
	
	public String getSemester() {
		
		return semester;
	}
	public String getHomeroompro() {
		
		return homeroompro;
	}
	public String getId() {
		
		return id;
	}
	public String getProemail1() {
		
		return proemail1;
	}
	
		
	public String getPart() {
		
		return part;
	}
	public String getClassname() {
		
		return classname;
	}
	public int getScore() {
	
		return score;
	}
	public String getProemail() {
	
		return proemail;
	}
	public String getClasscode() {
	
		return classcode;
	}
	
	
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	
	
}
