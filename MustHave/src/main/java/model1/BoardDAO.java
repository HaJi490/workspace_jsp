package model1;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBCConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBCConnect{
	public BoardDAO(ServletContext application){
		super(application);
	}
	
	//검색 조건에 맞는 게시물의 개수를 반환
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0; //결과(게시물 수)를 담을 변수
		
		//게시물 수를 얻어오는 쿼리문 작성
		String query = "Select Count(*) From board";
		if(map.get("searchWord") != null) {
			query += " Where " + map.get("searchField") + " "
				+ "Like '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement();//쿼리문 생성
			rs= stmt.executeQuery(query);//쿼리 실행
			rs.next();//커서를 첫 번째 행으로 이동
			totalCount = rs.getInt(1);//첫번째 컬럼 값을 가져옴
		}catch(Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	//검색 조건에 맞는 게시물 목록 반환(페이징 기능 지원)
	public List<BoardDTO> selectListPage(Map<String, Object> map){
		List<BoardDTO> bbs = new Vector<BoardDTO>();//결과(게시물 목록)를 담을 변수
		
		//쿼리문 템플릿
		String query = "Select * From board";
		//검색 조건 추가
		if(map.get("searchWord") != null) {
			query += " Where " + map.get("searchField") + " "	//대소문자 구분
					+ "Like '%" + map.get("searchWord") + "%'";
		}
		query += " Order By num Desc Limit ?, ? ";
		
		//쿼리문 완성
		try {
			psmt = con.prepareStatement(query);//쿼리문 생성
			
			psmt.setInt(1, (int)map.get("start"));
			psmt.setInt(2, (int)map.get("pageSize"));
			rs = psmt.executeQuery();//쿼리 실행
			
			while(rs.next()) {//결과를 순회하며
				//한 행(게시물 하나)의 내용을 DTO에 저장
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				//반환할 결과 목록에 게시물 추가
				bbs.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		//목록 반환
		return bbs;
	}
	
	//게시글 데이터를 받아 DB에 추가
	public int insertWrite(BoardDTO dto) {
		int result =0 ;
		
		try {
			//Insert 쿼리문 작성
			String query = "Insert Into board ( title, content, id, visitcount)"
							+ " Values ( ?, ?, ?, 0)";
			
			psmt=con.prepareStatement(query);//동적 쿼리
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	//지정한 게시물을 찾아 내용을 반환
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		
		//쿼리문 준비
		String query = "Select B.*, M.name "
						+ "From member M Inner Join board B On M.id=B.id "
						+"Where num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);//인파라미터를 일련번호로 설정
			rs = psmt.executeQuery();//쿼리 실행
			
			//결과 처리
			if(rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));
			}
		}catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;		
	}
	
	//저장한 게시물의 조회수를 1 증가
	public void updateVisitCount(String num) {
		//쿼리문 준비
		String query = "Update board Set visitcount=visitcount+1 Where num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);//인파라미터를 일련번호로 설정
			psmt.executeUpdate();//쿼리 실행
			
		}catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	//지정한 게시물을 수정
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		
		try {
			//쿼리문 템플릿
			String query = "Update board Set title=?, content=? Where num=?";
			
			//쿼리문 완성
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			
			//쿼리문 실행
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result; //결과 반환
	}
	
	//지정한 게시물을 삭제
	public int deletePost(BoardDTO dto) {
		int result = 0;
		
		try {
			//쿼리문 템플릿
			String query = "Delete From board Where num=?";
			
			//쿼리문 완성
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			
			//쿼리문 실행
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
}
