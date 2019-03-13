<%@page import="com.jihoon.guestbook.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@page import="com.jihoon.guestbook.vo.GuestbookVo"%>
<%@page import="com.jihoon.guestbook.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	List<GuestbookVo> list = (List<GuestbookVo>)request.getAttribute("list");	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="/guestbook02/gs?a=add" method="post">
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" value=" 확인 "></td>
		</tr>
	</table>
	</form>
	<br>
	<% 
		for(int i = 0; i< list.size(); i++){
			GuestbookVo gv = list.get(i);
	%>	
	<table width=510 border=1>
		<tr>
			<td><%= gv.getNo() %></td>
			<td><%= gv.getName() %></td>
			<td><%= gv.getRegDate() %></td>
			<td><a href="/guestbook02/gs?a=deleteform&no=<%= gv.getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4><%= gv.getMessage() %></td>
		</tr>
	</table>
	<br>
	<% 
		}
	%>	
</body>
</html>