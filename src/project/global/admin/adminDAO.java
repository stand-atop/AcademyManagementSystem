package project.global.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


import java.util.ArrayList;

public class adminDAO {
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	private Connection getConnection() { // 1�ܰ�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@nullmaster.iptime.org:1521:orcl";
			String user = "class09";
			String password = "class09";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	


	// ������ �л� ������ �Է�
	public String insertStudent(stuInfoDTO dto) {
		String msg = "�Է� �ȵ�.";
		try {
			
			conn=getConnection();
			String sql = "insert into student values(?,?,?,?,?"
					+ ",?,?,?,?,?"
					+ ",?,?,?,?,sysdate"
					+ ",?,?,?,?,?)";
			pst = conn.prepareStatement(sql);

			pst.setString(1, dto.getImage());	// �̹���
			pst.setString(2, dto.getStname());	// �̸�
			pst.setInt(3, dto.getYear1());		// ��
			pst.setInt(4, dto.getMon());		// ��
			pst.setInt(5, dto.getDay1());		// ��
			
			pst.setString(6, dto.getStnum());	// �й�
			pst.setInt(7, dto.getGrade());		//�г�
			pst.setString(8, dto.getPart());	//�а�
			pst.setString(9, dto.getZon());		//�����ȣ
			pst.setString(10, dto.getStreet());	// ���θ��ּ�
			
			pst.setString(11, dto.getAddr());	// ���ּ�
			pst.setString(12, dto.getBank());	//����
			pst.setString(13, dto.getAcnt());	//����
			pst.setString(14, dto.getFloor());	//�ź�
			// add sysdate						//���ӳ�¥
			
			pst.setString(15, dto.getSex());	//����
			pst.setString(16, dto.getDomiuse());	//����� ��뿩��
			pst.setString(17, dto.getPw());		//��й�ȣ
			pst.setString(18, dto.getPhone());	// �ڵ�����ȣ
			pst.setString(19,  dto.getEmail());
			
			pst.executeUpdate();
			
			sql = "insert into global values(?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1,dto.getStnum());
			pst.setString(2, dto.getPw());
			pst.setString(3, "1");
			pst.executeUpdate();
			
			msg = "�Է� �Ϸ�!!!!!";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException s) {
				}
			;
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException s) {
				}
			;
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException s) {
				}
			;
		}

		return msg;
	}
	
	// ���� ��û�� �л��� list
	public List<timeoffappDTO> timeoffList(){
		List<timeoffappDTO> list = null;
		
		try {
			conn = getConnection();
			pst = conn.prepareStatement("select * from status where approval=0");
			rs = pst.executeQuery();
			list = new ArrayList<timeoffappDTO>();
			while(rs.next()) {
				timeoffappDTO dto = new timeoffappDTO();
				dto.setStnum(rs.getString("stnum"));
				dto.setName(rs.getString("name"));
				dto.setCouse(rs.getString("couse"));
				dto.setDay(rs.getString("day"));
				dto.setYear(rs.getString("year"));
				dto.setSemester(rs.getString("semester"));
				dto.setApproval(rs.getInt("approval"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(rs != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try { pst.close(); } catch(SQLException s) { }
		}
		
		return list;
	}

	// ���� ��û ����
	public String timeoffapp1(String stnum, String year, String semester, String couse, int data) {
		String msg = "����";
		
		try {
			
			conn = getConnection();
			pst = conn.prepareStatement("select * from status where stnum = ? and year=? and semester=? and couse=? and approval=0");
			pst.setString(1, stnum);
			pst.setString(2, year);
			pst.setString(3, semester);
			pst.setString(4, couse);
			rs = pst.executeQuery();
			msg="������";
			while(rs.next()) {
				pst = conn.prepareStatement("update status set approval=? where stnum=? and year=? and semester=? and couse=?");
				pst.setInt(1, data);
				pst.setString(2, stnum);
				pst.setString(3, year);
				pst.setString(4, semester);
				pst.setString(5, couse);
				pst.executeUpdate();
				msg = "ó�� �Ǿ��D";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try { pst.close(); } catch(SQLException s) { }
			if(rs != null) try { rs.close(); } catch(SQLException s) { }
		}
		
		
		return msg;
	}
	
	// ���� ��û ����
	public String timeoffapp2(String stnum, String year, String semester, String couse) {
		String msg = "����";
		
		try {
			
			conn = getConnection();
			pst = conn.prepareStatement("select * from status where stnum = ? and year=? and semester=? and couse=?");
			pst.setString(1, stnum);
			pst.setString(2, year);
			pst.setString(3, semester);
			pst.setString(4, couse);
			rs = pst.executeQuery();
			msg="������";
			while(rs.next()) {
				pst = conn.prepareStatement("update status set approval=? where stnum=? and year=? and semester=? and couse=?");
				pst.setInt(1, 2);
				pst.setString(2, stnum);
				pst.setString(3, year);
				pst.setString(4, semester);
				pst.setString(5, couse);
				pst.executeUpdate();
				msg = "���� �Ǿ��D";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try { pst.close(); } catch(SQLException s) { }
			if(rs != null) try { rs.close(); } catch(SQLException s) { }
		}
		
		
		return msg;
	}
	
	// Ķ���� ���� �Է�
	public String calendarinput(calendarDTO dto) {
		String msg = "����";
		
		try {
			conn = getConnection();
			String sql = "Insert into schedule values(?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, dto.getDay());
			pst.setString(2, dto.getContents());
			
			pst.executeUpdate();
			msg = "���� �Է� �Ϸ�";
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try {pst.close(); } catch(SQLException s) { }
		}
	
		return msg;
	}
	
	// �л� ��������/���� �������� �Է½� �а� �ҷ�����
	public List<subjectDTO> partcall() {
		List<subjectDTO> list = null;
		try {
			conn = getConnection();
			String sql = "select * from subject";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<subjectDTO>();
			while(rs.next()) {
				subjectDTO dto = new subjectDTO();
				dto.setSubname(rs.getString("subname"));
				dto.setSubnum(rs.getString("subnum"));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try { pst.close(); } catch(SQLException s) { }
			if(rs != null) try { rs.close(); } catch(SQLException s) { }
		}
		
		return list;
	}

	// ���� �������� �Է�
	public String proinputinfo(proinputinfoDTO dto) {
		String msg = "����";
		
		try {
			conn = getConnection();
			String sql = "Insert into professor values(?,?,?,?,"
					+ "?,?,?,?,"
					+ "?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, dto.getProname());
			pst.setString(2, dto.getProborn());
			pst.setString(3, dto.getPropart());
			pst.setString(4, dto.getTel1());
			
			pst.setString(5, dto.getProemail());
			pst.setString(6, dto.getZone());
			pst.setString(7, dto.getStreet());
			pst.setString(8, dto.getAddr());
			
			pst.setString(9, dto.getAcount());
			pst.setString(10, dto.getPw());
			pst.setString(11, dto.getId());
			pst.setString(12, dto.getBank());
			pst.setString(13, dto.getImage());
			pst.setString(14, dto.getPropartcode());
			pst.executeUpdate();
			msg = "�Է���";
			
			sql = "insert into global values(?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1,dto.getId());
			pst.setString(2, dto.getPw());
			pst.setString(3, "2");
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try { pst.close(); } catch(SQLException s) { }
			if(rs != null) try { rs.close(); } catch(SQLException s) { }
		}
		
		return msg;
	}
	
	// ���� ��û�� �л��� list
	public List<timeonDTO> timeonList(){
		List<timeonDTO> list = null;
		
		try {
			conn = getConnection();
			pst = conn.prepareStatement("select * from timeon where approval = 0");
			rs = pst.executeQuery();
			list = new ArrayList<timeonDTO>();
			while(rs.next()) {
				timeonDTO dto = new timeonDTO();
				
				dto.setName(rs.getString("name"));
				dto.setDay(rs.getString("day"));
				dto.setStnum(rs.getString("stnum"));
				dto.setYear(rs.getString("year"));
				dto.setSemester(rs.getString("semester"));
				dto.setApproval(rs.getInt("approval"));
				list.add(dto);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try { pst.close(); } catch(SQLException s) { }
		}
		
		
		return list;
	}

	// ���� ����
	public String timeonapp1(String stnum, String name, int data) {
		String msg = "�ƾƾƾ���!";
		
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		int nYear = calendar.get(Calendar.YEAR);
		int nMonth = calendar.get(Calendar.MONTH) + 1;
		int nDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		String year = Integer.toString(nYear);
		String mon = Integer.toString(nMonth);
		String day1 = String.format("%02d", nDay);

		
		try {
			conn = getConnection();
			pst = conn.prepareStatement("select * from timeon where stnum=? and name=? and approval=0");
			pst.setString(1, stnum);
			pst.setString(2, name);
			rs = pst.executeQuery();
			msg="������";
			while(rs.next()) {
				pst = conn.prepareStatement("update timeon set approval = ?, approvaldate=?, backschool=? where stnum = ? and name=? and approval=0");
				pst.setInt(1, data);
				pst.setString(2, year+"-0"+mon+"-"+day1);
				String off_year = rs.getString("year");
				String off_semester = rs.getString("semester");
				
				if(off_semester.equals("1")) {
					off_semester = "2";
					msg="��������~";
				}else if(off_semester.equals("2")) {
					off_semester = "1";
					int i = Integer.parseInt(off_year);
					i++;
					off_year = Integer.toString(i);
				}				
				
				pst.setString(3, off_year+"�� "+off_semester+"�б�");
				pst.setString(4, stnum);
				pst.setString(5, name);
				pst.executeUpdate();
				
				msg = "ó�� �Ǿ�?";
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try { pst.close(); } catch(SQLException s) { }
			if(rs != null) try { rs.close(); } catch(SQLException s) { }
		}
		
		return msg;
	}

	// ���� ����
	public String timeonapp2(String stnum, String name) {
		String msg = "�õ���!";
		
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		int nYear = calendar.get(Calendar.YEAR);
		int nMonth = calendar.get(Calendar.MONTH) + 1;
		int nDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		String year = Integer.toString(nYear);
		String mon = Integer.toString(nMonth);
		String day1 = String.format("%02d", nDay);
		
		try {
			conn = getConnection();
			pst = conn.prepareStatement("select * from timeon where stnum=? and name=?");
			pst.setString(1, stnum);
			pst.setString(2, name);
			rs = pst.executeQuery();
			msg="������";
			while(rs.next()) {
				pst = conn.prepareStatement("update timeon set approval = ?, approvaldate=? where stnum = ? and name=? and approval=0");
				pst.setInt(1, 2);
				pst.setString(2, year+"-0"+mon+"-"+day1);
				pst.setString(3, stnum);
				pst.setString(4, name);
				pst.executeUpdate();
				
				msg = "���� �Ǿ�?";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try { pst.close(); } catch(SQLException s) { }
			if(rs != null) try { rs.close(); } catch(SQLException s) { }
		}
		
		return msg;
	}

	// ������ : �л� ���� ������ ���� ��ȸ dao
	public stumodifyDTO stumodify(String subject, String search_stnum) {
		stumodifyDTO dto = null;
		try {
			conn = getConnection();
			
			pst = conn.prepareStatement("select * from student where part=? and stnum=?");
			pst.setString(1, subject);
			pst.setString(2, search_stnum);
			rs = pst.executeQuery();
			if(rs.next()) {
				dto = new stumodifyDTO();
				dto.setStname(rs.getString("stname"));
				dto.setYear1(rs.getInt("year1"));
				dto.setMon(rs.getInt("mon"));
				dto.setDay1(rs.getInt("day1"));
				dto.setStnum(rs.getString("stnum"));
				dto.setGrade(rs.getInt("grade"));
				dto.setPart(rs.getString("part"));
				dto.setZon(rs.getString("zon"));
				dto.setStreet(rs.getString("street"));
				dto.setAddr(rs.getString("addr"));
				dto.setBank(rs.getString("bank"));
				dto.setAcnt(rs.getString("acnt"));
				dto.setFloor(rs.getString("floor"));
				dto.setSex(rs.getString("sex"));
				dto.setDomiuse(rs.getString("domiuse"));
				dto.setPw(rs.getString("pw"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setImage(rs.getString("image"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try { pst.close(); } catch(SQLException s) { }
			
		}
		
		return dto;
	}
	
	// ������ �л� ���� ����
	public String stumodify(stumodifyDTO dto) {
		String msg = "���� ��Ÿ��";
		
		try {
			conn = getConnection();
			pst = conn.prepareStatement("update student set "
					+ "image =?, stname=?, year1=?, mon=?,"
					+ "day1=?, grade=?, part=?,"
					+ "zon=?, street=?, addr=?, bank=?,"
					+ "acnt=?, floor=?, acsday=sysdate, sex=?,"
					+ "domiuse=?, pw=?, phone=? where stnum=?");
			
			
			pst.setString(1, dto.getImage());
			pst.setString(2, dto.getStname());
			pst.setInt(3, dto.getYear1());
			pst.setInt(4, dto.getMon());
			
			pst.setInt(5, dto.getDay1());
			pst.setInt(6, dto.getGrade());
			pst.setString(7, dto.getPart());
			
			pst.setString(8, dto.getZon());
			pst.setString(9, dto.getStreet());
			pst.setString(10, dto.getAddr());
			pst.setString(11, dto.getBank());
			
			pst.setString(12, dto.getAcnt());
			pst.setString(13, dto.getFloor());
			// sysdate
			pst.setString(14, dto.getSex());
			
			pst.setString(15, dto.getDomiuse());
			pst.setString(16, dto.getPw());
			pst.setString(17, dto.getPhone());
			
			pst.setString(18, dto.getStnum());
			pst.executeUpdate();
			msg = "�����Ǿ��D!";
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(pst != null) try { pst.close(); } catch(SQLException s) { }
		}
		
		return msg;
	}
	
	//���� ���� ��û ��ȸ
		public List<procheckDTO> getLecture1(){
			List<procheckDTO> list = null;
			try {
				conn = getConnection();
				pst = conn.prepareStatement("select * from class1 where classname is not null and allow=0");
				rs = pst.executeQuery();
				
				list = new ArrayList<procheckDTO>();
				while(rs.next()) {
					procheckDTO dto = new procheckDTO();
					dto.setCan(rs.getString("can"));
					dto.setClassnum(rs.getString("classnum"));
					dto.setClassname(rs.getString("classname"));
					dto.setScore(rs.getInt("score")); 
					dto.setReg_date(rs.getTimestamp("reg_date"));
					list.add(dto);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null)try {rs.close();}catch(SQLException s) {}
				if(pst != null)try {pst.close();}catch(SQLException s) {}
				if(conn != null)try {conn.close();}catch(SQLException s) {}
			}
			return list;
		}
		
		// ���ǰ��� ����
		public String procheck1(String classnum) {
			String msg = "�ƾƾƾ���!";
						
			try {
				conn = getConnection();
				pst = conn.prepareStatement("select * from class1 where classnum=?");
				pst.setString(1, classnum);
				rs = pst.executeQuery();
				msg="������";
				
				while(rs.next()) {
					msg = "����� �̰�";
					pst = conn.prepareStatement("update class1 set allow=1 where classnum=?");
					pst.setString(1, classnum);
					
					pst.executeUpdate();
					
					msg = "���� �Ǿ��D";
				
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(conn != null) try { conn.close(); } catch(SQLException s) { }
				if(pst != null) try { pst.close(); } catch(SQLException s) { }
				if(rs != null) try { rs.close(); } catch(SQLException s) { }
			}
			
			return msg;
		}
		
		// ���ǰ��� ����
				public String procheck2(String classnum) {
					String msg = "�ƾƾƾ���!";
								
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select * from class1 where classnum=?");
						pst.setString(1, classnum);
						rs = pst.executeQuery();
						msg="������";
						
						while(rs.next()) {
							msg = "����� �̰�";
							pst = conn.prepareStatement("update class1 set allow=-1 where classnum=?");
							pst.setString(1, classnum);
							
							pst.executeUpdate();
							
							msg = "���� �Ǿ��D";
						
						}
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						if(conn != null) try { conn.close(); } catch(SQLException s) { }
						if(pst != null) try { pst.close(); } catch(SQLException s) { }
						if(rs != null) try { rs.close(); } catch(SQLException s) { }
					}
					
					return msg;
				}
				// �а���ȣ �������
				public String subnum2(String part) {
					String subnum = null;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select subnum2 from subject where subname=?");
						pst.setString(1, part);
						rs = pst.executeQuery();
						if(rs.next()) {
							subnum = rs.getString(1);
						}
					}catch(Exception e){
						e.printStackTrace();
					}finally {
						if(conn != null) try { conn.close(); } catch(SQLException s) { }
						if(pst != null) try { pst.close(); } catch(SQLException s) { }
						if(rs != null) try { rs.close(); } catch(SQLException s) { }
						
					}
					
					return subnum;
				}
				
				// �а��� ���� �⵵�� ��ϵǾ��ִ� �л��� count
				public String partnum(String part) {
					String partnum = null;
					try {
						conn = getConnection();
						String sql = "select max(stnum) from student where stnum like concat(concat((select to_char(sysdate,'YYYY') from dual),(select subnum2 from subject where subname=?)),'%')";
						pst = conn.prepareStatement(sql);
						pst.setString(1, part);
						rs = pst.executeQuery();
						if(rs.next()) {
							partnum = rs.getString(1);
						}
					}catch (Exception e){
						e.printStackTrace();
					}finally {
						if( conn != null ) try { conn.close(); } catch(SQLException s) { }
						if( pst != null ) try { pst.close(); } catch(SQLException s) { }
						if( rs != null ) try { rs.close(); } catch(SQLException s) { }
					}
					
					return partnum;
				}
				
				// ����������ȣ �ִ밪 ������
				public String pronumber(String propart) {
					String number = null;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select max(substr(id,7,9)) from professor where propart=?");
						pst.setString(1, propart);
						rs = pst.executeQuery();
						if(rs.next()) {
							number = rs.getString(1);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						if(conn != null) try { conn.close(); } catch(SQLException s) { }
						if(rs != null) try { rs.close(); } catch(SQLException s) { }
						if(pst != null) try { pst.close(); } catch(SQLException s) { }
					}
					
					return number;
				}
				
				
				//���� ������ �������� �Խ���
				public void insertArticle(adminListDTO article) throws Exception {
					int num=article.getNum(); //���������� ������� �μ�Ʈ�ϴ� ����
				
					int number=0;
					String sql="";
					try {
						conn = getConnection(); 
						pst = conn.prepareStatement("select max(num) from adminlist");//num���� ������ �������� ���� ū ���ڸ� ����Ʈ
						rs = pst.executeQuery();
						if (rs.next()) 
							number=rs.getInt(1)+1;//�������� 1�̶�� 1+1�ؼ� 2�� �ؿ��ִ� ������� DB�� �μ�Ʈ�Ѵ�	
						else
							number=1; //���� ���� ������ 1�� ���´ٸ�(�Խù��� �ƹ��͵� ���ٸ�) �ѹ� 1�� �ؿ� �ִ� ������� DB�� �μ�Ʈ�Ѵ�

			 
						sql = "insert into adminlist(num,writer,subject,reg_date,";
						sql+="content) values(adminlist_seq.NEXTVAL,?,?,?,?)";
						pst = conn.prepareStatement(sql);
						pst.setString(1, article.getWriter());
						pst.setString(2, article.getSubject());
						pst.setTimestamp(3, article.getReg_date());
						pst.setString(4, article.getContent());
						pst.executeUpdate();
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
				}
				public int getArticleCount() throws Exception {//�ش� ���̺� �Խù��� ���� ��Ÿ���� �޼���
					int x=0;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select count(*) from adminlist");
						rs = pst.executeQuery();
						if (rs.next()) {
							x= rs.getInt(1); //ī���Ϳ� ��Ÿ���°� ��ƮŸ���̱⿡ ����Ʈ�ι޴´�
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
					return x; //�� ī���͸� ���
				}
				public int getArticleCount(String writer) throws Exception {//��  writer�� �޼��� ������ �𸣰��� 
					int x=0;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select count(*) from adminlist where writer=?");//�ۼ����� �Խù� ������ ��Ÿ����
						pst.setString(1, writer);//
						rs = pst.executeQuery();
						if (rs.next()) {
							x= rs.getInt(1); 
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
					return x; //�� ī���͸� ���
				}
				
				public List getArticles(int start, int end) throws Exception {
					List articleList=null;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select * from (select num,writer,subject,reg_date,content,readcount,rownum r from "
								+ "(select *  from adminlist order by num desc) order by num desc ) where r >= ? and r <= ? ");
								pst.setInt(1, start); //adminlist�� 
								pst.setInt(2, end); 

								rs = pst.executeQuery();
								if (rs.next()) {
									articleList = new ArrayList(end); 
									do{ 
										adminListDTO article= new adminListDTO();
										article.setNum(rs.getInt("num"));
										article.setWriter(rs.getString("writer"));
										article.setSubject(rs.getString("subject"));
										article.setReg_date(rs.getTimestamp("reg_date"));
										article.setReadcount(rs.getInt("readcount"));
										article.setContent(rs.getString("content"));
										articleList.add(article); 
									}while(rs.next());
								}
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}

					
					return articleList;
				}
				public adminListDTO getArticle(int num) throws Exception {
					adminListDTO article=null;
					try {
						conn = getConnection();
						pst = conn.prepareStatement(
						"update adminlist set readcount=readcount+1 where num = ?");//��ȸ���� ������Ʈ�ϴ� �޼���
						pst.setInt(1, num);
						pst.executeUpdate();
						pst = conn.prepareStatement(
						"select * from adminlist where num = ?"); //������ Num�� ����Ʈ�ϱ�
						pst.setInt(1, num);
						rs = pst.executeQuery();
						if (rs.next()) {
							article = new adminListDTO();
							article.setNum(rs.getInt("num"));
							article.setWriter(rs.getString("writer"));
							article.setSubject(rs.getString("subject"));
							article.setReg_date(rs.getTimestamp("reg_date"));
							article.setReadcount(rs.getInt("readcount"));
							article.setContent(rs.getString("content"));
							
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
					
					return article;
				}
				public String testDelete(int num) {//����
					String msg="�����Ͻðڽ��ϱ�?";
					try {
						conn = getConnection();	
						String sql = "delete from adminlist where num=?";
						pst = conn.prepareStatement(sql);
						pst.setInt(1,num);
						pst.executeUpdate();
						msg= "�����Ǿ����ϴ�.";

					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						if(rs!=null)try {rs.close();}catch(SQLException s) {}
						if(pst != null)try {pst.close();}catch(SQLException s) {}
						if(conn != null)try {conn.close();}catch(SQLException s) {}
					}
					return msg;
				}
				public adminListDTO updateGetArticle(int num) throws Exception {//���� �� �⺻���� ����Ʈ�ؼ� ���� �������
					adminListDTO article=null;
					try {
						conn = getConnection();
						pst = conn.prepareStatement(
						"select * from adminlist where num = ?"); 
						pst.setInt(1, num);
						rs = pst.executeQuery();
						if (rs.next()) {
							article = new adminListDTO();
							article.setNum(rs.getInt("num"));
							article.setWriter(rs.getString("writer"));
							article.setSubject(rs.getString("subject"));
							article.setReg_date(rs.getTimestamp("reg_date"));
							article.setReadcount(rs.getInt("readcount"));
							article.setContent(rs.getString("content"));

						}
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}

					return article;
				}
				public int updateArticle(adminListDTO article) throws Exception {// ���� �� ������Ʈ
					String writer="";
					String sql="";
					int x=-1;
					try {
						conn = getConnection();
						pst = conn.prepareStatement(
						"select * from adminlist where num = ?");
						pst.setInt(1, article.getNum());
						rs = pst.executeQuery();
						if(rs.next()){
							writer= rs.getString("writer"); 
							if(writer.equals(article.getWriter())){
								sql="update adminlist set writer=?,subject=?";
								sql+=",content=? where num=?";
								pst = conn.prepareStatement(sql);
								pst.setString(1, article.getWriter());
								pst.setString(2, article.getSubject());
								pst.setString(3, article.getContent());
								pst.setInt(4, article.getNum());
								pst.executeUpdate();
								x= 1;
							}else{
								x= 0;
							}
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
					return x;
				}
				//���� �нǹ� �Խ���
				public int getArticleCount1() throws Exception {//�ش� ���̺� �Խù��� ���� ��Ÿ���� �� 
					int x=0;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select count(*) from adminlost");
						rs = pst.executeQuery();
						if (rs.next()) {
							x= rs.getInt(1); //ī���Ϳ� ��Ÿ���°� ��ƮŸ���̱⿡ ����Ʈ�ι޴´�
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
					return x; //�� ī���͸� ���
				}
				public int getArticleCount1(String writer) throws Exception {
					int x=0;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select count(*) from adminlost where writer=?");
						pst.setString(1, writer);//
						rs = pst.executeQuery();
						if (rs.next()) {
							x= rs.getInt(1); 
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
					return x; //�� ī���͸� ���
				}
				
				
				public List<adminlostDTO> getArticles1(int start, int end) throws Exception{//������������ ��Ÿ���� �Խù� ������ ������������ �����ϴ� �޼���
					List<adminlostDTO> articleList=null;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select * from(select num,writer,subject,reg_date,content,readcount,rownum r from"
								+ "(select * from adminlost order by num desc) order by num desc) where r >=? and r <=?");
						pst.setInt(1, start);
						pst.setInt(2, end);
						rs = pst.executeQuery();
						articleList = new ArrayList<adminlostDTO>();
						while(rs.next()) {
							adminlostDTO article = new adminlostDTO();
							article.setNum(rs.getInt("num"));
							article.setWriter(rs.getString("writer"));
							article.setSubject(rs.getString("subject"));
							article.setReg_date(rs.getTimestamp("reg_date"));
							article.setReadcount(rs.getInt("readcount"));
							article.setContent(rs.getString("content"));
							articleList.add(article);
						}
						
			/*
			 * if (rs.next()) { articleList = new ArrayList<adminlostDTO>(end); do{
			 * adminlostDTO article = new adminlostDTO(); article.setNum(rs.getInt("num"));
			 * article.setWriter(rs.getString("writer"));
			 * article.setSubject(rs.getString("subject"));
			 * article.setReg_date(rs.getTimestamp("reg_date"));
			 * article.setReadcount(rs.getInt("readcount"));
			 * article.setContent(rs.getString("count")); articleList.add(article);
			 * }while(rs.next()); }
			 */
				}catch (Exception ex){
					ex.printStackTrace();
				}finally {
					if (rs !=null) try {rs.close();} catch(SQLException ex) {}
					if (pst !=null) try {pst.close();} catch(SQLException ex) {}
					if (conn !=null) try {conn.close();} catch(SQLException ex) {}
					
				}
					return articleList;
				}
				public adminlostDTO getArticle1(int num) throws Exception{
					adminlostDTO article=null;
					try{
						conn = getConnection();
						pst = conn.prepareStatement("update adminlost set readcount=readcount+1 where num = ?");
						pst.setInt(1, num);
						pst.executeUpdate();
						pst = conn.prepareStatement("select * from adminlost where num = ?");
						pst.setInt(1, num);
						rs = pst.executeQuery();
						if (rs.next()) {
							article = new adminlostDTO();
							article.setNum(rs.getInt("num"));
							article.setWriter(rs.getString("writer"));
							article.setSubject(rs.getString("subject"));
							article.setReg_date(rs.getTimestamp("reg_date"));
							article.setReadcount(rs.getInt("readcount"));
							article.setContent(rs.getString("content"));
						}
						
						
					
					}catch(Exception ex){
						
					}finally {
						if (rs !=null) try {rs.close();} catch(SQLException ex) {}
						if (pst != null) try {pst.close();} catch(SQLException ex) {}
						if (conn !=null) try {conn.close();} catch(SQLException ex) {}
						
					}
					return article;
					}
				public void insertArticle1(adminlostDTO article) throws Exception{
					int num=article.getNum();
					int number=0;
					String sql="";
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select max(num) from adminlost");
						rs = pst.executeQuery();
						if (rs.next())
							number=rs.getInt(1)+1;
						else
							number=1;
						sql = "insert into adminlost(num,writer,subject,reg_date,";
						sql+= "content) values(adminlost_seq.NEXTVAL,?,?,?,?)";
						pst = conn.prepareStatement(sql);
						pst.setString(1, article.getWriter());
						pst.setString(2, article.getSubject());
						pst.setTimestamp(3, article.getReg_date());
						pst.setString(4, article.getContent());
						pst.executeUpdate();
						
					}catch(Exception ex) {
						ex.printStackTrace();
					}finally {
						if (rs !=null) try {rs.close();} catch(SQLException ex) {}
						if (pst != null) try {pst.close();} catch(SQLException ex) {}
						if (conn !=null) try {conn.close();} catch(SQLException ex) {}
						
					
						
					}
				}
				public adminlostDTO updateGetArticle1(int num) throws Exception {//���� �� �⺻���� ����Ʈ�ؼ� ���� �������
					adminlostDTO article=null;
					try {
						conn = getConnection();
						pst = conn.prepareStatement(
						"select * from adminlost where num = ?"); 
						pst.setInt(1, num);
						rs = pst.executeQuery();
						if (rs.next()) {
							article = new adminlostDTO();
							article.setNum(rs.getInt("num"));
							article.setWriter(rs.getString("writer"));
							article.setSubject(rs.getString("subject"));
							article.setReg_date(rs.getTimestamp("reg_date"));
							article.setReadcount(rs.getInt("readcount"));
							article.setContent(rs.getString("content"));

						}
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}

					return article;
				}
				public int updateArticle1(adminlostDTO article) throws Exception {// ���� �� ������Ʈ
					String writer="";
					String sql="";
					int x=-1;
					try {
						conn = getConnection();
						pst = conn.prepareStatement(
						"select * from adminlost where num = ?");
						pst.setInt(1, article.getNum());
						rs = pst.executeQuery();
						if(rs.next()){
							writer= rs.getString("writer"); 
							if(writer.equals(article.getWriter())){
								sql="update adminlost set writer=?,subject=?";
								sql+=",content=? where num=?";
								pst = conn.prepareStatement(sql);
								pst.setString(1, article.getWriter());
								pst.setString(2, article.getSubject());
								pst.setString(3, article.getContent());
								pst.setInt(4, article.getNum());
								pst.executeUpdate();
								x= 1;
							}else{
								x= 0;
							}
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					} finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pst != null) try { pst.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
					return x;
				}
				public String testDelete1(int num) {
					String msg="�����Ͻðڽ��ϱ�?";
					try {
						conn = getConnection();	
						String sql = "delete from adminlost where num=?";
						pst = conn.prepareStatement(sql);
						pst.setInt(1,num);
						pst.executeUpdate();
						msg= "�����Ǿ����ϴ�.";

					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						if(rs!=null)try {rs.close();}catch(SQLException s) {}
						if(pst != null)try {pst.close();}catch(SQLException s) {}
						if(conn != null)try {conn.close();}catch(SQLException s) {}
					}
					return msg;
				}
				public int getStudentCount1() throws Exception{//�л��� �� �����ο� ������ ���ο� ����
					int x= 0;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select count (*) from student where floor='����'");
						rs = pst.executeQuery();
						if (rs.next()) {
							x = rs.getInt(1);
						}
					}catch(Exception ex) {
						ex.printStackTrace();
				}finally {if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pst != null) try { pst.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
					return x;

				}
				public int getStudentCount2() throws Exception{//�л��� �� �����ο� ������ ���ο� ����
					int x= 0;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select count (*) from student where floor='����'");//
						rs = pst.executeQuery();
						if (rs.next()) {
							x = rs.getInt(1);
						}
					}catch(Exception ex) {
						ex.printStackTrace();
				}finally {if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pst != null) try { pst.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
					return x;

				}
				public int getProfessorCount() throws Exception{
					int x= 0;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select count(*) from professor");
						rs = pst.executeQuery();
						if (rs.next()) {
							x = rs.getInt(1);
						}
					}catch(Exception ex) {
						ex.printStackTrace();
				}finally {if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pst != null) try { pst.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
					return x;

				}
				//�����ٿ�
				//���� ���� ��û ��ȸ
				public List<procheckDTO> getLecture(){
					List<procheckDTO> list = null;
					try {
						conn = getConnection();
						pst = conn.prepareStatement("select * from class1");
						rs = pst.executeQuery();
						
						list = new ArrayList<procheckDTO>();
						while(rs.next()) {
							procheckDTO dto = new procheckDTO();
							dto.setCan(rs.getString("can"));
							dto.setClassnum(rs.getString("classnum"));
							dto.setClassname(rs.getString("classname"));
							dto.setScore(rs.getInt("score")); 
							dto.setReg_date(rs.getTimestamp("reg_date"));
							list.add(dto);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						if(rs != null)try {rs.close();}catch(SQLException s) {}
						if(pst != null)try {pst.close();}catch(SQLException s) {}
						if(conn != null)try {conn.close();}catch(SQLException s) {}
					}
					return list;
				}
				
				//���δٿ�
				
}

				
				
				

				