package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//'do'+ctrl+space해서 입력
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setAttribute("message", "Hello Servlet..!!");
		req.getRequestDispatcher("/12Servlet/HelloServlet.jsp").forward(req, resp);
	}

}
