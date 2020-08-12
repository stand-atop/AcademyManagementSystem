package project.global.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.global.student.stuLostDTO;
import project.global.student.infoDTO;
import project.global.student.modifyDTO;

public class studentDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
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
	

	// �������� ��ȸ (infoDTO / infoForm.jsp)
	public infoDTO printInfo(String stnum) {
		infoDTO dto = null;

		try {
			conn = getConnection();
			String sql = "select * from student where stnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new infoDTO();
				dto.setStname(rs.getString("stname"));
				dto.setYear1(rs.getInt("year1"));
				dto.setMon(rs.getInt("mon"));
				dto.setDay1(rs.getInt("day1"));
				dto.setStnum(rs.getString("stnum"));
				dto.setPart(rs.getString("part"));
				dto.setPhone(rs.getString("phone"));
				dto.setZon(rs.getString("zon"));
				dto.setStreet(rs.getString("street"));
				dto.setAddr(rs.getString("addr"));
				dto.setBank(rs.getString("bank"));
				dto.setAcnt(rs.getString("acnt"));
				dto.setGrade(rs.getInt("grade"));
				dto.setImage(rs.getString("image"));
				dto.setFloor(rs.getString("floor"));
				dto.setSex(rs.getString("sex"));
				dto.setPw(rs.getString("pw"));
				dto.setDomiuse(rs.getString("domiuse"));
				dto.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException s) {
				}
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
		}

		return dto;
	}

	// �������� ���� (modifyDTO / modifyForm.jsp)
	public modifyDTO printInfo2(String stnum) {
		modifyDTO dto = null;

		try {
			conn = getConnection();
			String sql = "select * from student where stnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new modifyDTO();
				dto.setStname(rs.getString("stname"));
				dto.setYear1(rs.getInt("year1"));
				dto.setMon(rs.getInt("mon"));
				dto.setDay1(rs.getInt("day1"));
				dto.setStnum(rs.getString("stnum"));
				dto.setPart(rs.getString("part"));
				dto.setPhone(rs.getString("phone"));
				dto.setZon(rs.getString("zon"));
				dto.setStreet(rs.getString("street"));
				dto.setAddr(rs.getString("addr"));
				dto.setBank(rs.getString("bank"));
				dto.setAcnt(rs.getString("acnt"));
				dto.setGrade(rs.getInt("grade"));
				dto.setImage(rs.getString("image"));
				dto.setFloor(rs.getString("floor"));
				dto.setSex(rs.getString("sex"));
				dto.setPw(rs.getString("pw"));
				dto.setDomiuse(rs.getString("domiuse"));
				dto.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException s) {
				}
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
		}

		return dto;
	}

	// �л����� ����
	public String stuinfo_modify(modifyDTO dto) {
		String msg = "������������";
		try {
			conn = getConnection();

			String sql = "select * from student where stnum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getStnum());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sql = "update student set image=?, stname=?, year1=?, mon = ?, "
						+ "day1 = ?, grade = ?, part = ?, zon = ?, street=?"
						+ ", addr=?, bank=?, acnt = ?, floor=?, acsday= sysdate,"
						+ "domiuse=?, pw = ?, phone=? where stnum=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, dto.getImage());
				pstmt.setString(2, dto.getStname());
				pstmt.setInt(3, dto.getYear1());
				pstmt.setInt(4, dto.getMon());

				pstmt.setInt(5, dto.getDay1());
				pstmt.setInt(6, dto.getGrade());
				pstmt.setString(7, dto.getPart());
				pstmt.setString(8, dto.getZon());
				pstmt.setString(9, dto.getStreet());

				pstmt.setString(10, dto.getAddr());
				pstmt.setString(11, dto.getBank());
				pstmt.setString(12, dto.getAcnt());
				pstmt.setString(13, dto.getFloor());

				pstmt.setString(14, dto.getDomiuse());
				pstmt.setString(15, dto.getPw());
				pstmt.setString(16, dto.getPhone());
				pstmt.setString(17, dto.getStnum());

				pstmt.executeUpdate();
				msg = "��������*^^*!";
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
		return msg;
	}

	// ���� ��û�� (timeoffDTO / timeoffForm.jsp)
	public timeoffDTO printInfo3(String stnum) {
		timeoffDTO dto = null;

		try {
			conn = getConnection();
			String sql = "select * from student where stnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new timeoffDTO();
				dto.setName(rs.getString("stname"));
				dto.setStnum(rs.getString("stnum"));
				dto.setZon(rs.getString("zon"));
				dto.setStreet(rs.getString("street"));
				dto.setAddr(rs.getString("addr"));
				dto.setDay();
				dto.setYear();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException s) {
				}
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
		}

		return dto;
	}
	
	// ���н�û ��ȸ
	public List<timeoffDTO> timeofflookup(String stnum) {
		timeoffDTO dto = null;
		List<timeoffDTO> list = null;
		try {
			conn = getConnection();
			String sql = "select * from status where stnum=? order by day desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();
			list = new ArrayList<timeoffDTO>();
			while(rs.next()) {
				dto = new timeoffDTO();
				dto.setName(rs.getString("name"));
				dto.setDay(rs.getString("day"));
				dto.setCouse(rs.getString("couse"));
				dto.setYear(rs.getString("year"));
				dto.setSemester(rs.getString("semester"));
				dto.setApproval(rs.getInt("approval"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try{ conn.close();} catch(SQLException s) { }
			if(rs != null) try{ rs.close();} catch(SQLException s) { }
			if(pstmt != null) try{ pstmt.close();} catch(SQLException s) { }
		}
		
		return list;
		
	}

	// ���� ��ûPro (timeoffDTO / timeoffPro.jsp)
	public String timeoff(timeoffDTO dto) {
		String msg = "��û�ȵƴ�";

		try {
			msg = "������1";
			conn = getConnection();
			String sql = "Insert into status(name, couse, day, year, semester, stnum, zon, street, addr) values (?,?,?,?,?,?,?,?,?)";
			msg = "������2";
			pstmt = conn.prepareStatement(sql);
			msg = "������3";

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getCouse());
			pstmt.setString(3, dto.getDay());
			pstmt.setString(4, dto.getYear());
			pstmt.setString(5, dto.getSemester());
			pstmt.setString(6, dto.getStnum());
			pstmt.setString(7, dto.getZon());
			pstmt.setString(8, dto.getStreet());
			pstmt.setString(9, dto.getAddr());
			pstmt.executeUpdate();
			msg = "��û�ƿ�!����";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException s) {
				}
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
		}

		return msg;
	}
	
	// ���� ��û�� (timeonDTO / timeonForm.jsp)
	public timeonDTO printInfo4(String stnum) {
		timeonDTO dto = null;
		
		try {
			conn = getConnection();
			String sql = "select * from status where stnum=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new timeonDTO();
				dto.setName(rs.getString("name"));
				dto.setStnum(rs.getString("stnum"));
				dto.setYear(rs.getString("year"));
				dto.setSemester(rs.getString("semester"));
				dto.setDay();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(rs != null) try { rs.close(); } catch(SQLException s) { }
			if(pstmt != null) try { pstmt.close(); } catch(SQLException s) { }
		}
		
		return dto;
		
	}
	
	// ���н�û ��ȸ list
	public List<timeonDTO> timeonlookup(String stnum){
		timeonDTO dto = null;
		List<timeonDTO> list = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from timeon where stnum=? order by day desc");
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();
			list = new ArrayList<timeonDTO>();
			while(rs.next()) {
				dto = new timeonDTO();
				dto.setStnum(rs.getString("stnum"));
				dto.setName(rs.getString("name"));
				dto.setDay(rs.getString("day"));
				dto.setApproval(rs.getInt("approval"));
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(rs != null) try { rs.close(); } catch(SQLException s) { }
			if(pstmt != null) try { rs.close(); } catch(SQLException s) { }
		}
		return list;
	}
	
	// ���� ��ûPro (timeonDTO / timeonPro.jsp)
	public String timeon(timeonDTO dto) {
		String msg = "����!";
		
		try {
			conn = getConnection();
			String sql = "insert into timeon(name, day, stnum, year, semester) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			msg = "�Ƥ�����!";
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getDay());
			pstmt.setString(3, dto.getStnum());
			pstmt.setString(4, dto.getYear());
			pstmt.setString(5, dto.getSemester());
			pstmt.executeUpdate();
			msg = "��û�߿� ���� ���!";
		}catch(Exception e) {
			
		}finally {
			if(conn != null) try { conn.close(); } catch(SQLException s) { }
			if(rs != null) try { rs.close(); } catch(SQLException s) { }
			if(pstmt != null) try { pstmt.close(); } catch(SQLException s) { }
		}
		return msg;
	}
	
	// 
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
	
	public gradeDTO studentInfo(String stnum) { //������ȸ�� �л� ������ȸ
		gradeDTO stuinfo = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from results where stnum=?"); //�й����� �ֱ�
			pstmt.setString(1, stnum); //�й� pro ���ؼ� �Ѱ� �޾ƾ� �ҵ�
			rs = pstmt.executeQuery();
			stuinfo = new gradeDTO();
			if(rs.next()) {
				stuinfo.setStnum(rs.getString("stnum"));
				stuinfo.setStname(rs.getString("name"));
				stuinfo.setRecord(rs.getInt("record"));
				stuinfo.setClassname(rs.getString("classname"));
				stuinfo.setGrade(rs.getString("grade"));
				stuinfo.setScore(rs.getInt("score"));
				stuinfo.setPart(rs.getString("part"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}
		return stuinfo;
	}	
	
	
	public List<gradeDTO> resultChecking(String stnum){ //���� ����Ʈ
		List<gradeDTO> list = null;		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from (select stnum, name, lecturecode, "
					+ "classname, record, grade, score, part from(select * from results "
					+ "order by grade desc, classname asc)order by grade desc, classname asc)"
					+ " where stnum=?"); //
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();
			list = new ArrayList<gradeDTO>();
			while(rs.next()) {
				gradeDTO dto = new gradeDTO();
				dto.setStnum(rs.getString("stnum"));
				dto.setStname(rs.getString("name"));
				dto.setLecturecode(rs.getString("lecturecode"));
				dto.setClassname(rs.getString("classname"));
				dto.setRecord(rs.getInt("record"));
				dto.setGrade(rs.getString("grade"));
				dto.setScore(rs.getInt("score"));
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}		
		return list;		
	}	
	public List<gradeDTO> gradeSelect(String grade, String stnum){
		List<gradeDTO> select = null;
		try {
			conn = getConnection();
			if(grade.equals("1�г�") || grade.equals("2�г�") || grade.equals("3�г�") || grade.equals("4�г�") ) {
				pstmt = conn.prepareStatement("select * from results where grade = ? and stnum = ?");
				pstmt.setString(1, grade);
				pstmt.setString(2, stnum);
				rs = pstmt.executeQuery();
				select = new ArrayList<gradeDTO>();
				while(rs.next()) {
					gradeDTO selectlist = new gradeDTO();
					selectlist.setStnum(rs.getString("stnum"));
					selectlist.setStname(rs.getString("name"));
					selectlist.setLecturecode(rs.getString("lecturecode"));
					selectlist.setClassname(rs.getString("classname"));
					selectlist.setRecord(rs.getInt("record"));
					selectlist.setGrade(rs.getString("grade"));
					selectlist.setScore(rs.getInt("score"));
					select.add(selectlist);
				}
			}else if(grade.equals("����")) {
				pstmt = conn.prepareStatement("select * from results where stnum = ? order by grade desc");
				pstmt.setString(1, stnum);
				rs = pstmt.executeQuery();
				select = new ArrayList<gradeDTO>();
				while(rs.next()) {
					gradeDTO selectlist = new gradeDTO();
					selectlist.setStnum(rs.getString("stnum"));
					selectlist.setStname(rs.getString("name"));
					selectlist.setLecturecode(rs.getString("lecturecode"));
					selectlist.setClassname(rs.getString("classname"));
					selectlist.setRecord(rs.getInt("record"));
					selectlist.setGrade(rs.getString("grade"));
					selectlist.setScore(rs.getInt("score"));
					select.add(selectlist);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException s) {}
			if(conn != null) try {conn.close();} catch(SQLException s) {}
		}		
		return select;
	}

	//���� �ٿ�
		public  sleepoutAppDTO2 sleepoutApp(String stnum){ //�л������� ����������  ����Ʈ�ϴ³���
			sleepoutAppDTO2 dto = null;
			String dbstnum="";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from student where stnum= ? ");
				pstmt.setString(1, stnum);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					dto = new sleepoutAppDTO2();
					dbstnum = rs.getString("stnum");
					if(dbstnum.equals(stnum)) {
						dto.setStname(rs.getString("stname"));
						dto.setStnum(rs.getString("stnum"));
						dto.setPart(rs.getString("part"));

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
		 	return dto;

		}

		public void sleepoutAppinsert(sleepoutAppDTO dto) {
			try {
				conn = getConnection();
				String sql = "insert into sexnight(no,stnum,outsleep,startday,comeback,secret,name,dreamroom,ho,part,reg_date)"
						+ " values(sn_seq.nextval,?,?,?,?,?,?,?,?,?,?)";// ����ڰ� �Է��ϴ� �ܹڳ����� insert�ϴ� ������
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getStnum());
				pstmt.setString(2, dto.getOutsleep());
				pstmt.setString(3, dto.getStartday());
				pstmt.setString(4, dto.getComeback());
				pstmt.setString(5, dto.getSecret());
				pstmt.setString(6, dto.getName());
				pstmt.setString(7, dto.getDreamroom());
				pstmt.setString(8, dto.getHo());
				pstmt.setString(9, dto.getPart());
				pstmt.setTimestamp(10, dto.getSend());
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
	public  inDTO2 extraadd(String stnum){ //�л������� ����������  ����Ʈ�ϴ³���
		inDTO2 dto = null;
		String dbstnum="";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from student where stnum= ? ");
			pstmt.setString(1, stnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new inDTO2();
				dbstnum = rs.getString("stnum");
				if(dbstnum.equals(stnum)) {
					dto.setStname(rs.getString("stname"));
					dto.setStnum(rs.getString("stnum"));
					dto.setGrade(rs.getString("grade"));
					dto.setPart(rs.getString("part"));
					dto.setZon(rs.getString("zon"));
					dto.setStreet(rs.getString("street"));
					dto.setAddr(rs.getString("addr"));
					dto.setSex(rs.getString("sex"));
					dto.setPhone(rs.getString("phone"));
		
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
	 	return dto;

	}

		public void ininsert(inDTO dto) {//����Ʈ ����� �Բ� �μ�Ʈ�Ѵ�
			try {
				conn = getConnection();
				String sql = "insert into enter(no,stnum,gender,dreamroom,phone,post,street,addr,name,part,grade,email) "
						+ "values(enter_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
				System.out.println(dto.getGender());
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getStnum());
				pstmt.setString(2, dto.getGender());
				pstmt.setString(3, dto.getDreamroom());
				pstmt.setString(4, dto.getPhone());
				pstmt.setString(5, dto.getPost());
				pstmt.setString(6, dto.getStreet());
				pstmt.setString(7, dto.getAddr());
				pstmt.setString(8, dto.getName());
				pstmt.setString(9, dto.getPart());
				pstmt.setString(10, dto.getGrade());
				pstmt.setString(11, dto.getEmail());
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
						pstmt.close();
					} catch (SQLException s) {

					}
			}
		}
		public  outDTO2 extraadd2(String stnum){ //�л������� ����������  ����Ʈ�ϴ³���
			outDTO2 dto = null;
			String dbstnum="";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from student where stnum= ? ");
				pstmt.setString(1, stnum);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					dto = new outDTO2();
					dbstnum = rs.getString("stnum");
					if(dbstnum.equals(stnum)) {
						dto.setStname(rs.getString("stname"));
						dto.setStnum(rs.getString("stnum"));
						dto.setGrade(rs.getString("grade"));
						dto.setPart(rs.getString("part"));
						dto.setZon(rs.getString("zon"));
						dto.setStreet(rs.getString("street"));
						dto.setAddr(rs.getString("addr"));
						dto.setSex(rs.getString("sex"));
						dto.setPhone(rs.getString("phone"));
			
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
		 	return dto;

		}


		public void outinsert(outDTO dto) {//����Ʈ ����� �Բ� �μ�Ʈ�Ѵ�
			try {
				conn = getConnection();
				String sql = "insert into leaving (no,stnum,gender,dormitory,phone,acount,reason,dday,bankname,part,depositer,ho,name,semester,"
						+ "grade,outdate,other,post,street,addr,email)"
						+ "values(leaving_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getStnum());
				pstmt.setString(2, dto.getGender());
				pstmt.setString(3, dto.getDormitory());
				pstmt.setString(4, dto.getPhone());
				pstmt.setString(5, dto.getAcount());
				pstmt.setString(6, dto.getReason());
				pstmt.setString(7, dto.getDday());
				pstmt.setString(8, dto.getBankname());
				pstmt.setString(9, dto.getPart());
				pstmt.setString(10, dto.getDepositer());
				pstmt.setString(11, dto.getHo());
				pstmt.setString(12, dto.getName());
				pstmt.setString(13, dto.getSemester());
				pstmt.setString(14, dto.getGrade());
				pstmt.setString(15, dto.getOutdate());
				pstmt.setString(16, dto.getOther());
				pstmt.setString(17, dto.getPost());
				pstmt.setString(18, dto.getStreet());
				pstmt.setString(19, dto.getAddr());
				pstmt.setString(20, dto.getEmail());

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
						pstmt.close();
					} catch (SQLException s) {
								
					}
			}
		}

		public List<passDTO> list(String stnum) {       //�հ�����ȸ  ���ΰ��� 1
			List<passDTO> list = null;
			int dbok = 0;
			//String dbstnum="";
			try {
				conn = getConnection();
				
				pstmt = conn.prepareStatement("select * from enter where stnum=?");
				pstmt.setString(1, stnum);
				rs = pstmt.executeQuery();
				list = new ArrayList<passDTO>();
				while (rs.next()) {
					dbok= rs.getInt("ok");
					if(dbok > 0) {
						passDTO dto = new passDTO();
						dto.setStnum(rs.getString("Stnum"));
						dto.setGender(rs.getString("gender"));
						dto.setDreamroom(rs.getString("dreamroom"));
						dto.setPhone(rs.getString("phone"));
						dto.setName(rs.getString("name"));
						dto.setPart(rs.getString("part"));
						dto.setHo(rs.getString("ho"));
						list.add(dto);
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
			return list;
		}

	public List<sleepoutcheckDTO> sleepoutlist(String stnum){ //�ܹڽ�����ȸ �޼���
		List<sleepoutcheckDTO> list = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from sexnight where stnum=?");
			pstmt.setString(1, stnum);

			rs = pstmt.executeQuery();
			list = new ArrayList<sleepoutcheckDTO>();
			while (rs.next()) {

					sleepoutcheckDTO dto = new sleepoutcheckDTO();
					dto.setStnum(rs.getString("stnum"));
					dto.setName(rs.getString("name"));
					dto.setPart(rs.getString("part"));
					dto.setStartday(rs.getString("startday"));
					dto.setAllow(rs.getInt("allow"));
					list.add(dto);

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
		return list;
	}
	//�л� �нǹ� ���� �μ�Ʈ
	public void insertArticle1(stuLostDTO article) throws Exception {
		int num=article.getNum(); //���������� ������� �μ�Ʈ�ϴ� ����
	
		int number=0;
		String sql="";
		try {
			conn = getConnection(); 
			pstmt = conn.prepareStatement("select max(num) from adminlost");//num���� ������ �������� ���� ū ���ڸ� ����Ʈ
			rs = pstmt.executeQuery();
			if (rs.next()) 
				number=rs.getInt(1)+1;//�������� 1�̶�� 1+1�ؼ� 2�� �ؿ��ִ� ������� DB�� �μ�Ʈ�Ѵ�	
			else
				number=1; //���� ���� ������ 1�� ���´ٸ�(�Խù��� �ƹ��͵� ���ٸ�) �ѹ� 1�� �ؿ� �ִ� ������� DB�� �μ�Ʈ�Ѵ�

 
			sql = "insert into adminlost(num,writer,subject,reg_date,";
			sql+="content) values(adminlost_seq.NEXTVAL,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setTimestamp(3, article.getReg_date());
			pstmt.setString(4, article.getContent());
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	public int getArticleCount1() throws Exception {//�ش� ���̺� �Խù��� ���� ��Ÿ���� �޼���
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from adminlost");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1); //ī���Ϳ� ��Ÿ���°� ��ƮŸ���̱⿡ ����Ʈ�ι޴´�
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; //�� ī���͸� ���
	}
	public int getArticleCount1(String writer) throws Exception {//��  writer�� �޼��� ������ �𸣰��� 
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from adminlost where writer=?");//�ۼ����� �Խù� ������ ��Ÿ����
			pstmt.setString(1, writer);//
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; //�� ī���͸� ���
	}
	
	public List getArticles1(int start, int end) throws Exception {
		List articleList=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from (select num,writer,subject,reg_date,content,readcount,rownum r from "
					+ "(select *  from adminlost order by num desc) order by num desc ) where r >= ? and r <= ? ");
					pstmt.setInt(1, start); //adminlist�� 
					pstmt.setInt(2, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							stuLostDTO article= new stuLostDTO();
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
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		
		return articleList;
	}
	public stuLostDTO getArticle1(int num) throws Exception {
		stuLostDTO article=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"update adminlost set readcount=readcount+1 where num = ?");//��ȸ���� ������Ʈ�ϴ� �޼���
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
			"select * from adminlost where num = ?"); //������ Num�� ����Ʈ�ϱ�
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new stuLostDTO();
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
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return article;
	}
	public String testDelete1(int num) {//����
		String msg="�����Ͻðڽ��ϱ�?";
		try {
			conn = getConnection();	
			String sql = "delete from adminlost where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.executeUpdate();
			msg= "�����Ǿ����ϴ�.";

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException s) {}
			if(pstmt != null)try {pstmt.close();}catch(SQLException s) {}
			if(conn != null)try {conn.close();}catch(SQLException s) {}
		}
		return msg;
	}
	public stuLostDTO updateGetArticle1(int num) throws Exception {//���� �� �⺻���� ����Ʈ�ؼ� ���� �������
		stuLostDTO article=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"select * from adminlost where num = ?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new stuLostDTO();
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
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return article;
	}
	public int updateArticle1(stuLostDTO article) throws Exception {// ���� �� ������Ʈ
		String writer="";
		String sql="";
		int x=-1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"select * from adminlost where num = ?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()){
				writer= rs.getString("writer"); 
				if(writer.equals(article.getWriter())){
					sql="update adminlost set writer=?,subject=?";
					sql+=",content=? where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getSubject());
					pstmt.setString(3, article.getContent());
					pstmt.setInt(4, article.getNum());
					pstmt.executeUpdate();
					x= 1;
				}else{
					x= 0;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
}





























