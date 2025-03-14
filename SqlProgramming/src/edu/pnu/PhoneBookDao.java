package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PhoneBookDao {
	private static Scanner sc = new Scanner(System.in);
	//로컬호스트의 3306포트에 있는 myfirstdb 데이터에 접속
	private static String url = "jdbc:mysql://localhost:3306/myfirstdb";
	static int rs = 0;
	
	static void insertPhonebook(Connection con) {
		QueryPreparedStatement ps = new QueryPreparedStatement();
		
		String sql = "insert into phonebook(name, mobile, company) values(?, ?, ?)";
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement(sql);
			System.out.print("이름 입력 : ");
			String name = sc.next();
			String mobile = "010-6666-6666";
			String company = "Dao";
			
			
			psm.setString(1, name);
			psm.setString(2, mobile);
			psm.setString(3, company);
			rs = psm.executeUpdate();
			System.out.println("실행완료 :" + rs + "개 입력완료" );
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("insert 실행실패1 : " + e.getMessage());
		}finally {
			try {
				if(psm != null) psm.close();
			} catch (SQLException e) {
				System.out.println("insert 실행실패2 : " + e.getMessage());
			}
		}
	}
	
	static void updatePhonebook(Connection con) {
		QueryStatement qs = new QueryStatement();
		
		String sql = "update phonebook set ";
		Statement stm = null;
		try {
			stm = con.createStatement();
			System.out.print("업데이트할 id : ");
			int id = sc.nextInt();
			String home = "DaoUpdate 051";
			rs =stm.executeUpdate(sql + String.format("home= '%s' where id= %d", home, id));
			System.out.println("실행완료 : " + rs + "개 업데이트 완료");
			
		} catch (SQLException e) {
			System.out.println("update 실행실패1 : " + e.getMessage());
		}finally {
			try {
				if(stm != null) stm.close();
			} catch (SQLException e) {
				System.out.println("update 실행실패2 : " + e.getMessage());
			}
		}
	}
	
	static void deletePhonebook(Connection con) {
		QueryPreparedStatement ps = new QueryPreparedStatement();
		
		String sql = "delete from phonebook where id=?";
		//초기화
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement(sql);
			System.out.print("삭제할 id : ");
			int id = sc.nextInt();
			
			psm.setInt(1, id);
			rs = psm.executeUpdate();
			System.out.println("실행완료 : " + rs +"개 삭제 완료" );
		} catch (SQLException e) {
			System.out.println("delete 실행실패1 : " + e.getMessage());
		}finally {
			try {
				if(psm != null) psm.close();
			} catch (SQLException e) {
				System.out.println("delete 실행실패2 : " + e.getMessage());
			}
		
		}
		
	}
	
	static void selectAllPhonebook(Connection con) {
		QueryPreparedStatement ps = new QueryPreparedStatement();
		
		String sql = "select * from phonebook where id > ?";
		PreparedStatement psm = null;
		ResultSet selRs = null;
		try {
			psm = con.prepareStatement(sql);
			System.out.println("출력할 id : ");
			int id = sc.nextInt();
			
			psm.setInt(1, id);
			selRs = psm.executeQuery();
			
			while(selRs.next()) {
				System.out.print(selRs.getInt("id") + ", ");
				System.out.print(selRs.getString("name") + ", ");
				System.out.print(selRs.getString("mobile") + ", ");
				System.out.print(selRs.getString("home") + ", ");
				System.out.print(selRs.getString("company") + ", ");
				System.out.println(selRs.getString("email"));
			}
			System.out.println("실행완료 ");
		} catch (SQLException e) {
			System.out.println("select 실행실패1 : " + e.getMessage());
		}finally {
			try {
				if(selRs!= null) selRs.close();
				if(psm != null) psm.close();
			} catch (SQLException e) {
				System.out.println("select 실행실패2 : " + e.getMessage());
			}
		}
		
	}
	
	static void nativeQuery(Connection con) {
		QueryStatement ps = new QueryStatement();
		
		//앞에서 next()쓰면 한번더해줘야 '\n'이되서 정상적으로 사용가능
		sc.nextLine();
		System.out.print("mySql> ");
		String sql = sc.nextLine();
		Statement stm = null;
		ResultSet selRs = null;
		
		
		//String str = sql.substring(1,1);//1-1.!!!!!!!!!!!
		try {
			stm = con.createStatement();
			//char s = 's';
			
			//select
			//str.equalsIgnoreCase("s")//1-2.!!!!!!!!!!!!!!
			//sql.toUpperCase().charAt(0) == 'S' //2.!!!!!!!!!!!!!!!!
			if(sql.toUpperCase().charAt(0) == 'S') { 
				
				selRs = stm.executeQuery(sql);
				
				while(selRs.next()) {
					System.out.print(selRs.getInt("id") + ", ");
					System.out.print(selRs.getString("name") + ", ");
					System.out.print(selRs.getString("mobile") + ", ");
					System.out.print(selRs.getString("home") + ", ");
					System.out.print(selRs.getString("company") + ", ");
					System.out.println(selRs.getString("email"));
				}
				System.out.println("select 실행완료");
				
			
			//insert, update, delete
			//내가 했던거 sql.charAt(0) != 's'
			} else {
				rs = stm.executeUpdate(sql);
				
				System.out.println(rs+ "개 실행완료");
				
				}
			
		}catch (SQLException e) {
			System.out.println("native 실행실패1 : " + e.getMessage());
		}finally {
			try {
				if(selRs != null) selRs.close();
				if(stm != null) stm.close();
			} catch (SQLException e) {
				System.out.println("native 실행실패2 : " + e.getMessage());
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		//DB연결 객체 생성
		Connection con = null;
		
		try {
			//프로그램 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			//musthave 권한으로 url 가져옴
			con = DriverManager.getConnection(url, "musthave", "tiger");
			boolean flag = true;
			while(flag) {
				System.out.println("[I]nsert/[U]pdate/[D]elete/[S]elect/[N]ative/[Q]uit:");
				char c = sc.next().toUpperCase().charAt(0);
				switch(c) {
				case 'I' : insertPhonebook(con); break;
				case 'U' : updatePhonebook(con); break;
				case 'D' : deletePhonebook(con); break;
				case 'S' : selectAllPhonebook(con); break;
				case 'N' : nativeQuery(con); break;
				case 'Q' : flag = false; break;
				}
			}
			System.out.println("실행종료");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("예외발생 1 : " + e.getMessage());
		}finally {
				try {
					if(con != null) con.close();
				} catch (SQLException e) {
					System.out.println("예외발생 2 :" + e.getMessage());
				}
		}
		
		
		
		
	}

}
