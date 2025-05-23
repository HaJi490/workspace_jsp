package fileupload;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/13FileUpload/UploadProcess.do")
@MultipartConfig( maxFileSize = 1024 * 1024 *1, maxRequestSize = 1024 * 1024 *10)
public class UploadProcess extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	//HttpServletRequest: 톰캣이 만들어서 넘겨줌
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//드라이브명으로 절대경로 저장하기
			//Uploads 디렉토리의 물리적 경로 가져오기
			String saveDirectory = getServletContext().getRealPath("/Uploads");//실제경로명
			
			//파일 업로드하기
			String originalFileName = FileUtil.uploadFile(req, saveDirectory);
			
			//저장된 파일명 변경하기
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			
			//DB에 저장하기
			insertMyFile(req, originalFileName, savedFileName);
			
			//파일 목록 페이지로 이동하기
			resp.sendRedirect("FileList.jsp");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "파일 업로드 오류");
			req.getRequestDispatcher("FileUploadMain.jsp").forward(req, resp);
		}
	}
	
	private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName) {
		//파일 외 폼값받기
		String title = req.getParameter("title");
		String[] cateArray = req.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();
		if(cateArray == null) {
			cateBuf.append("선택한 항목 없음");
		}else{
			for(String s : cateArray) {
				cateBuf.append(s+", ");
			}
		}
		MyFileDTO dto = new MyFileDTO();
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(oFileName);
		dto.setSfile(sFileName);
		
		MyFileDAO dao = new MyFileDAO();
		dao.insertFile(dto);
		dao.close();
	}
}
