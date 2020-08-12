package project.global.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class appDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private Connection getConnection() throws Exception {
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

	public List<appDTO> appChecking(int num) { // ������ ���� ���̺��� ���Ǹ���Ʈ ��������
		List<appDTO> applist = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from class1 where allow= ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			applist = new ArrayList<appDTO>();

			while (rs.next()) {
				appDTO listdto = new appDTO();
				listdto.setBuilding(rs.getString("building")); // �ǹ���
				listdto.setRoomnum(rs.getString("roomnum")); // ���ǽ�
				listdto.setDay(rs.getString("day")); // ����
				listdto.setTime(rs.getInt("time"));// ���ǽð�
				listdto.setCan(rs.getString("can")); // �ο�
				listdto.setClassnum(rs.getString("classnum")); // �����ڵ�
				listdto.setClassname(rs.getString("classname")); // ���Ǹ�
				listdto.setHomeroompro(rs.getString("homeroompro")); // ��米��
				listdto.setPart(rs.getString("part")); // �а�
				listdto.setWho(rs.getString("who"));// �ο�
				listdto.setDate1(rs.getString("date1")); // �⵵
				listdto.setSemester(rs.getString("semester")); // �б�
				listdto.setScore(rs.getInt("score")); // ����
				applist.add(listdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException s) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException s) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException s) {
				}
		}
		return applist;
	}

	// ������û ���̺� �� �����ϱ�
	public void appInsert(appDTO apply, String stnum, String stname, String grade) {
		try {
			conn = getConnection();
			String sql = "insert into signup(part, classname, homeroompro, score, classroom,classtime,date1,semester,"
					+ "stnum,stname,building,day,classnum,grade) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, apply.getPart()); // �а�
			pstmt.setString(2, apply.getClassname()); // ���Ǹ�
			pstmt.setString(3, apply.getHomeroompro()); // ������
			pstmt.setInt(4, apply.getScore()); // ����
			pstmt.setString(5, apply.getRoomnum()); // ���ǽ�
			pstmt.setInt(6, apply.getTime()); // ����
			pstmt.setString(7, apply.getDate1()); // �⵵
			pstmt.setString(8, apply.getSemester()); // �б�
			pstmt.setString(9, stnum); // �й�
			pstmt.setString(10, stname); // �̸�
			pstmt.setString(11, apply.getBuilding()); // �ǹ���
			pstmt.setString(12, apply.getDay()); // ����
			pstmt.setString(13, apply.getClassnum()); // �����ڵ�
			pstmt.setString(14, grade);// �г�
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException s) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException s) {
				}
		}
	}

	public appDTO studentInfo(String stnum) { // ������û���� �л� ���� ��ȸ
		appDTO stuinfo = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from student where stnum=?"); // �й����� �ֱ�
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();
			stuinfo = new appDTO();
			if (rs.next()) {
				stuinfo.setStnum(rs.getString("stnum")); // �й�
				stuinfo.setStname(rs.getString("stname")); // �̸�
				stuinfo.setGrade(rs.getInt("grade") + ""); // �г�
				stuinfo.setPart(rs.getString("part")); //�а�
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException s) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException s) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException s) {
				}
		}
		return stuinfo;
	}

	 //������ ������ ������ ������
	public appDTO selectLecture(String classnum) { 
		appDTO select = null; 
		try { 
			conn = getConnection(); 
			pstmt = conn.prepareStatement("select * from class1 where classnum=? and allow=1");
			pstmt.setString(1, classnum); 
			rs = pstmt.executeQuery(); 
			select = new appDTO(); 
			if(rs.next()) { 
				select.setPart(rs.getString("part")); //�а�
				select.setClassname(rs.getString("classname")); //���Ǹ�
				select.setHomeroompro(rs.getString("homeroompro")); //������
				select.setScore(rs.getInt("score")); //�й�
				select.setRoomnum(rs.getString("roomnum")); //���ǽ�
				select.setTime(rs.getInt("time")); //�ð�
				select.setDate1(rs.getString("date1")); //�⵵
				select.setSemester(rs.getString("semester")); //�б�
				select.setBuilding(rs.getString("building")); //�ǹ�
				select.setDay(rs.getString("day")); //����
				select.setClassnum(rs.getString("classnum")); //�����ڵ� 
				select.setCan(rs.getInt("can")+"");//����
			}

		}catch(Exception e) { 
			e.printStackTrace(); 
		}finally { 
			if(rs != null) try {rs.close();} catch(SQLException s) {} 
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {} 
			if(conn != null) try {conn.close();} catch(SQLException s) {} } return select; 
		}

	// ������û�� ������ �̹� ������ ���� ���� üũ - signup���� �����ڵ�(classnum)�� ī��Ʈ�Ͽ� class1�� �ο�(can)��
	// ���� ������ ��
	// ������ ������ �л��� ���� �ٸ� ��� ��û�� �� ������ ������ ��
	public int canCheck(String stnum, String classnum) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		appDTO stu = studentInfo(stnum); //�л��� ������ ������
		appDTO lec = selectLecture(classnum); //������ ������ ������ ������

		String sql = "";
		int countclass = 0; // ������û�� ������ ���� ����
		int can = Integer.parseInt(lec.getCan()); // ���� ����
		int check = -1; // ��ȯ���� ����
		String stpart = stu.getPart(); // �л��� �а�
		String lecpart = lec.getPart(); //������ ������ �а�
		String status = ""; // �л��� �������¸� ���� ����

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from signup where classnum=?");// �����ڵ�� ������û�� ������ ��
			pstmt.setString(1, classnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				countclass = rs.getInt(1); // ������û ������ ����
			}
			//������ ������ ��û�� �� ����
			if(can < countclass) {
				check = 1;
			}
			// ������û���̺��� �й��� �����ڵ�� �̹� ��û�� �������� üũ��
			sql = "select * from signup where classnum=? and stnum=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classnum); // �����ڵ�
			pstmt.setString(2, stnum); // �й�
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = 2; // ���� ��� �ߺ���û���� ������û �ź�
			}
			//�ٸ��� ���Ǵ� ��û�� �� ����.
			if(!stpart.equals(lecpart)) {
				check = 3;
			}
			// �л��� �������°� ������ ��쿡�� ������û�� ������.
			sql = "select * from student where stnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				status = rs.getString("floor");
				if(!status.equals("����")||!status.equals("����")) {
					check = 4;
				}
			}
			// �̹� ���ǰ� �ִ� �ð����� ������û �� �� ����.
			// ������û ���̺��� �й�,�⵵,�б⸦ �������� �����ڵ带 ��ȸ�ϰ�, �����ڵ� �� ���ڸ��� ���� ��� ���� �ð��̹Ƿ� ��û�� �� ����.	
			sql = "select * from signup where stnum=? and date1=? and semester=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stnum);
			pstmt.setString(2, lec.getDate1());
			pstmt.setString(3, lec.getSemester());
			rs = pstmt.executeQuery();
 
			if (rs.next()) {
				String num = classnum; // �޾ƿ� �����ڵ�
				String numresult = num.substring(num.length() -2, num.length());
				String code = rs.getString("classnum");
				String coderesult = code.substring(code.length() -2, code.length());
				if (numresult.equals(coderesult)) { // �����ڵ��� ���ڸ��� ���ٸ� ��û�ð��� �����Ƿ� ��û�� �� ����.
					check = 5;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException s) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException s) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException s) {
				}
		}
		return check;
	}
}