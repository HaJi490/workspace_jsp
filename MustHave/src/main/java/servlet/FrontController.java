package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.one")
public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();
		int lastSlash = uri.lastIndexOf("/");
		String commandStr = uri.substring(lastSlash);//lastSlash이후를 가져옴
		
		if(commandStr.equals("/regist.one"))
			registFunc(req);
		else if(commandStr.equals("/login.one"))
			loginFunc(req);
		else if(commandStr.equals("/freeboard.one"))
			freeboardFunc(req);
		req.setAttribute("uri", uri);
		req.setAttribute("commandStr", commandStr);
		req.getRequestDispatcher("/12Servlet/FrontController.jsp").forward(req, resp);
	}
	
	//페이지별 처리 메서드
	private void registFunc(HttpServletRequest req) {
		req.setAttribute("resultValue", "<h4>회원가입</h4>");
	}
	
	private void loginFunc(HttpServletRequest req) {
		req.setAttribute("resultValue", "<h4>로그인</h4>");
	}
	
	private void freeboardFunc(HttpServletRequest req) {
		req.setAttribute("resultValue", "<h4>자유게시판</h4>");
	}

}
