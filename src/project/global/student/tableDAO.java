package project.global.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tableDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Connection getConnection() throws Exception{ //DB�� �����ϱ�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@nullmaster.iptime.org:1521:orcl";
			String user = "class09", pass = "class09";
			conn = DriverManager.getConnection(url,user,pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<tableDTO> myList(String stnum, String date1, String semester){ //�й�, �⵵, �бⰡ ������ ���� ����Ʈ�� �ҷ���
		List<tableDTO> list = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from signup where stnum=? and date1 =? and semester=?");//�й�, �⵵, �бⰡ �´°�?
			pstmt.setString(1, stnum);
			pstmt.setString(2, date1);
			pstmt.setString(3, semester);
			rs = pstmt.executeQuery();
			list = new ArrayList<tableDTO>();
			while(rs.next()) {
				tableDTO mylistdto = new tableDTO();
				mylistdto.setClassname(rs.getString("classname")); //���Ǹ�
				mylistdto.setHomeroompro(rs.getString("homeroompro")); //��米��
				mylistdto.setScore(rs.getInt("score"));//����
				mylistdto.setClassroom(rs.getString("classroom")); //���ǽ�
				mylistdto.setClasstime(rs.getString("classtime")); //���ǽð�
				mylistdto.setDate1(rs.getString("date1")); //�⵵
				mylistdto.setSemester(rs.getString("semester")); //�б�
				mylistdto.setBuilding(rs.getString("building")); //�ǹ���
				mylistdto.setDay(rs.getString("day"));//����
				mylistdto.setClassnum(rs.getString("classnum"));//�����ڵ�
				mylistdto.setPart(rs.getString("part"));//�а�

				list.add(mylistdto);
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

}
