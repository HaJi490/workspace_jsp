package ServletStudy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MyServlet/MyServlet04.do")
public class MyServlet04 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	class NationDTO{
		private int num;
		private String nat;
		private String cp;
		
		
		
		public NationDTO() {
			super();
		}
		
		public NationDTO(int num, String nat, String cp) {
			super();
			this.num = num;
			this.nat = nat;
			this.cp = cp;
		}
		
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public String getNat() {
			return nat;
		}
		public void setNat(String nat) {
			this.nat = nat;
		}
		public String getCp() {
			return cp;
		}
		public void setCp(String cp) {
			this.cp = cp;
		}
		
		
	}
	
//		private NationDAO ndao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
//		//방법 1
//		PrintWriter out = resp.getWriter();
//		out.print("<table>");
		
		//방법 2: DTO 이용
		List<NationDTO> list = new ArrayList<>();
		
		//방법 3: DTO, DAO 이용
//		List<NationDTO> list = ndao.getList();
		
		list.add(new NationDTO(1, "대한민국", "서울"));
		list.add(new NationDTO(2, "미국", "워싱턴"));
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/MyServlet/MyServlet04.jsp").forward(req, resp);
		
	}
}
