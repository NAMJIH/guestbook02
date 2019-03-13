package com.jihoon.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jihoon.guestbook.dao.GuestbookDao;
import com.jihoon.guestbook.vo.GuestbookVo;

@WebServlet("/gs")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("a");
		
		if("add".equals(action)) {
			// 한글 깨짐;
			request.setCharacterEncoding("UTF-8");
			// 1. 데이터 받기
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("message");
			
			GuestbookVo guestbookVo = new GuestbookVo();
			guestbookVo.setName(name);
			guestbookVo.setPassword(password);
			guestbookVo.setMessage(message);
			
			// 2. 받은 데이터를 DB 로 insert 시킨다
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.insert(guestbookVo);
			
			// 3. 들어간 리스트를 다시 보여준다.
			response.sendRedirect("/guestbook02/gs");

		} else if ("deleteform".equals(action)) { 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
		
		} else if("delete".equals(action)) { 
			request.setCharacterEncoding("UTF-8");
		
			//1. 데이터를 받는다.
			long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestbookVo guestbookVo = new GuestbookVo();
			guestbookVo.setNo(no);
			guestbookVo.setPassword(password);
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.delete(guestbookVo);

			response.sendRedirect("/guestbook02/gs");
	
		
	 	} else {
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> list = guestbookDao.getList();	
		
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/list.jsp");
			rd.forward(request, response);
		}	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
