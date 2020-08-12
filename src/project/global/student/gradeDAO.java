package project.global.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class gradeDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Connection getConnection() throws Exception{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@nullmaster.iptime.org:1521:orcl";
			String user = "class09", pass="class09";
			conn =  DriverManager.getConnection(url, user, pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public gradeDTO student(String stnum) { //������ȸ�� �л� ������ȸ
		gradeDTO info = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from student where stnum=?"); //�й����� �ֱ�
			pstmt.setString(1, stnum); //�й� pro ���ؼ� �Ѱ� �޾ƾ� �ҵ�
			rs = pstmt.executeQuery();
			info = new gradeDTO();
			if(rs.next()) {
				info.setStnum(rs.getString("stnum")); //�й�
				info.setStname(rs.getString("stname")); //�̸�
				info.setGrade(rs.getInt("grade")+""); //�г�
				info.setPart(rs.getString("part")); //�а�
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}
		return info;
	}	
	
	/*
	 * public gradeDTO studentInfo(String stnum) { //������ȸ�� �л� ������ȸ gradeDTO stuinfo
	 * = null; try { conn = getConnection(); pstmt =
	 * conn.prepareStatement("select * from signup where stnum=? and record!=null");
	 * //�й��� ������ �ο��� ���� ���� �ֱ� pstmt.setString(1, stnum); rs = pstmt.executeQuery();
	 * stuinfo = new gradeDTO(); if(rs.next()) {
	 * stuinfo.setStnum(rs.getString("stnum"));
	 * stuinfo.setStname(rs.getString("stname"));
	 * stuinfo.setRecord(rs.getInt("record"));
	 * stuinfo.setClassname(rs.getString("classname"));
	 * stuinfo.setGrade(rs.getString("grade"));
	 * stuinfo.setScore(rs.getInt("score")); stuinfo.setPart(rs.getString("part"));
	 * } }catch(Exception e) { e.printStackTrace(); }finally { if(rs != null) try
	 * {rs.close();} catch(SQLException s) {} if(pstmt != null) try {pstmt.close();}
	 * catch(SQLException s) {} if(conn != null) try {conn.close();}
	 * catch(SQLException s) {} } return stuinfo; }
	 */
	
	//���� ����Ʈ
	public List<gradeDTO> resultChecking(String stnum){ 
		List<gradeDTO> list = null;		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from (select stnum, stname, classnum, "
					+ "classname, record, grade, score, part from(select * from signup "
					+ "order by grade desc, classname asc)order by grade desc, classname asc)"
					+ " where stnum=?"); //
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();
			list = new ArrayList<gradeDTO>();
			while(rs.next()) {
				gradeDTO dto = new gradeDTO();
				dto.setStnum(rs.getString("stnum"));
				dto.setStname(rs.getString("stname"));
				dto.setLecturecode(rs.getString("classnum"));
				dto.setClassname(rs.getString("classname"));
				dto.setRecord(rs.getInt("record"));
				dto.setGrade(rs.getString("grade"));
				dto.setScore(rs.getInt("score"));
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}		
		return list;		
	}	
	
	//�г� ���ǿ� �°� ���� �˻�
	public List<gradeDTO> gradeSelect(String grade, String stnum){
		List<gradeDTO> select = null;
		try {
			conn = getConnection();
			if(grade.equals("1")|| grade.equals("2")) { //����Ʈ�� 1�г�, 2�г��� �������� ����� ����
				pstmt = conn.prepareStatement("select * from signup where grade = ? and stnum = ? ");
				pstmt.setString(1, grade);
				pstmt.setString(2, stnum);
				rs = pstmt.executeQuery();
				select = new ArrayList<gradeDTO>();
				while(rs.next()) {
					gradeDTO selectlist = new gradeDTO();
					selectlist.setStnum(rs.getString("stnum")); //�й�
					selectlist.setStname(rs.getString("stname")); //�л��̸�
					selectlist.setLecturecode(rs.getString("classnum")); //�����ڵ�
					selectlist.setClassname(rs.getString("classname")); //���Ǹ�
					selectlist.setRecord(rs.getInt("record")); //����
					selectlist.setGrade(rs.getString("grade")); //�г�
					selectlist.setScore(rs.getInt("score")); //�ο�����
					select.add(selectlist);
				}
			}else if(grade.equals("����")) { //��ü�г� ��ȸ
				pstmt = conn.prepareStatement("select * from signup where stnum = ?  order by grade desc");
				pstmt.setString(1, stnum);
				rs = pstmt.executeQuery();
				select = new ArrayList<gradeDTO>();
				while(rs.next()) {
					gradeDTO selectlist = new gradeDTO();
					selectlist.setStnum(rs.getString("stnum")); //�й�
					selectlist.setStname(rs.getString("stname")); //�л��̸�
					selectlist.setLecturecode(rs.getString("classnum")); //�����ڵ�
					selectlist.setClassname(rs.getString("classname")); //���Ǹ�
					selectlist.setRecord(rs.getInt("record")); //����
					selectlist.setGrade(rs.getString("grade")); //�г�
					selectlist.setScore(rs.getInt("score")); //�ο�����
					select.add(selectlist);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}		
		return select;
	}
	
}
