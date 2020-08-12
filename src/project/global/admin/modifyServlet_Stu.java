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

@WebServlet("/global/admin/modifyInfo.do")
public class modifyServlet_Stu extends HttpServlet {
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
			//System.out.println("���� ���� �Ϸ�.");
		}
		
		//System.out.println(uploadPath);
		
		// request : request ��ü / uploadPath : ���� �� ���ε� �� ���丮
		// uploadSizeLimit : ���ε� ���� ũ�� ���� / encType : ���ڵ� ���
		// new DefaultFileRenamePolic() : ���� �̸� ���� �� ���ο� �̸� �ο� ���
		MultipartRequest multi = new MultipartRequest(request, uploadPath, uploadSizeLimit, encType, new DefaultFileRenamePolicy());
		
		String file;
		stumodifyDTO dto = new stumodifyDTO();
		

		
		String image = multi.getFilesystemName("image");		// ������ �̹��� ����
		String image2 = multi.getParameter("image2");	// ������ ������־��� �̹��� �����. 
		
		
		
		System.out.println("image(������ �̹���) : " + image);
		System.out.println("image2(db�� ����� �̹���) : " + image2);
		
		
		
		if(image == null) {				// ������ ����� �̹����� ������ ���
			dto.setImage(image2);
			
			dto.setStname(multi.getParameter("stname"));
			String i = multi.getParameter("year1");
			int j = Integer.parseInt(i);
			dto.setYear1(j);
			
			i = multi.getParameter("mon");
			j = Integer.parseInt(i);
			dto.setMon(j);
			
			i = multi.getParameter("day1");
			j = Integer.parseInt(i);
			dto.setDay1(j);
			
			dto.setStnum(multi.getParameter("stnum"));
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
			dto.setFloor(multi.getParameter("floor"));
			
			//acsday
			
			dto.setSex(multi.getParameter("sex"));
			System.out.println(dto.getSex());
			dto.setDomiuse(multi.getParameter("domiuse"));
			dto.setPw(multi.getParameter("pw"));
			dto.setEmail(multi.getParameter("email"));
			
			
			/* dao */
			adminDAO dao = new adminDAO();
			String msg = dao.stumodify(dto);
			
			//System.out.println(msg);
			
			// java ���� jsp �� �̵�
			//request.setAttribute("msg", msg);
			//RequestDispatcher rd = request.getRequestDispatcher("/global/admin/stumodifyPro.jsp");
			//rd.forward(request, response);
			response.sendRedirect("stumodifyPro.jsp");
			

		}else {							// �̹��� ������ ������ ���
			dto.setImage(image);
			file = multi.getFilesystemName("image");
			
			if(file == null) {
				// ���ε� ����
				//System.out.println("���ε� ����");
			}else {
				// ���ε� ����
				
				File file2 = new File(uploadPath + "\\" + image2);
				System.out.println(file2);
				if(file2.exists()) {
					if(file2.delete()) {
						//System.out.println("���� ���� ����");
					}else {
						//System.out.println("���� ���� ����");
					}
				}else {
					//System.out.println("������ ���� X");
				}
				
				dto.setStname(multi.getParameter("stname"));
				String i = multi.getParameter("year1");
				int j = Integer.parseInt(i);
				dto.setYear1(j);
				
				i = multi.getParameter("mon");
				j = Integer.parseInt(i);
				dto.setMon(j);
				
				i = multi.getParameter("day1");
				j = Integer.parseInt(i);
				dto.setDay1(j);
				
				dto.setStnum(multi.getParameter("stnum"));
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
				System.out.println(dto.getSex());
				dto.setDomiuse(multi.getParameter("domiuse"));
				dto.setPw(multi.getParameter("pw"));
				dto.setEmail(multi.getParameter("email"));
				
				/* dao */
				adminDAO dao = new adminDAO();
				String msg = dao.stumodify(dto);
				
				//System.out.println(msg);
				
				// java ���� jsp �� �̵�
				//request.setAttribute("msg", msg);
				//RequestDispatcher rd = request.getRequestDispatcher("/global/admin/stumodifyPro.jsp");
				//rd.forward(request, response);
				response.sendRedirect("stumodifyPro.jsp");
			}
		}
	}
}
