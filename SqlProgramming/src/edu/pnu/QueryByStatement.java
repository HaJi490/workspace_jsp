package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryByStatement {
	public static void main(String[] args) {
		//메서드로 만들어서 메인에서 호출
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//프로그램 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url =  "jdbc:mysql://localhost:3306/world";
			//위의 localhost에서 Connection 가져오기
			con = DriverManager.getConnection(url, "root", "tiger");
			//Statement 질의객체
			st = con.createStatement();
			//executeQuery(질의할 내용)를 통해 statement 질의
			//입력한 순서대로 결과가 나옴
			rs = st.executeQuery("select id, name, countrycode, "
								+ "district, population from city");
			while(rs.next()) {
				//읽어오는 명령(getString으로 받아도됨)
				System.out.print(rs.getInt("id") + ",");
				System.out.print(rs.getString("name") + ",");
				System.out.print(rs.getString("countrycode") + ",");
				System.out.print(rs.getString("district") + ",");
				System.out.print(rs.getInt("population") + "\n");
			}
		}catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		//정상적으로 실행되든 안되든 실행됨
		}finally {
			try {
				//프로그램 닫기
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			}catch(Exception e) {}
			
		}
	}

}
