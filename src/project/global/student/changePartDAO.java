package project.global.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class changePartDAO {
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
		
		//�л����̺��� �л��� ������ �����´�
		public changePartDTO stinfo(String stnum) {
			changePartDTO info = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from student where stnum=?");
				pstmt.setString(1, stnum); //�й� �������� �л� ������ ������
				rs = pstmt.executeQuery();
				info = new changePartDTO();
				if(rs.next()) {
					info.setStnum(rs.getString("stnum"));//�й�
					info.setStname(rs.getString("stname"));//�̸�
					info.setGrade(rs.getString("grade"));//�г�
					info.setBfname(rs.getString("part"));//�а�
					info.setStphone(rs.getString("phone"));//�޴�Ǧ
					info.setStemail(rs.getString("email"));//�̸���
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();} catch(SQLException s) {}
				if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
				if(conn != null) try {conn.close();} catch(SQLException s) {}
			}
			return info;
		}//�л����� ��
		
		//�а����̺��� �а�����Ʈ�� �����´�.
		public List<changePartDTO> subjectList(){
			List<changePartDTO> sublist = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from subject");
				rs = pstmt.executeQuery();
				sublist = new ArrayList<changePartDTO>();
				while(rs.next()) {
					changePartDTO dto = new changePartDTO();
					dto.setAfnum(rs.getString("subnum")); //�а��ڵ�
					dto.setAfname(rs.getString("subname")); //�а���
					sublist.add(dto);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();} catch(SQLException s) {}
				if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
				if(conn != null) try {conn.close();} catch(SQLException s) {}
			}
			return sublist;
		}//�а� ����Ʈ ��
		
		//������û Ȯ�� ����Ʈ
		public List<changePartDTO> changeCheckList(String stnum){
			List<changePartDTO> changelist = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from subjectchange where stnum=?");
				pstmt.setString(1, stnum);
				rs = pstmt.executeQuery();
				changelist = new ArrayList<changePartDTO>();
				while(rs.next()) {
					changePartDTO dto = new changePartDTO();
					dto.setAppdate(rs.getString("appdate")); //��������
					dto.setDate1(rs.getString("date1"));//�⵵
					dto.setSemester(rs.getString("semester"));//�б�
					dto.setStnum(rs.getString("stnum"));//�й�
					dto.setStname(rs.getString("stname"));//�̸�
					dto.setBfname(rs.getString("bfname"));//�����а�
					dto.setAfname(rs.getString("afname"));//��û�а�
					dto.setProapp(rs.getInt("proapp"));//1������
					dto.setAdapp(rs.getInt("adapp"));//2������
					dto.setFinalapp(rs.getInt("finalapp"));//��������
					changelist.add(dto);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();} catch(SQLException s) {}
				if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
				if(conn != null) try {conn.close();} catch(SQLException s) {}
			}
			return changelist;		
		}//������û ����Ʈ ��
		
		//������û ���̺� ���� ���� ����
		public void subjectInsert(changePartDTO in) {
			String bfname = in.getBfname();//�����а� ����
			String afname = in.getAfname();//�����а� ����
			String sql = "";
			changePartDTO bfcode = null; //�����а��ڵ��ȣ�� ���� DTO			
			changePartDTO afcode = null; //�����а��ڵ��ȣ�� ���� DTO
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from subject where subname=?");//�а����̺�
				pstmt.setString(1, bfname); //�����а� ����
				rs = pstmt.executeQuery();
				if(rs.next()) {
					bfcode = new changePartDTO();
					bfcode.setBfnum(rs.getString("subnum"));//�����а� �ڵ带 ����
				}

				sql = "select * from subject where subname=?";//�����а��� �����а� �ڵ带 ã�´�.
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, afname); //�����а�����
				rs = pstmt.executeQuery();
				afcode = new changePartDTO();
				if(rs.next()) {
					afcode.setAfnum(rs.getString("subnum")); //�����а� �ڵ带 ����
				}

				sql = "insert into subjectchange(stnum,bfnum,bfname,afnum,afname,proapp,adapp,appdate,date1,semester,stname,couse,stphone,stemail,finalapp) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, in.getStnum());//�й�
				pstmt.setString(2, bfcode.getBfnum());//�����а��ڵ�
				pstmt.setString(3, in.getBfname());//�����а���
				pstmt.setString(4, afcode.getAfnum());//�����а��ڵ�
				pstmt.setString(5, in.getAfname());//�����а���
				pstmt.setInt(6, in.getProapp());//1����������
				pstmt.setInt(7, in.getAdapp());//2�������ڽ���
				pstmt.setString(8, in.getAppdate());//��û����
				pstmt.setString(9, in.getDate1());//�⵵
				pstmt.setString(10, in.getSemester());//�б�
				pstmt.setString(11, in.getStname());//�̸�
				pstmt.setString(12, in.getCouse());//������û����
				pstmt.setString(13, in.getStphone());//�л���ȭ��ȣ
				pstmt.setString(14, in.getStemail());//�л��̸���
				pstmt.setInt(15, in.getFinalapp());//��������
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
				if(conn != null) try {conn.close();} catch(SQLException s) {}
			}
		}	//������û ���̺� ���� ���� ���� ��
		
		
		//������û�� 1���� �����ϵ��� üũ��
		public int changeCheck(String stnum) //�й��� ������������ Ȯ��
		throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int fin=0;
			int pro=0;
			int check = -1; //����� ��ȯ�ϱ� ���� ������ ����
			
			try {
				conn = getConnection(); 
				pstmt = conn.prepareStatement("select stnum, finalapp, proapp from subjectchange where stnum=?");
				pstmt.setString(1, stnum); //�й����� ��ȸ
				rs = pstmt.executeQuery();

				if(rs.next()) {
					fin = rs.getInt("finalapp");					
					pro = rs.getInt("proapp");
				
					if(fin == 1) { //���� ������ ������ ����
						check = 1;
					}else if(fin == 2) { //���� ������ ������ ���û
						check = 2;
					}else if(fin == 1 && pro == 0) { //�������� ���̸� ������
						check = 0; 
					}else if(fin == 0 && pro == 0) { //������ ���� ��
						check = 3;
					} 
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();} catch(SQLException s) {}
				if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
				if(conn != null) try {conn.close();} catch(SQLException s) {}
			}			
			return check;
		}
		
}
