package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class QueryStatement2 {

	public static void main(String[] args) {

		// try-with-resource
		try(Scanner scan = new Scanner(System.in)) {
			
			//번호 선택하면 그뒤에 그번호만 실행됨
			//1-7사이 선택하라고 한뒤에 입력안됨
			while (true) {
				System.out.print("실행할 미션번호 입력 [exit:0] :");
				int pop = scan.nextInt();
				switch (pop) {
				case 0:
					break;
				case 1:
					System.out.println("mission1"); break;
				case 2:
					System.out.println("mission2"); break;
				case 3:
					System.out.println("mission3"); break;
				case 4:
					System.out.println("mission4"); break;
				case 5:
					System.out.println("mission5"); break;
				case 6:
					System.out.println("mission6"); break;
				case 7:
					System.out.println("mission7"); break;
				default:
					System.out.println("1-7사이의 숫자를 입력하세요");
				}
				if (pop == 0) break;
			}
			System.out.println("실행 종료");
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
			// 정상적으로 실행되든 안되든 실행됨
		}
	}
	

}
