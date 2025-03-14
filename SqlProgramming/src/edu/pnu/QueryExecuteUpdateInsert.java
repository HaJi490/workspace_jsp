package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryExecuteUpdateInsert {
	public static void main(String[] args) {

		// PreparedStatement 객체생성
		QueryPreparedStatement qs = new QueryPreparedStatement();

		// DB연결 객체 생성
		Connection con = null;
		PreparedStatement psm = null;
			
		try {
				//trycatch 안해주면 오류뜸 >> 다른 오류니까 Exception으로 묶어주면됨 
				try {
					// 프로그램 로드
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					System.out.println("실행실패1 : " + e.getMessage());
				}
		
				// 로컬호스트의 3306 포트에 있는 myfirstdb 데이터에 접속
				String url = "jdbc:mysql://localhost:3306/myfirstdb";
				// musthave 권한으로 url 가져옴
				con = DriverManager.getConnection(url, "musthave", "tiger");
				
				String name = "a";
				String mobile = "010-5555-5555";// '-'부호 있으니까 String
				// PreparedStatement 사용
				String sql = "insert into phonebook(name, mobile) values(?, ?)";
				psm= con.prepareStatement(sql);
				
				//넣기
				for(int i = 0; i< 20; i++) { 
				psm.setString(1, name);
				psm.setString(2, mobile);
				psm.executeUpdate();
				}
				
		} catch (SQLException e) {
			System.out.println("실행실패2 : " + e.getMessage());
		}finally {
			try {
				if(psm != null) psm.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
				
	
	}

}
