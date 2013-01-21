<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Qpeka</title>
</head>
<body>
<%String pageReq = request.getParameter("page");
if(pageReq == null || pageReq.length() == 0)
	pageReq = "home.jsp";

//response.sendRedirect(request.getContextPath()+"/"+pageReq);

getServletContext().getRequestDispatcher("/"+pageReq).forward(request, response);
%>
</body>
</html>