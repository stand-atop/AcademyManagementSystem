package project.global.admin;

import project.global.admin.adminDAO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

@WebServlet("/global/admin/stuInfo.do")
public class UploadServlet_Stu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html); charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// ���� ���� ���
		String fileSavePath = "stuImage\\";
		
		// ���� ũ�� 10M ����
		int uploadSizeLimit = 10*1024*1024;
		String encType = "UTF-8";
		
		ServletContext context = getServletContext();
		
		String uploadPath = context.getRealPath(fileSavePath);
		
		// upload�� ��ο� ������ �������� ���� ��� ���� ����.
		File upDir = new File(uploadPath);
		if(!upDir.exists()) {
			upDir.mkdir();
			System.out.println("���� ���� �Ϸ�.");
		}
		
		System.out.println(uploadPath);
		
		// request : request ��ü / uploadPath : ���� �� ���ε� �� ���丮
		// uploadSizeLimit : ���ε� ���� ũ�� ���� / encType : ���ڵ� ���
		// new DefaultFileRenamePolic() : ���� �̸� ���� �� ���ο� �̸� �ο� ���
		MultipartRequest multi = new MultipartRequest(request, uploadPath, uploadSizeLimit, encType, new DefaultFileRenamePolicy());
		
		// ���ε� �� ���� �̸� ������
		String file = multi.getFilesystemName("image");
		
		if(file == null) {
			// ���ε� ���н�
			System.out.println("���ε� ����"); 
		}else {
			// ���ε� ������
			stuInfoDTO dto = new stuInfoDTO();
			adminDAO dao = new adminDAO();
			
			dto.setStname(multi.getParameter("stname"));
			String i = multi.getParameter("year1");
			int j = Integer.parseInt(i);
			dto.setYear1(j);
			System.out.println("�� : " + dto.getYear1());
			
			i = multi.getParameter("mon");
			j = Integer.parseInt(i);
			dto.setMon(j);
			System.out.println("�� : " + dto.getMon());
			
			i = multi.getParameter("day1");
			j = Integer.parseInt(i);
			dto.setDay1(j);
			System.out.println("�� : " + dto.getDay1());
			
			// �й� �ο��ϱ�
			Calendar calendar = new GregorianCalendar(Locale.KOREA);
			int year = calendar.get(Calendar.YEAR);
			String Year = Integer.toString(year);
						
			String part = dao.subnum2(multi.getParameter("part"));	// �а���ȣ �������

			String num = dao.partnum(multi.getParameter("part"));

			if(num != null) {
				int temp = Integer.parseInt(num);
				temp++;
				num = Integer.toString(temp);
				dto.setStnum(num);
			}else {
				dto.setStnum(Year+part+"01");
			}
			
			dto.setPart(multi.getParameter("part"));
			dto.setPhone(multi.getParameter("phone"));
			dto.setZon(multi.getParameter("zon"));
			dto.setStreet(multi.getParameter("street"));
			dto.setAddr(multi.getParameter("addr"));
			dto.setBank(multi.getParameter("bank"));
			dto.setAcnt(multi.getParameter("acnt"));
			
			String gra = multi.getParameter("grade");
			int grade = Integer.parseInt(gra);
			dto.setGrade(grade);
			
			dto.setImage(file);
			dto.setFloor(multi.getParameter("floor"));
			
			//acsday
			
			dto.setSex(multi.getParameter("sex"));
			dto.setDomiuse(multi.getParameter("domiuse"));
			dto.setPw(multi.getParameter("pw"));
			dto.setEmail(multi.getParameter("email"));
			
			System.out.println("<br> �۾��� : " + dto.getStname());
			System.out.println("<br> ��   ��  : " + dto.getFloor());
			System.out.println("<br> ��   ��  : " + dto.getAcnt());
			System.out.println("<br> ��   ��  : " + dto.getGrade());
			System.out.println("<br> stnum  : " + dto.getStnum());
			System.out.println("<br> image : " + file);
			
			
			String msg = dao.insertStudent(dto);
			
			System.out.println(msg);
			
			// java ���� jsp �� �̵�
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/global/admin/stuInfoPro.jsp");
			//rd.forward(request, response);
			response.sendRedirect("stuInfoPro.jsp");
		}
	}
}
