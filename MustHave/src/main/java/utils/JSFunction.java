package utils;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

public class JSFunction {
	//메시지 알림창을 띄운 후 명시한 URL로 이동합니다.
	//JspWriter타입 추상클래스 (out.print할때 out) 
	//추상클래스니까 인스턴스생성이 안되니까 JspWriter를 상속한 JspWriterImple에서 
	public static void alertLocation(String msg, String url, JspWriter out) {	//JspWriter타입 추상클래스 (out.print할때 out) // 
		try {
			String script = ""	//삽입할 자바스크립트 코드
							+"<script>"
							+"	alert('" + msg+ "');"
							+"	location.href='" + url+ "';"
							+"</script>";
			out.println(script);	//자바스크립트 코드를 out 내장 객체로 출력(삽입)
		}
		catch(Exception e) {}
	}
	
	//메세지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = ""
						+"<script>"
						+"	alert('" + msg+ "');"
						+"	location.back();"
						+"</script>";
			out.println(script);
		}
		catch(Exception e) {}
	}

	//메세지 알림창을 띄운 후 명시한 URL로 이동합니다.
	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = "" 
							+"<script>"
							+"	alert('" + msg +"');"
							+"	location.href='" + url+ "';"
							+"</script>";
			writer.print(script);
		}catch(Exception e) {}
	}
	
	
}
