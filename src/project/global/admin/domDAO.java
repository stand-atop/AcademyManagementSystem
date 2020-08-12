package project.global.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class domDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection getConnection(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@nullmaster.iptime.org:1521:orcl";
			String user = "class09" , password = "class09";
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	//�Խ� ��û �л� ��ȸ
	public List<domCheckDTO> getCheck() {
		List<domCheckDTO> list = null;
		try {
			conn = getConnection();
			//�Խ� ��û�� �л�  select
			pstmt = conn.prepareStatement("select * from enter where allow=0");
			rs = pstmt.executeQuery();
			 
			list = new ArrayList<domCheckDTO>();//����Ʈ ��ü ����
			while(rs.next()) {
				domCheckDTO dto = new domCheckDTO();
				dto.setNo(rs.getInt("no")); 
				dto.setStnum(rs.getString("stnum"));
				dto.setGender(rs.getString("gender"));
				dto.setDreamroom(rs.getString("dreamroom"));
				dto.setName(rs.getString("name"));
				dto.setPart(rs.getString("part"));
				list.add(dto);//list�� dto add
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch(SQLException s) {}
			if(pstmt != null)try {pstmt.close();}catch(SQLException s) {}
			if(conn != null)try {conn.close();}catch(SQLException s) {}
		}
		return list;
	}
	//����� �հ��� ��ȸ 
	public List<domCheckDTO> getAllowCheck() {
		List<domCheckDTO> list = null;
		int dballow = 0;
		String sql="";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from enter");
			rs = pstmt.executeQuery();
			
			list = new ArrayList<domCheckDTO>();//ArrayList ��ü ����
			while(rs.next()) {
				domCheckDTO dto = new domCheckDTO(); //domCheckDTO() ��ü ����
				dballow = rs.getInt("allow");
				if(dballow == 1) { //allow=1 ���ε� �л��� dto ��ü�� ���� 
				dto.setNo(rs.getInt("no")); 
				dto.setStnum(rs.getString("stnum"));
				dto.setDreamroom(rs.getString("dreamroom"));
				dto.setName(rs.getString("name"));
				dto.setPart(rs.getString("part"));
				dto.setHo(rs.getString("ho"));
				list.add(dto); //list�� ����
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch(SQLException s) {}
			if(pstmt != null)try {pstmt.close();}catch(SQLException s) {}
			if(conn != null)try {conn.close();}catch(SQLException s) {}
		}
		return list;
	}
	//����� �Խ� ��û ���� 
	public void updateAllow(String stnum) throws Exception{
		String dbstnum = "";
		String sql = "";
		
		try {
			conn = getConnection();
			//�ش� �й��� �л� ������ select
			pstmt = conn.prepareStatement("select * from enter where stnum=?");
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbstnum = rs.getString("stnum");
				if(dbstnum.equals(stnum)) {
					//���� ��ư Ŭ�� �� allow=1�� ������Ʈ
					sql = "update enter set allow=1 where stnum=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, stnum);
					pstmt.executeUpdate();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
		//����� �Խ� ��û �ź�
		public void updateAllowNo(String stnum) throws Exception{
			String dbstnum = "";
			String sql = "";
			
			try {
				conn = getConnection();
				//�ش� �й��� �л� ������ select
				pstmt = conn.prepareStatement("select * from enter where stnum=?");
				pstmt.setString(1, stnum);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dbstnum = rs.getString("stnum");
					if(dbstnum.equals(stnum)) {
						//���� ��ư Ŭ�� �� allow=1�� ������Ʈ
						sql = "update enter set allow=-1 where stnum=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, stnum);
						pstmt.executeUpdate();
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		}		
	
		
	//����� �� ���� �ʿ��� �л� ����Ʈ
	public List<roomDTO> getPass(){
		List<roomDTO> list = null;
		int dballow=0;
		try {
			conn = getConnection();
			//������� �л� ��ȸ
			pstmt = conn.prepareStatement("select * from enter where ok=0 order by dreamroom");
			rs = pstmt.executeQuery();
			
			list = new ArrayList<roomDTO>();
			while(rs.next()) {
				roomDTO dto = new roomDTO();
				dballow = rs.getInt("allow");
				if(dballow==1) {//�Խ� ���ε� �л��� roomDTO ��ü�� ����
					dto.setStnum(rs.getString("stnum"));
					dto.setGender(rs.getString("gender"));
					dto.setDreamroom(rs.getString("dreamroom"));
					dto.setName(rs.getString("name"));
					dto.setPart(rs.getString("part"));
					list.add(dto);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return list;
	}
	//����� �� ���� ����
	public void updateRoom(roomDTO dto) {
		String dbstnum="";
		String sql="";
		try {
			conn = getConnection();
			//�Խ� ���ε� �ش� �й��� �л� ������ select
			pstmt = conn.prepareStatement("select * from enter where stnum=?");
			pstmt.setString(1, dto.getStnum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dbstnum = rs.getString("stnum");
				if(dbstnum.equals(dto.getStnum())){	
					//����� �Ϸ�� �л� ȣ��Ŀ��, ok=1�� ������Ʈ
					sql = "update enter set ho=?, ok=1 where stnum=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, dto.getHo()); 
					pstmt.setString(2, dto.getStnum());
					pstmt.executeUpdate();	
				} 
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	//����� �� ���� ���� �� �ι�° ���̺� 
	//����� �� ���� ��ȸ
		public List<roomDTO> updateGetRoom(){
			List<roomDTO> list = null;
			int dbok=0;
			try {
				conn = getConnection();
				//����� �Ϸ�� �л� select
				pstmt = conn.prepareStatement("select * from enter where ok=1 order by dreamroom ");
				rs = pstmt.executeQuery();
				
				list = new ArrayList<roomDTO>(); //ArrayList ��ü ����
				while(rs.next()) {
					roomDTO dto = new roomDTO(); //roomDTO ��ü ����
					dbok = rs.getInt("ok");//ok�÷� �� ����
					if(dbok==1) { //ok �÷� ���� 1 �� ���(�� ���� �Ϸ�� �л�) dto�� ����
						dto.setStnum(rs.getString("stnum"));
						dto.setGender(rs.getString("gender"));
						dto.setDreamroom(rs.getString("dreamroom"));
						dto.setName(rs.getString("name"));
						dto.setPart(rs.getString("part"));
						dto.setHo(rs.getString("ho"));
						list.add(dto); //list ��ü�� ����
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return list;
		}
	//����� ��� ��û �л� ��ȸ 
	public List<domoutDTO> getOut(){
		List<domoutDTO> list = null;
		try {
			conn = getConnection();
			//��� ��û �л� select
			pstmt = conn.prepareStatement("select * from leaving where allow=0");
			rs = pstmt.executeQuery();
			list = new ArrayList<domoutDTO>(); 
			while(rs.next()) {
				domoutDTO dto = new domoutDTO(); //domoutDTO ��ü �����Ͽ� ������ ����
				dto.setNo(rs.getInt("no"));
				dto.setStnum(rs.getString("stnum"));
				dto.setDormitory(rs.getString("dormitory"));
				dto.setReason(rs.getString("reason"));
				dto.setDday(rs.getString("dday"));
				dto.setPart(rs.getString("part"));
				dto.setHo(rs.getString("ho"));
				dto.setName(rs.getString("name"));
				list.add(dto); //list ��ü�� dto ����
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return list;
	}
	//����� ��� ��û ���� 
	public void updateOutAllow(String stnum) throws Exception{
		String dbstnum = "";
		String sql = "";

		try {
			conn = getConnection();
			//��� ��û�� �л� �й� select
			pstmt = conn.prepareStatement("select * from leaving where stnum=?");
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbstnum = rs.getString("stnum");
				if(dbstnum.equals(stnum)) {//���� ��ư Ŭ�� �� allow=1 �� ������Ʈ
					sql = "update leaving set allow=1 where stnum=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, stnum);
					pstmt.executeUpdate();
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	//�ܹ� ��û ��ȸ
	public List<nightDTO> getNignt(){	
		List<nightDTO> list = null;
		try {
			conn = getConnection();
			//�ܹ� ��û�� �л� select
			pstmt = conn.prepareStatement("select * from sexnight where allow=0");
			rs = pstmt.executeQuery();
			list = new ArrayList<nightDTO>();
			while(rs.next()) {
				nightDTO dto = new nightDTO(); //nightDTO ��ü ����
				dto.setNo(rs.getInt("no"));
				dto.setStnum(rs.getString("stnum"));
				dto.setAllow(rs.getInt("allow"));
				dto.setName(rs.getString("name"));
				dto.setDreamroom(rs.getString("dreamroom"));
				dto.setHo(rs.getString("ho"));
				dto.setPart(rs.getString("part"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setStartday(rs.getString("startday"));
				dto.setComeback(rs.getString("comeback"));
				list.add(dto); //list ��ü�� dto add
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return list;
	}
	//�ܹ� ��û ����/�ź� Ȯ�� ���̺�
		public List<nightDTO> getAllNignt(){	
			List<nightDTO> list = null;
			try {
				conn = getConnection();
				//�ܹ� ����/�źε� �л� ������ select
				pstmt = conn.prepareStatement("select * from sexnight where allow=-1 or allow=1");
				rs = pstmt.executeQuery();
				list = new ArrayList<nightDTO>();//ArrayList ��ü ����
				while(rs.next()) {
					nightDTO dto = new nightDTO(); //nightDTO ��ü ����
					dto.setNo(rs.getInt("no"));
					dto.setStnum(rs.getString("stnum"));
					dto.setAllow(rs.getInt("allow"));
					dto.setName(rs.getString("name"));
					dto.setDreamroom(rs.getString("dreamroom"));
					dto.setHo(rs.getString("ho"));
					dto.setPart(rs.getString("part"));
					dto.setReg_date(rs.getTimestamp("reg_date"));
					list.add(dto); //list ��ü�� dto add
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return list;
		}
	//�ܹ� ����
	public void sleepoutAllow(String stnum, int no) throws Exception {
		//String dbstnum = "";
		String sql = "";
		
		
		try {
			conn = getConnection();
			//�ܹ� ��û�� �л� ������ select
			pstmt = conn.prepareStatement("select * from sexnight");
			rs = pstmt.executeQuery();
			if(rs.next()) {
					//�ش� ������ ��ȣ, �й� ��ġ�� ����Ʈ �ܹ� ���ε� �л� �÷� allow=1�� ������Ʈ
					sql = "update sexnight set allow=1 where stnum=? and no=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, stnum);
					pstmt.setInt(2, no);
					pstmt.executeUpdate();
				}		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
		//����� �ܹ� ���� �ź�
		public void sleepAllowNo(String stnum, int no) throws Exception{
			//String dbstnum = "";
			String sql = "";
				
			try {
				conn = getConnection();
				//�ܹ� ��û�� �л� ������ select
				pstmt = conn.prepareStatement("select * from sexnight where stnum=?");
				pstmt.setString(1, stnum);
				rs = pstmt.executeQuery();
				if(rs.next()) {
						//�ش� ������ ��ȣ, �й� ��ġ�� ����Ʈ �ܹ� �ź� ��ư Ŭ�� �� allow �÷� �� -1�� ������Ʈ	
						sql = "update sexnight set allow=-1 where stnum=? and no=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, stnum);
						pstmt.setInt(2, no);
						pstmt.executeUpdate();
					}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		}		
		
		
	
}
