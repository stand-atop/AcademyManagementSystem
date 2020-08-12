package project.global.professor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class plan3DAO {
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
		
	//���ǰ�ȹ�� ��ȸ
	public plan3DTO plan(String classnum){ //�ϴ� ���� �ڵ�θ� �и�
		plan3DTO plan = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from class1 where classnum =? and allow=1");
			pstmt.setString(1, classnum);
			rs = pstmt.executeQuery();
			plan = new plan3DTO();
			if(rs.next()) {
				plan.setClassnum(rs.getString("classnum")); //�����ڵ�
				plan.setPart(rs.getString("part")); //�а�
				plan.setClassname(rs.getString("classname")); //���Ǹ�
				plan.setHomeroompro(rs.getString("homeroompro")); //������
				plan.setScore(rs.getInt("score")); //����
				plan.setBuilding(rs.getString("building")); //�ǹ���
				plan.setRoomnum(rs.getString("roomnum")); //���ǽ�
				plan.setDay(rs.getString("day")); //����
				plan.setTime(rs.getInt("time")); //����
				plan.setCan(rs.getInt("can")); //�ο�
				plan.setWho(rs.getString("who"));//�������
				plan.setGoal(rs.getString("goal"));//���Ǹ�ǥ
				plan.setProemail(rs.getString("proemail"));//�����̸���
				plan.setDate1(rs.getString("date1")); //�����⵵
				plan.setSemester(rs.getString("semester"));//�б�
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}		
		return plan;
	}
	//���ǰ�ȹ�� ���� - �����ڵ�� ����id�� ���� ��츸 ����
	public void planModify(plan3DTO modify) {
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update class1 set building=?, roomnum=?, day=?, time=?, can=?, part=?, who=?, goal=? where allow=1 and classnum=? and id=?");
			pstmt.setString(1, modify.getBuilding());
			pstmt.setString(2, modify.getRoomnum());
			pstmt.setString(3,  modify.getDay());
			pstmt.setInt(4, modify.getTime());
			pstmt.setInt(5, modify.getCan());
			pstmt.setString(6, modify.getPart());
			pstmt.setString(7, modify.getWho());
			pstmt.setString(8, modify.getGoal());
			pstmt.setString(9, modify.getClassnum());
			pstmt.setString(10, modify.getId());
			
			pstmt.executeUpdate();


		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}
	}
	
}
