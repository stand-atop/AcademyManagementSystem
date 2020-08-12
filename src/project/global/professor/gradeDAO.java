package project.global.professor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.global.student.evalDTO;

public class gradeDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Connection getConnection() throws Exception{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@nullmaster.iptime.org:1521:orcl";
			String user = "class09" ,pass="class09";
			conn = DriverManager.getConnection(url,user,pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public int classCount(String classnum) { //�����ο� �ҷ�����// ���Ǹ��� ��ȸ�ο����� �Ű����� ����(classname)
		//gradeDTO claCount = null;//��Ƽ�� Ÿ������ clacount���� �ʱ�ȭ
		int count = 0; //ī��Ʈ �������� �ʱ�ȭ ���־���.
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from signup where classnum=? ");//signup���̺��� classnum�� �ο��� �ҷ��´�
			pstmt.setString(1, classnum); //��Ʈ�� Ÿ������ set(classnum�� ���� ����)���ش�.
			rs = pstmt.executeQuery();
			rs.next();//ȭ��ǥ����Ų��.
			count = rs.getInt(1); //ī��Ʈ�� ù��° ��(1) ��
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}
		return count;
	}
	
	
	
	public List<gradeDTO> stcall(String classnum){//�л��̸�, �л��й� �ҷ����� 
		List<gradeDTO> list = null;
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement("select * from signup where classnum=?");
			pstmt.setString(1, classnum); //�����ڵ� ����
			rs = pstmt.executeQuery();
			list = new ArrayList<gradeDTO>();
			while(rs.next()) {
				gradeDTO dto = new gradeDTO();
				dto.setStnum(rs.getString("stnum")); //�л��й�
				dto.setName(rs.getString("stname")); //�л��̸�
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}
		return list;
	}
	
	//�������̺��� ������ ��� ������� ����Ʈ�� �����´� - id, �⵵, �б� - class1
	public List<gradeDTO> lectureList(String proid, String date1, String semester){ 
		List<gradeDTO> list = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from class1 where allow=1 and id=? and date1=? and semester=?");//id, �⵵, �бⰡ �´°�?
			pstmt.setString(1, proid);
			pstmt.setString(2, date1);
			pstmt.setString(3, semester);
			rs = pstmt.executeQuery();
			list = new ArrayList<gradeDTO>();
			while(rs.next()) {
				gradeDTO lecture = new gradeDTO();
				lecture.setClassname(rs.getString("classname")); //���Ǹ�
				lecture.setLecturecode(rs.getString("classnum")); //�����ڵ�
				list.add(lecture);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(rs != null) try {rs.close();} catch(SQLException s) {}
		}
		return list;
	}
	
	//������ �Է��� �� �ֵ��� ������Ʈ��
	public void insertResult(gradeDTO dto) {
		try {
			conn = getConnection();			
			pstmt = conn.prepareStatement("update signup set record=? where stnum=? and classnum=?"); //�й��� �����ڵ尡 ���� ��� ������ ����
			pstmt.setInt(1, dto.getRecord()); //����
			pstmt.setString(2, dto.getStnum()); //�л���
			pstmt.setString(3, dto.getLecturecode()); //�����ڵ�
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}
	}
}





















