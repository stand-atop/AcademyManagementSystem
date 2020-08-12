package project.global.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class evalDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Connection getConnection() throws Exception{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@nullmaster.iptime.org:1521:orcl";
			String user = "class09", pass = "class09";
			conn = DriverManager.getConnection(url, user, pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<evalDTO> lectureList(String stnum, String date1, String semester){ //������û ���̺��� ������� �ҷ���
		List<evalDTO> list = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from signup where stnum=? and date1=? and semester=?");//�й�, �⵵, �бⰡ �´°�?
			pstmt.setString(1, stnum);
			pstmt.setString(2, date1);
			pstmt.setString(3, semester);
			rs = pstmt.executeQuery();
			list = new ArrayList<evalDTO>();
			while(rs.next()) {
				evalDTO lecture = new evalDTO();
				lecture.setClassname(rs.getString("classname")); //���Ǹ�
				lecture.setProname(rs.getString("homeroompro")); //������
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
	
	public evalDTO lectureSelect(String stnum, String date1, String semester){ //������û ���̺��� ����
		evalDTO select = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from signup where stnum=? and date1=? and semester=?");//�й�, �⵵, �бⰡ �´°�?
			pstmt.setString(1, stnum); //�й�
			pstmt.setString(2, date1); //�⵵
			pstmt.setString(3, semester); //�б�
			rs = pstmt.executeQuery();
			select = new evalDTO();
			if(rs.next()) {
				select.setProname(rs.getString("homeroompro")); //������
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(rs != null) try {rs.close();} catch(SQLException s) {}
		}
		return select;
	}
	
	
	public void evalInsert(evalDTO eval) {	//������ ���̺� �� �����ϱ�
		String lecture = eval.getClassname(); //�а� ����
		String stnum = eval.getStnum(); //�й� ����
		String sql = "";
		evalDTO choose = null; //�������� ���� DTO
		int score = 0;	//������ ����� ����
		int [] num = new int[10];
			num[0] = eval.getNum1();
			num[1] = eval.getNum2();
			num[2] = eval.getNum3();
			num[3] = eval.getNum4();
			num[4] = eval.getNum5();
			num[5] = eval.getNum6();
			num[6] = eval.getNum7();
			num[7] = eval.getNum8();
			num[8] = eval.getNum9();
			num[9] = eval.getNum10();
			
		for(int i = 0; i < 10; i++) {				
			score += num[i];
		}		

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from signup  where stnum=? and classname=?"); //�������� ���ǿ� �°� ã�´�.
			pstmt.setString(1, stnum);
			pstmt.setString(2, lecture);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				choose = new evalDTO();
				choose.setProname(rs.getString("homeroompro"));
			}
		
			sql = "insert into evaluation values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eval.getDate1()); //�⵵
			pstmt.setString(2, eval.getSemester()); //�б�
			pstmt.setString(3, eval.getClassname()); //���Ǹ�
			pstmt.setString(4, choose.getProname()); //ã�Ƴ� �������� �����ؼ� �ִ´�
			pstmt.setInt(5, eval.getNum1()); //�׸�
			pstmt.setInt(6, eval.getNum2());
			pstmt.setInt(7, eval.getNum3());
			pstmt.setInt(8, eval.getNum4());
			pstmt.setInt(9, eval.getNum5());
			pstmt.setInt(10, eval.getNum6());
			pstmt.setInt(11, eval.getNum7());
			pstmt.setInt(12, eval.getNum8());
			pstmt.setInt(13, eval.getNum9());
			pstmt.setInt(14, eval.getNum10());
			pstmt.setString(15, eval.getTopro()); //�����Բ�
			pstmt.setString(16, eval.getStnum()); //�й�
			pstmt.setInt(17, score); //����
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}
		
	}	
		
	//���� �򰡽� �ι� ������ �� ����.
	public int submitCheck(String stnum, String classname) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int check =  -1; //���� üũ�� �ϱ� ���� ����
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select stnum, classname from evaluation where stnum=? and classname=?");
			pstmt.setString(1, stnum);
			pstmt.setString(2, classname);
			rs = pstmt.executeQuery();
				
			if(rs.next()) { //�˻� ����� ������ 
				check = 1;					
			}else {	//�˻� ����� ������
				check  = -1;
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
