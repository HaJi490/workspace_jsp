package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QueryPreparedStatement {
	static PreparedStatement pst = null;
	static ResultSet rs = null;
	static Scanner scan = new Scanner(System.in);
	
//	public QueryPreparedStatement(PreparedStatement pst, ResultSet rs) {
//		pst = null;
//		rs = null;
//	}
	
	 //1. 인구 수를 입력받아서 그보다 많은 인구를 가진 도시를 검색
		static void Mission1(Connection con) {
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery("");

			try {
				System.out.print("1. 인구수를 입력하세요");
				int pop = scan.nextInt();
				pst = con.prepareStatement("select name, population from city where population > ?");

				pst.setInt(1, pop);
				rs = pst.executeQuery();

				// close위치, finally하고 넣어야하는지, 차이점

				//pst.close();
				while (rs.next()) {
					System.out.print(rs.getString("name") + ", ");
					System.out.println(rs.getInt("population"));
				}
				//rs.close();
				
			} catch (SQLException e) {
				System.out.println("예외발생2 : " + e.getMessage());

				// 예외가 발생안하면 상관없지만 발생하는 경우를 위해
				// ->finally 안에 close를 넣어야함
			} finally {
				try {
					if(pst != null)pst.close();
					if(rs != null) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Mission1 end" + "-".repeat(30));
		}
		
	
	//2. 국가 명의일부 또는 국가코드를 입력받아서 해당국가의 도시의 이름과 인구를 검색
	static void Mission2(Connection con) {
		try {
			System.out.print("2. 국가 명의일부 또는 국가코드 : ");
			String pop = scan.next();
			pst = con.prepareStatement("select city.name, city.population "
										+ "from city join country "
										+ "on city.countrycode = country.code "
										+ "where country.name like ? or country.code = ?" );
			
			pst.setString(1, "%"+ pop +"%");
			pst.setString(2, pop);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getString("city.name") + ", ");
				System.out.println(rs.getInt("city.population") );
			}
			
		} catch (SQLException e) {
			System.out.println("예외발생2 : " + e.getMessage());
		}finally {
			try {
				if(pst != null)pst.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Mission2 end" + "-".repeat(30));
	}
	
	// 3. 대륙을 입력받아서 해당 대륙에 위치한 국가를 검색
	static void Mission3(Connection con) {

		try {
			System.out.print("3. 대륙 입력: ");
			String pop = scan.nextLine();
			pst = con.prepareStatement("select name, continent from country where continent = ?" );
			
			pst.setString(1, pop);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getString("name") + ", ");
				System.out.println(rs.getString("continent") );
			}
			
		} catch (SQLException e) {
			System.out.println("예외발생2 : " + e.getMessage());
		}finally {
			try {
				if(pst != null)pst.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Mission3 end" + "-".repeat(30));
	}
	
	 //4. 넓이(10,0002 km)를 입력받아서 입력값보다 작은면적을가진 국가의 이름과 면적을 면적오름차순으로 검색
	static void Mission4(Connection con) {
		try {
			System.out.print("4. 넓이 입력: ");
			int pop = scan.nextInt();
			pst = con.prepareStatement("select name, surfaceArea from country where SurfaceArea < ?" );
			
			pst.setInt(1, pop);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getString("name") + ", ");
				System.out.println(rs.getInt("SurfaceArea") );
			}
			
		} catch (SQLException e) {
			System.out.println("예외발생2 : " + e.getMessage());
		}finally {
			try {
				if(pst != null)pst.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Mission4 end" + "-".repeat(30));
	}
	
	// 5. 대한민국의 District를 입력 받아서 해당 지역에 있는 모든 도시를 검색
	static void Mission5(Connection con) {
		try {
			System.out.print("5. 지역 입력: ");
			String pop = scan.next();
			pst = con.prepareStatement("select name, district from city where district = ?" );
			
			pst.setString(1, pop);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getString("name") + ", ");
				System.out.println(rs.getString("district") );
			}
			
		} catch (SQLException e) {
			System.out.println("예외발생2 : " + e.getMessage());
		}finally {
			try {
				if(pst != null)pst.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Mission5 end" + "-".repeat(30));
	}
	
	// 6. 언어를 입력받아서 해당언어가 국가공식언어인 국가를 검색
	static void Mission6(Connection con) {

		try {
			System.out.println("6. 언어 입력: ");
			String pop = scan.next();
			pst = con.prepareStatement("select country.name, countrylanguage.language, countrylanguage.isOfficial "
										+ "from countrylanguage join country "
										+ "on countrylanguage.countrycode = country.code"
										+ " where countrylanguage.language = ? and isOfficial = 't'" );
			
			pst.setString(1, pop);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getString("country.name") + ", ");
				System.out.print(rs.getString("countrylanguage.language") + ", " );
				System.out.println(rs.getString("countrylanguage.isOfficial") );
			}
			
		} catch (SQLException e) {
			System.out.println("예외발생2 : " + e.getMessage());
		}finally {
			try {
				if(pst != null)pst.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Mission6 end" + "-".repeat(30));
	}
	
	// 7. CountryLanguage에서 사용자가 입력 비율 이상인 언어의 국가 코드와 비율을 검색
	static void Mission7(Connection con) {

		try {
			System.out.println("7. 언어사용 비율: ");
			int pop = scan.nextInt();
			pst = con.prepareStatement("select countrycode, percentage from countrylanguage where percentage > ?" );
			
			pst.setInt(1, pop);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getString("countrycode") + ", ");
				System.out.println(rs.getInt("percentage") );
			}
			
		} catch (SQLException e) {
			System.out.println("예외발생2 : " + e.getMessage());
		
		}finally {
			try {
				if(pst != null)pst.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Mission7 end" + "-".repeat(30));
	}
	
	public static void main(String[] args) throws SQLException {
		QueryPreparedStatement qs = new QueryPreparedStatement();
		
		// DB연결 객체 생성
		Connection con = null;
		
		try {
			// 프로그램 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			//로컬호스트의 3306 포트에 있는 world 데이터에 접속
			String url = "jdbc:mysql://localhost:3306/world";
			//root 권한으로 url 가져옴
			con = DriverManager.getConnection(url, "musthave", "tiger");
			
//			System.out.println("실행할 미션입력[exit:0] :");
//			while(true){
//				Scanner scan = new Scanner(System.in);
//				int pop = scan.nextInt();
//				switch(pop)
			//첫번째 미션 메서드를 호출할때 파라미터로 DB 연결 객체(con)을 넘겨줌
					//case 1 :
						qs.Mission1(con);
						qs.Mission2(con);
						qs.Mission3(con);
						qs.Mission4(con);
						qs.Mission5(con);
						qs.Mission6(con);
						qs.Mission7(con);
					//default :
						//System.out.println("0-7사이의 숫자를 입력하세요");
					//if(pop == 0) break;
			//}
			//System.out.println("실행종료");
		
		}catch(SQLException | ClassNotFoundException e){
			System.out.println("연결 실패 1 : " + e.getMessage());

		}finally {
			if(con != null) con.close();
		}
		
		
	}

}
