package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecuteUpdateUpdate {
	public static void main(String[] args) {
		//PreparedStatement 객체 생성
		QueryStatement qs = new QueryStatement(); 
		
		//DB 연결객체 생성
		Connection con = null;
		Statement stm = null;
		int rs = 0;
		
		
		try {
			//sql프로그램 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			//로컬호스트의 3306 포트에 있는 myfirstdb 데이터에 접속
			String url = "jdbc:mysql://localhost:3306/myfirstdb";
				// musthave 권한으로 url 가져옴
				con = DriverManager.getConnection(url, "musthave", "tiger");
				String sql = "update phonebook set ";
				stm = con.createStatement();
				String home = "update 051";
				
				for(int i = 12 ; i <= 52 ; i++) {
					int id = i;
					rs = stm.executeUpdate(sql + String.format("home='%s' where id=%d", home, id));
				}
			
			
			System.out.println(rs + "개가 update되었습니다.");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("예외발생1 : " + e.getMessage());
		}finally {
				try {
					if(stm != null) stm.close();
					if(con != null) con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}

}
