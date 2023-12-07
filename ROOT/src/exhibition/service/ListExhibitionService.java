package exhibition.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import exhibition.dao.ExhibitionDAO;
import exhibition.dao.ExhibitionPage;
import exhibition.model.Exhibition;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ListExhibitionService {
	
	ExhibitionDAO exhibitionDAO = new ExhibitionDAO();
	int size = 9; //한 페이지당 출력할 게시글 수 
	
	//수업
	public ExhibitionPage getExhibitionPage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			//p651 18라인
			int total = exhibitionDAO.selectCount(conn);
			List<Exhibition> content = exhibitionDAO.getList(conn, (pageNum-1) * size, size); //p651 19라인
			
			//목록조회예정 - p651 21라인
			//파라미터 int total //총게시글수
			//				  int pageNum //보고싶은 페이지
			//				  int size //한페이지당 츨력할 게시글 수 
			//				  List<Article> content //article 목록
			ExhibitionPage ep = new ExhibitionPage(total, pageNum, size, content);
			return ep;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	 }
	 public ExhibitionPage getExhibitionName(int pageNum, String name) {
		int size = 3; 

		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			int total = exhibitionDAO.selectCountByname(conn, name);
			List<Exhibition> content = exhibitionDAO.getListByName(conn, name, (pageNum-1) * size, size);
			System.out.println("total = "+total);
			ExhibitionPage ep = new ExhibitionPage(total, pageNum, size, content);
			return ep;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	 }



}
