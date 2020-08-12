package project.global.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class plan2DAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Connection getConnection() throws Exception{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@nullmaster.iptime.org:1521:orcl";
			String user = "class09", pass="class09";
			conn = DriverManager.getConnection(url, user, pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	//�а��� �����ϱ� ���� ����Ʈ�� ��ȸ��
	public List<plan2DTO> lecture(){
		List<plan2DTO> lecturelist = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from subject"); //�а����̺�
			rs = pstmt.executeQuery();
			lecturelist = new ArrayList<plan2DTO>();
			while(rs.next()) { //�а��� �ݺ�
				plan2DTO lec = new plan2DTO();
				lec.setClassname(rs.getString("subname")); //�а���
				lecturelist.add(lec);
			}
		}catch(Exception e) { //����ó��
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
			if(conn != null) try {conn.close();}catch(SQLException s) {}
		}
		return lecturelist; //�� ����
	}
	
	
	public List<plan2DTO> allListSelect(){ //��ü ���� ����Ʈ�� ������
		List<plan2DTO> alllist = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from class1 where allow=1");
			rs = pstmt.executeQuery();
			alllist = new ArrayList<plan2DTO>();
			while(rs.next()) {
				plan2DTO dto = new plan2DTO();
				dto.setClassnum(rs.getString("classnum"));
				dto.setPart(rs.getString("part"));
				dto.setClassname(rs.getString("classname"));
				dto.setHomeroompro(rs.getString("homeroompro"));
				dto.setScore(rs.getInt("score"));
				dto.setBuilding(rs.getString("building"));
				dto.setRoomnum(rs.getString("roomnum"));
				dto.setDay(rs.getString("day"));
				dto.setTime(rs.getInt("time"));
				dto.setCan(rs.getInt("can"));
				alllist.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}		
		return alllist;
	}
	
	//�˻� ���� ����Ʈ ��ȸ - �⵵, �б�, �а�, ���Ǹ�
		public List<plan2DTO> planListSelect(String date1, String semester, String part, String classname){
			List<plan2DTO> planlist = null; //��ȯ�� DTO
			String sql = ""; //SQL
			String name = ""; //���Ǹ��� ���� �ӽú���
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from class1 where date1=? and semester=? and part=? and allow=1"); //�⵵,�б�,�а��� ���� ��� ���
				pstmt.setString(1, date1);
				pstmt.setString(2, semester);
				pstmt.setString(3, part);	
				rs = pstmt.executeQuery();
				
				while(rs.next()) { //��������� classname�� �ݺ����� ����
					name = rs.getString("classname");
					if(name.equalsIgnoreCase(classname)) { //�Է¹��� ���Ǹ�� ��ҹ��ڸ� �������� �ʰ� ���� ������ �ݺ����� �����
						break;
					}
				}
				
				if(name.equalsIgnoreCase(classname)) { //���� �Է¹��� ���� ���� ���Ǹ��� �ִٸ�
					sql = "select * from class1 where date1=? and semester=? and part=? and classname=? and allow=1"; //����� name���� �ٽ� �˻�
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, date1);
					pstmt.setString(2, semester);
					pstmt.setString(3, part);
					pstmt.setString(4, name);
					rs = pstmt.executeQuery();
					
					planlist = new ArrayList<plan2DTO>();
					while(rs.next()) {
						plan2DTO dto = new plan2DTO();
						dto.setClassnum(rs.getString("classnum")); //�����ڵ�
						dto.setPart(rs.getString("part")); //�а�
						dto.setClassname(rs.getString("classname")); //���Ǹ�
						dto.setHomeroompro(rs.getString("homeroompro")); //������
						dto.setScore(rs.getInt("score")); //����
						dto.setBuilding(rs.getString("building")); //�ǹ�
						dto.setRoomnum(rs.getString("roomnum")); //���ǽ�
						dto.setDay(rs.getString("day")); //����
						dto.setTime(rs.getInt("time")); //����
						dto.setCan(rs.getInt("can")); //����
						planlist.add(dto);
					}
					
				}else if(classname==null || classname==""|| name!=classname) { //classname�� ���ų�, �����̰ų�, ���Ǹ�� �ٸ� ���, �⵵,�б�,�а��� ������ �˻�
					sql = "select * from class1 where date1=? and semester=? and part=? and allow=1";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, date1);
					pstmt.setString(2, semester);
					pstmt.setString(3, part);
					
					rs = pstmt.executeQuery();
					planlist = new ArrayList<plan2DTO>();
					while(rs.next()) {
						plan2DTO dto = new plan2DTO();
						dto.setClassnum(rs.getString("classnum")); //�����ڵ�
						dto.setPart(rs.getString("part")); //�а�
						dto.setClassname(rs.getString("classname")); //���Ǹ�
						dto.setHomeroompro(rs.getString("homeroompro")); //������
						dto.setScore(rs.getInt("score")); //����
						dto.setBuilding(rs.getString("building")); //�ǹ�
						dto.setRoomnum(rs.getString("roomnum")); //���ǽ�
						dto.setDay(rs.getString("day")); //����
						dto.setTime(rs.getInt("time")); //����
						dto.setCan(rs.getInt("can")); //����
						planlist.add(dto);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();} catch(SQLException s) {}
				if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
				if(conn != null) try {conn.close();} catch(SQLException s) {}
			}		
			return planlist;
		}



	
	
	
}
