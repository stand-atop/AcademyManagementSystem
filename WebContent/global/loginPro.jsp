<%@ page contentType="text/html;charset=euc-kr" %>
<%@ page import = "project.global.admin.globalDAO" %>

<% request.setCharacterEncoding("euc-kr");%>


<%
	String id = request.getParameter("id");
	String pw  = request.getParameter("pw");
	String a = request.getParameter("ppp");		// ���� 
	globalDAO manager = globalDAO.getInstance();//���߿� �������̿��� �̸�����
    int check= manager.globalCheck(id, pw);
	
	
	if(check==1 && a.equals("1")){
		session.setAttribute("studentId", id);
		response.sendRedirect("/project/global/student/Main.jsp");
	}else if(check==2 && a.equals("2")){
		session.setAttribute("professorId",id);
		response.sendRedirect("/project/global/professor/professorMain2.jsp");
	}else if(check==3 && a.equals("2")){
		session.setAttribute("adminId",id);
		response.sendRedirect("/project/global/admin/adminMain.jsp");
	}else if(check == 0){%>
	<!-- <script> 
	  alert("���̵�/��й�ȣ�� ���� �ʽ��ϴ�.. ��3��");
      history.go(-1);//�ڷΰ���, ������������ ���ư���
	</script> -->
	<script>
		document.location.href="errorPage.jsp";
	</script>
<%	}else{%>
	<script>
		document.location.href="errorPage.jsp";
	</script>
<%} %>

