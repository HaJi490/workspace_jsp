package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBCConnect {
	//private으로 하는게 좋음
	//메서드안에 넣어주는게 더 좋음
	public Connection con;//DB연결이 제일 비용이 많이듦
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	//기본 생성자
	public JDBCConnect() {
		
//		Statement stmt = null;
//		PreparedStatement psmt = null;
//		public ResultSet rs;
		
		try {
			//JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//DB 연결
			String url = "jdbc:mysql://localhost:3306/musthave";
			String id = "musthave";
			String pwd = "tiger";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//두번째 생성자
	public JDBCConnect(String driver, String url, String id, String pwd) {
		try {
			//JDBC 드라이버 로드
			Class.forName(driver);
			
			//DB 연결
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자1)");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//세번째 생성자(매개변수를 이용하는 방식이 '두번째 생성자'와 다름)
	//ServletContext #application의 객체타입
	public JDBCConnect(ServletContext application) {
		try {
			//JDBC 드라이버 로드
			String url = application.getInitParameter("MySQLURL");
			String id = application.getInitParameter("MySQLId");
			String pwd = application.getInitParameter("MySQLPwd");
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자2)");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//연결 해제(자원 반납)
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(con != null) con.close();
			
			System.out.println("JDBC 자원 해제");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
