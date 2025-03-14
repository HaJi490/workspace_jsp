package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// static Connection getConnection(String url, String user, String password)
			Connection con = DriverManager.getConnection(
					//localhost대신 다른컴퓨터 ip주소 넣으면 다른컴퓨터 접속(ip주소: cmd>ipconfig)
					"jdbc:mysql://10.125.121.172:3306/world",
					"musthave",
					"tiger");
			System.out.println("연결 성공");
			con.close();
			
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}
	}
}
