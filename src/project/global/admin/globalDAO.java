package project.global.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class globalDAO {
	private static globalDAO instance = new globalDAO();

	public static globalDAO getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private Connection getConnection() { 
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@nullmaster.iptime.org:1521:orcl";
			String user = "class09", pass = "class09";
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn; 
	}
	//what-���� : �л� 1, ���� 2, ������ 3 ���� �����Ͽ� �α���
	public int globalCheck(String id, String pw)
			throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpw = ""; //�α��� ��й�ȣ �ʱ�ȭ
		String dbwhat = ""; //�α��� �ź� �ʱ�ȭ
		int x = 0;

		try {
			conn = getConnection();
			//�α��� �� student ���̺� �ð� ������Ʈ
			pstmt = conn.prepareStatement("update student set acsday=sysdate where stnum=? and pw=?");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
			//�й��� ��й�ȣ ��ġ�� ��츸 �α���
			pstmt = conn.prepareStatement("select * from global where id = ? and pw=?");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();
			//��ġ�� �й�, ��й�ȣ�� �ִ� Ʃ������ ��
			if (rs.next()) {
				//global ���̺��� what �÷����� ���� dbwhat�� ����
				dbwhat = rs.getString("what");
				if (dbwhat.equals("1")) { //�л�(1)
					x = 1; 
				} else if (dbwhat.equals("2")) { //����(2)
					x = 2;
				} else {
					x = 3; //������(3)
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally { 
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; //�ź� ���а� ����
	}
	

	//��й�ȣ ã�� 
	public void getPw(pwDTO dto) {
		//String sql="";
		try {
			conn = getConnection();
			//student ���̺��� �̸�, �й�, �а�, �̸��� ��ġ Ȯ��
			pstmt = conn.prepareStatement("select * from student where stname=? and stnum=? and part=? and email=?");
			pstmt.setString(1, dto.getStname());
			pstmt.setString(2, dto.getStnum());
			pstmt.setString(3, dto.getPart());
			pstmt.setString(5, dto.getEmail());
			rs = pstmt.executeQuery();
			//��ġ�� ���� �ִٸ� pwDTO�� ����
			if(rs.next()) {
				dto.setStname(rs.getString("stname"));
				dto.setStnum(rs.getString("stnum"));
				dto.setPart(rs.getString("part"));
				dto.setPw(rs.getString("pw"));
				dto.setEmail(rs.getString("email"));
			}		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	//�̸��Ϸ� �ӽ� ��й�ȣ ���� �� �ӽ� ��й�ȣ�� ������Ʈ
	public void updatePw(String pw, String id) {
		try {
			conn = getConnection();
			//�ӽ� ��й�ȣ ���� �л��� student ���̺��� ��й�ȣ ������Ʈ
			pstmt = conn.prepareStatement("update student set pw=? where stnum=?");
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
			System.out.println(pw);
			System.out.println(id);
			//�α��ο� ���� global ���̺� ��й�ȣ ������Ʈ
			pstmt = conn.prepareStatement("update global set pw=? where id=?");
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	//����  ��й�ȣ ã�� 
	public void getProPw(pwProDTO dto) {
		try {
			conn = getConnection();
			//���� ���̺��� �̸�, �а� ,�̸��� ,�й� ��ġ�� ���
			pstmt = conn.prepareStatement("select * from professor where proname=? and propart=? and proemail=? and id=?");
			pstmt.setString(1, dto.getProname());
			pstmt.setString(2, dto.getPropart());
			pstmt.setString(3, dto.getProemail());
			pstmt.setString(4, dto.getId());
			rs = pstmt.executeQuery();
			//��ġ�� �����Ͱ� �ִٸ� pwProDTO �� ����
			if(rs.next()) {
				dto.setProname(rs.getString("proname"));
				dto.setPropart(rs.getString("propart"));
				dto.setProemail(rs.getString("proemail"));
				dto.setPw(rs.getString("pw"));
				dto.setId(rs.getString("id"));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	//���� �ӽú�й�ȣ�� ������Ʈ
	public void updatepwPro(String pw, String id) {
		try {
			conn = getConnection();
			//���� ���̺��� �й� ��ġ�� ���� ��й�ȣ ������Ʈ
			pstmt = conn.prepareStatement("update professor set pw=? where id=?");
			pstmt.setString(1, pw);
			pstmt.setString(2,id);
			pstmt.executeUpdate();
			//�α��ο� ���� global ���̺� ��й�ȣ ������Ʈ
			pstmt = conn.prepareStatement("update global set pw=? where id=?");
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
}
