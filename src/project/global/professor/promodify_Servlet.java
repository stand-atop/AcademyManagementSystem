package project.global.professor;

import project.global.professor.professorDAO;

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

@WebServlet("/global/professor/promodify.do")
public class promodify_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html); charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// ���� ���� ���
		String fileSavePath = "proImage\\";
		
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
		promodifyDTO dto = new promodifyDTO();
		

		
		String image = multi.getFilesystemName("image");		// ������ �̹��� ����
		String image2 = multi.getParameter("image2");	// ������ ������־��� �̹��� �����. 
		
		
		
		//System.out.println("image(������ �̹���) : " + image);
		//System.out.println("image2(db�� ����� �̹���) : " + image2);
		
		
		
		if(image == null) {				// ������ ����� �̹����� ������ ���
			dto.setImage(multi.getParameter("image2"));
			
			dto.setProname(multi.getParameter("proname"));
			dto.setProborn(multi.getParameter("proborn"));
			dto.setPropart(multi.getParameter("propart"));
			
			dto.setTel1(multi.getParameter("tel1"));
			dto.setProemail(multi.getParameter("proemail"));
			dto.setZone(multi.getParameter("zone"));
			
			dto.setStreet(multi.getParameter("street"));
			dto.setAddr(multi.getParameter("addr"));
			dto.setAcount(multi.getParameter("acount"));
			
			dto.setPw(multi.getParameter("pw"));
			dto.setId(multi.getParameter("id"));
			dto.setBank(multi.getParameter("bank"));
			dto.setPropartcode(multi.getParameter("propartcode"));
			
			
			/* dao */
			professorDAO dao = new professorDAO();
			dao.proModify(dto);
			
			//System.out.println(msg);
			
			// java ���� jsp �� �̵�
			//request.setAttribute("msg", msg);
			//RequestDispatcher rd = request.getRequestDispatcher("/global/admin/stumodifyPro.jsp");
			//rd.forward(request, response);
			response.sendRedirect("pro modifyPro.jsp");
			

		}else {							// �̹��� ������ ������ ���
			
			file = multi.getFilesystemName("image");
			
			if(file == null) {
				// ���ε� ����
				//System.out.println("���ε� ����");
			}else {
				// ���ε� ����
				
				File file2 = new File(uploadPath + "\\" + image2);
				if(file2.exists()) {
					if(file2.delete()) {
						//System.out.println("���� ���� ����");
					}else {
						//System.out.println("���� ���� ����");
					}
				}else {
					//System.out.println("������ ���� X");
				}
				dto.setImage(image);
				dto.setProname(multi.getParameter("proname"));
				dto.setProborn(multi.getParameter("proborn"));
				dto.setPropart(multi.getParameter("propart"));
				
				dto.setTel1(multi.getParameter("tel1"));
				dto.setProemail(multi.getParameter("proemail"));
				dto.setZone(multi.getParameter("zone"));
				
				dto.setStreet(multi.getParameter("street"));
				dto.setAddr(multi.getParameter("addr"));
				dto.setAcount(multi.getParameter("acount"));
				
				dto.setPw(multi.getParameter("pw"));
				dto.setId(multi.getParameter("id"));
				dto.setBank(multi.getParameter("bank"));
				dto.setPropartcode(multi.getParameter("propartcode"));
				
				/* dao */
				professorDAO dao = new professorDAO();
				dao.proModify(dto);
				
				//System.out.println(msg);
				
				// java ���� jsp �� �̵�
				//request.setAttribute("msg", msg);
				//RequestDispatcher rd = request.getRequestDispatcher("/global/admin/stumodifyPro.jsp");
				//rd.forward(request, response);
				response.sendRedirect("pro modifyPro.jsp");
			}
		}
	}
}
