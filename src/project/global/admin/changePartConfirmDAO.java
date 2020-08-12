package project.global.admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import project.global.admin.changePartConfirmDTO;

import java.util.ArrayList;

public class changePartConfirmDAO {
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		
		private Connection getConnection() throws Exception{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@nullmaster.iptime.org:1521:orcl";
				String user = "class09", pass = "class09";
				conn = DriverManager.getConnection(url, user,pass);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		public List<changePartConfirmDTO> changeConfirmList(){
			List<changePartConfirmDTO> list = null;
			int x = 1;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select * from (select appdate,date1,semester,stnum,stname,bfname,afname,couse,"
						+ "proapp,adapp,finalapp from(select * from subjectchange order by appdate desc, stname asc)"
						+ "order by appdate desc, stname asc) where proapp=? and finalapp=0");
				pstmt.setInt(1, x);
				rs = pstmt.executeQuery();
				list = new ArrayList<changePartConfirmDTO>();
				while(rs.next()) {
					changePartConfirmDTO dto = new changePartConfirmDTO();
					dto.setAppdate(rs.getString("appdate")); //��������
					dto.setDate1(rs.getString("date1"));//�⵵
					dto.setSemester(rs.getString("semester"));//�б�
					dto.setStnum(rs.getString("stnum"));//�й�
					dto.setStname(rs.getString("stname"));//�̸�
					dto.setBfname(rs.getString("bfname"));//�����а�
					dto.setAfname(rs.getString("afname"));//��û�а�
					dto.setCouse(rs.getString("couse"));//������û����
					dto.setProapp(rs.getInt("proapp"));//1������
					dto.setAdapp(rs.getInt("adapp"));//2������
					dto.setFinalapp(rs.getInt("finalapp"));//��������
					list.add(dto);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();} catch(SQLException s) {}
				if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
				if(conn != null) try {conn.close();} catch(SQLException s) {}
			}
			return list;		
		}//������û ����Ʈ ��
		
		//������û ������ ����
		public void changePart(String stnum, String stname, String bfname, String afname, int proapp, int data) {//�й�, �̸�, �����а�, �����а�, proapp ������
			String sql="";
			//int x = 1; //adapp�� finalapp ���ο� ����� ����
			try {
				conn = getConnection();
				sql = "select * from subjectchange where stnum=? and proapp=?"; //�������̺��� �й��� �°�, ������ ������ ��츸
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, stnum); //������ �й��� ������ 
				pstmt.setInt(2, proapp); //2���������� proapp�� 1�� ��츸
				rs = pstmt.executeQuery();
				if(rs.next()) {
					sql = "update subjectchange set adapp=?, finalapp=? where stnum=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, data); //adapp ����
					pstmt.setInt(2, data); //finalapp ����
					pstmt.setString(3, stnum);
					pstmt.executeUpdate();
				}
				
				sql = "select * from student where stnum=? and stname=? and part=?"; //�л����̺��� �й��� �̸��� ���� ��츸
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, stnum); //�й�
				pstmt.setString(2, stname); //�̸�
				pstmt.setString(3, bfname); //�����а�
				rs = pstmt.executeQuery();
				if(rs.next()) {
					sql = "update student set part=? where stnum=?"; //�л����̺��� �л��� �а�����
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, afname); //�а�����
					pstmt.setString(2, stnum);
					pstmt.executeUpdate();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();} catch(SQLException s) {}
				if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
				if(conn != null) try {conn.close();} catch(SQLException s) {}
			}
		}//������û ������ ���� ��
		
		//������û ������ ����
			public void changePart(String stnum, int proapp, int data) {//�й�, proapp�� ������
				String sql="";
				//int y = 2; //adapp�� finalapp ������ ����� ����
				
				try {
					conn = getConnection();
					sql = "select * from subjectchange where stnum=? and proapp=?"; //�������̺��� �й��� �°�, ������ ������ ��츸
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, stnum); //������ �й��� ������ 
					pstmt.setInt(2, proapp); //2���������� proapp�� 1�� ��츸
					rs = pstmt.executeQuery();
					if(rs.next()) {
						sql = "update subjectchange set adapp=?, finalapp=? where stnum=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, data); //adapp ����
						pstmt.setInt(2, data); //finalapp ����
						pstmt.setString(3, stnum);
						pstmt.executeUpdate();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(rs != null) try {rs.close();} catch(SQLException s) {}
					if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
					if(conn != null) try {conn.close();} catch(SQLException s) {}
				}
			}//������û ������ ���� ��

}//Ŭ���� ��
