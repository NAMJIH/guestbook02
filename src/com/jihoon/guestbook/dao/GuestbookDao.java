package com.jihoon.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jihoon.guestbook.vo.GuestbookVo;

public class GuestbookDao {
	public void delete(GuestbookVo gv) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?sslMode=DISABLED&characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			//3. 쿼리 준비
			String sql = "delete from guestbook where no=? and password=?";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			pstmt.setLong(1, gv.getNo());
			pstmt.setString(2, gv.getPassword());
			
			//5. sql 실행
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	public void insert(GuestbookVo gv ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
		
			//2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?sslMode=DISABLED&characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. 쿼리 준비
			String sql = "insert into guestbook values(null, ?, ?, ?, now() )";
			pstmt = conn.prepareStatement(sql);
//			System.out.println(gv.getPassword());
		
			//4. 바인딩
			pstmt.setString(1, gv.getName());
			pstmt.setString(2, gv.getPassword());
			pstmt.setString(3, gv.getMessage());
			
			//5. sql 실행
			pstmt.executeUpdate();

		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 에러" + e);
		} catch(SQLException e) {
			System.out.println("error" + e);
		}

	}
	
	public List<GuestbookVo> getList(){
		
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			//2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?sslMode=DISABLED&characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. 쿼리 준비
			String sql = "select no, name, message, date_format(reg_date, '%Y-%m-%d %h:%i:%s') from guestbook";
			pstmt = conn.prepareStatement(sql);
			
			//4. sql 실행
			rs = pstmt.executeQuery();
			
			//5. 결과 받아오기
			while(rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
				String message = rs.getString(3);
				String regDate = rs.getString(4);
	
				GuestbookVo guestbookVo = new GuestbookVo();
				guestbookVo.setNo(no);
				guestbookVo.setName(name);
				guestbookVo.setMessage(message);
				guestbookVo.setRegDate(regDate);
				
				list.add(guestbookVo);
			}

		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch(SQLException e) {
			System.out.println("error:"+ e);
			
		}
		return list;
	}
}