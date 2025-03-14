package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class QueryStatement {
	// 1. 인구 수를 입력받아서 그보다많은인구를 가진 도시를 검색
	static void Mission1(Connection con) {
		Scanner scan = new Scanner(System.in);
		//st= con.createStatement();
		Statement st = null;
		ResultSet rs = null;
		try {
			System.out.print("1. 인구수 :");
			//인구수를 입력받음
			int pop = scan.nextInt();
			
			//질의 객체 생성(Statement)
			st = con.createStatement();
			
			//질의 실행(st.executeQuery)
			rs = st.executeQuery("select name, population from city where population >" + pop);
			
			//커서 프로세싱 => 결과출력(ResultSet)
			while(rs.next()) {
				System.out.print(rs.getString("name" )+ ", ");
				System.out.println(rs.getInt("population"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 실패1 : " + e.getMessage());
		
		//리소스 해제(rs.close(), st.close())
		}finally {
			try {
				if(rs != null)rs.close();
				if(st != null)st.close();
			} catch (SQLException e) {
				System.out.println("연결 실패2 : " + e.getMessage());
			}
		}
		System.out.println("Mission1 end" + "-".repeat(30));
	}
	
	
	
	//2. 국가명의 일부 또는 국가코드를 입력받아서 해당국가의 도시의 이름과 인구를 검색
	static void Mission2(Connection con) {
		Scanner scan = new Scanner(System.in);
		Statement st = null;
		ResultSet rs = null;
		try {
			System.out.print("2. 국가명 :");
			//국가명을 입력받음
			String pop = scan.next();
			
			//질의 객체 생성(Statement)
			st = con.createStatement();
			
			//질의 실행(st.executeQuery)
			rs = st.executeQuery("select city.name, city.population "
								+ "from city join country "
								+ "on city.countrycode=country.code "
								+ "where city.countrycode ='" +pop+"' or country.name like '%" +pop+"%'" );
			
			//커서 프로세싱 => 결과출력(ResultSet)
			while(rs.next()) {
				System.out.print(rs.getString("city.name")+", ");
				System.out.println(rs.getString("city.population"));
			}
		} catch (SQLException e) {
			System.out.println("연결 실패1 : " + e.getMessage());
		
		//리소스 해제(rs.close(), st.close())
		}finally {
			try {
				if(rs != null) rs.close();
				if(rs != null) st.close();
			} catch (SQLException e) {
				System.out.println("연결 실패2 : " + e.getMessage());
			}
		}
		System.out.println("Mission2 end"+ "-".repeat(30));
	}
	
	// 3. 대륙을 입력받아서 해당 대륙에 위치한 국가를 검색(america안됨)!!!!!!!!!!!!
		static void Mission3(Connection con) {
			Scanner scan = new Scanner(System.in);
			Statement st = null;
			ResultSet rs = null;
			try {
				
				System.out.print("3. 대륙이름 : ");
				//대륙을 입력받음
				//next() : space, tap, enter로 따로 인식
				String pop = scan.nextLine();
				
				//질의 객체 생성(Statement)
				st = con.createStatement();
				
				//질의 실행(st.executeQuery)
				rs = st.executeQuery("select name, continent from country where continent = '"+ pop + "'" );
				
				//커서 프로세싱 => 결과출력(ResultSet)
				
				while(rs.next()) {
					System.out.print(rs.getString("name") + ", ");
					System.out.println(rs.getString("Continent"));
				}
			} catch (SQLException e) {
				System.out.println("연결 실패1 : " + e.getMessage());
			
			//리소스 해제(rs.close(), st.close())
			}finally {
				try {
					rs.close();
					st.close();
				} catch (SQLException e) {
					System.out.println("연결 실패2 : " + e.getMessage());
				}
			}
			System.out.println("Mission3 end"+ "-".repeat(30));
		}
		
		// 4. 넓이(10,0002 km)를 입력 받아서 입력 값보다 작은 면적을 가진 국가의 이름과 면적을 면적오름차순으로 검색해서 출력
		static void Mission4(Connection con) {
			Statement st = null;
			ResultSet rs = null;
			try {
				
				System.out.println("4. 국가면적 < 100000인 국가 ");
				
				//질의 객체 생성(Statement)
				st = con.createStatement();
				
				//질의 실행(st.executeQuery)
				rs = st.executeQuery("select name, SurfaceArea from country where SurfaceArea < 100000 order by Surfacearea asc" );
				
				//커서 프로세싱 => 결과출력(ResultSet)
				while(rs.next()) {
					System.out.print(rs.getString("name") + ", ");
					System.out.println(rs.getInt("surfacearea"));
				}
			} catch (SQLException e) {
				System.out.println("연결 실패1 : " + e.getMessage());
			
			//리소스 해제(rs.close(), st.close())
			}finally {
				try {
					rs.close();
					st.close();
				} catch (SQLException e) {
					System.out.println("연결 실패2 : " + e.getMessage());
				}
			}
			System.out.println("Mission4 end"+ "-".repeat(30));
		}
		
		
		//5. ??대한민국의?? District를 입력받아서 해당 지역에 있는 모든도시를검색(예:‘Kyonggi’)
		static void Mission5(Connection con) {
			Scanner scan = new Scanner(System.in);
			Statement st = null;
			ResultSet rs = null;
			try {
				//district 입력받음
				System.out.print("5. 지역입력 : ");
				String pop = scan.next();
				
				//질의 객체 생성(Statement)
				st = con.createStatement();
				
				//질의 실행(st.executeQuery)
				rs = st.executeQuery("select name, District from city where District='" + pop + "'" );
				
				//커서 프로세싱 => 결과출력(ResultSet)
				while(rs.next()) {
					System.out.print(rs.getString("name") + ", ");
					System.out.println(rs.getString("District"));
				}
			} catch (SQLException e) {
				System.out.println("연결 실패1 : " + e.getMessage());
			
			//리소스 해제(rs.close(), st.close())
			}finally {
				try {
					rs.close();
					st.close();
				} catch (SQLException e) {
					System.out.println("연결 실패2 : " + e.getMessage());
				}
			}
			System.out.println("Mission5 end"+ "-".repeat(30));
		}
		
		//6. 언어를 입력받아서 해당언어가 국가공식언어인 국가를 검색해서출력하세요. 
		static void Mission6(Connection con) {
			Scanner scan = new Scanner(System.in);
			Statement st = null;
			ResultSet rs = null;
			try {
				//district 입력받음
				System.out.print("6. 언어입력 : ");
				String pop = scan.next();
				
				//질의 객체 생성(Statement)
				st = con.createStatement();
				
				//질의 실행(st.executeQuery)
				rs = st.executeQuery("select country.name, countrylanguage.language, countrylanguage.IsOfficial "
									+ "from countrylanguage join country "
									+ "on country.code = countrylanguage.countrycode "
									+ "where isofficial = 'T' and language='" + pop + "'" );
				
				//커서 프로세싱 => 결과출력(ResultSet)
				while(rs.next()) {
					System.out.print(rs.getString("country.name") + ", ");
					System.out.print(rs.getString("countrylanguage.language") + ", ");
					System.out.println(rs.getString("countrylanguage.IsOfficial"));
				}
			} catch (SQLException e) {
				System.out.println("연결 실패1 : " + e.getMessage());
			
			//리소스 해제(rs.close(), st.close())
			}finally {
				try {
					rs.close();
					st.close();
				} catch (SQLException e) {
					System.out.println("연결 실패2 : " + e.getMessage());
				}
			}
			System.out.println("Mission6 end"+ "-".repeat(30));
		}
		
		// 7. CountryLanguage에서 사용자가 입력 비율 이상인 언어의 국가 코드와 비율을 검색
		static void Mission7(Connection con) {
			//변수 생성(스캐너, Statement, Resultset)
			Scanner scan = new Scanner(System.in);
			Statement st = null;
			ResultSet rs = null;
			
			//비율 입력받기
			try {
				System.out.print("언어 사용 비율 :");
				int pop = scan.nextInt();

			//질의 객체 생성
			st = con.createStatement();
			
			//질의 실행
			rs = st.executeQuery("select language, percentage, countrycode from countrylanguage where percentage >" + pop);
			
			//커서프로세싱 => 결과출력
			//select한 순서대로 안해도 됨
			while(rs.next()) {
				System.out.print(rs.getString("countrycode") + ", ");
				System.out.print(rs.getString("language" )+ ", ");
				System.out.println(rs.getInt("percentage"));
			}
			
			}catch(SQLException e) {
				System.out.println("예외발생1 : " + e.getMessage());
				
			}finally {
				//리소스 해제
					try {
						if(st != null) st.close();
						if(rs != null) rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			System.out.println("Misson7 end" + "-".repeat(30));
		}
	
	public static void main(String[] args) {

		QueryStatement qs = new QueryStatement();
		
		//DB연결 객체 생성(con)
		Connection con = null;
		
		
		try {
			// 프로그램 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			//로컬호스트의 3306 포트에 있는 world데이터에 접속
			String url = "jdbc:mysql://localhost:3306/world";
			//root권한으로 url을 가져옴
			con = DriverManager.getConnection(url, "root", "tiger");
			
			
			Scanner scan = new Scanner(System.in);
			
			//번호 선택하면 그뒤에 그번호만 실행됨
			//1-7사이 선택하라고 한뒤에 입력안됨
			while (true) {
				System.out.print("실행할 미션번호 입력 [exit : 0]:");
				int pop = scan.nextInt();
				switch (pop) {
				case 0: break;
				case 1:
					qs.Mission1(con); break;
				case 2:
					qs.Mission2(con); break;
				case 3:
					qs.Mission3(con); break;
				case 4:
					qs.Mission4(con); break;
				case 5:
					qs.Mission5(con); break;
				case 6:
					qs.Mission6(con); break;
				case 7:
					qs.Mission7(con); break;
				default:
					System.out.println("1-7사이의 숫자를 입력하세요");
				}
				//if문 break인데 왜 while문이 빠져나오지
				//break 가장가까운 반복문을 벗어남
				if(pop == 0) break;
			}System.out.println("실행 종료");
			
			//첫번째 미션 메서드를 호출할때 파라미터로 DB 연결 객체(con)을 넘겨준다.
//			qs.Mission1(con);
//			qs.Mission2(con);
//			qs.Mission3(con);
//			qs.Mission4(con);
//			qs.Mission5(con);
//			qs.Mission6(con);
//			qs.Mission7(con);

		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
			// 정상적으로 실행되든 안되든 실행됨
		} finally {
			try {
				// 프로그램 닫기
				con.close();
			} catch (Exception e) {
			}

		}
	}
	

}
