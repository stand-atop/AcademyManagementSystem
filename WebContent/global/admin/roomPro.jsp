<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="project.global.admin.domDAO" %>

<jsp:useBean id="dto" class="project.global.admin.roomDTO">
	<jsp:setProperty name="dto" property="*"/>
</jsp:useBean>
<%
	String stnum = request.getParameter("stnum"); //�й� 
	String ho = request.getParameter("ho"); //ȣ��
	int ok = Integer.parseInt(request.getParameter("ok")); //ȣ�� ���� �Ϸ�
	domDAO dao = new domDAO(); //domDAO() ��ü ����
	dao.updateRoom(dto); //�� ���� �Ϸ� �� ok=1 �� ������Ʈ
%>
		<h2 style="text-align:center; margin-top:10%;">�й� : <%=stnum %> �� �л��� 
����� <%=ho%>�� ���� �Ϸ��Ͽ����ϴ�.</h2>
	<script language="JavaScript">
		alert("�����Ϸ�");
		//history.go(-1);
	</script>
	<meta http-equiv="Refresh" content="0;url=roomForm.jsp"> <!-- ���ΰ�ħ -->


	
	
