package project.global.admin;

import project.global.admin.adminDAO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/global/admin/proInfo.do")
public class inputpro_servlet extends HttpServlet {
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
		
		adminDAO dao = new adminDAO();
		
		// ���ε� �� ���� �̸� ������
		String file = multi.getFilesystemName("image");
		
		if(file == null) {
			// ���ε� ���н�
			//System.out.println("���ε� ����"); 
		}else {
			// ���ε� ������
			proinputinfoDTO dto = new proinputinfoDTO();
			
			dto.setImage(multi.getFilesystemName("image"));
			dto.setProname(multi.getParameter("proname"));
			dto.setProborn(multi.getParameter("proborn"));

			dto.setPropart(multi.getParameter("propart"));
			dto.setTel1(multi.getParameter("tel1"));
			dto.setProemail(multi.getParameter("proemail"));
			
			dto.setZone(multi.getParameter("zone"));
			dto.setStreet(multi.getParameter("street"));
			dto.setAddr(multi.getParameter("addr"));
			
			dto.setBank(multi.getParameter("bank"));
			dto.setAcount(multi.getParameter("acount"));
			
			/* ���� id(�й�) �ο��ϱ� : ���� �������(4)+�а���ȣ(2)+������ȣ(3)*/	
			String born = multi.getParameter("proborn");
			born = born.substring(0,4);
			
			String propart = dao.subnum2(multi.getParameter("propart"));
			
			String num = dao.pronumber(multi.getParameter("propart"));
			int temp = Integer.parseInt(num);
			temp++;
			num = String.format("%03d", temp);
			
			if(num != null) {
				dto.setId(born+propart+num);
			}else {
				dto.setId(born+propart+"001");
			}
			
			System.out.println(born + " " + propart + " " + num);
			
			dto.setPw(multi.getParameter("pw"));

			// propart code
			List<subjectDTO> list = dao.partcall();
			for(int i = 0; i<list.size(); i++){
				String part = list.get(i).getSubname();
				if(dto.getPropart().equals(part)){
					dto.setPropartcode(list.get(i).getSubnum());
				}
			}
			

			String msg = dao.proinputinfo(dto);
			
			//System.out.println(msg);
			
			// java ���� jsp �� �̵�
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/global/admin/proinputinfoPro.jsp");
			//rd.forward(request, response);

			response.sendRedirect("proinputinfoPro.jsp");
		}
	}
}
