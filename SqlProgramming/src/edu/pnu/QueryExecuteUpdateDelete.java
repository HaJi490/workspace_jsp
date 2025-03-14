package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryExecuteUpdateDelete {
	public static void main(String[] args) {
		//PreparedStatement 사용
		QueryPreparedStatement qs = new QueryPreparedStatement();
		
		//DB 연결객체 생성
		Connection con = null;
		PreparedStatement psm =null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			String url = "jdbc:mysql://localhost:3306/myfirstdb";
			
			con = DriverManager.getConnection(url, "musthave", "tiger");
			
			String sql = "delete from phonebook where id>=40";
			psm = con.prepareStatement(sql);
			
			psm.executeUpdate();
			
			System.out.println("실행완료");
		}catch(Exception e) {
			System.out.println("실행실패 2 :" + e.getMessage());
		}finally {
			try {
				//1. 제일 가까운거부터 close 해주는게좋음 
				//> 큰거부터 닫으면 session닫혔다고 오류뜰수도 있고 열린채로 닫힐수 있기때문
				//2. null일 때 실행하면 오류 뜨니까 if문 붙여주기
				if(psm != null)psm.close();
				if(con != null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
