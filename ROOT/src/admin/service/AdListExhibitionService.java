package admin.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import admin.dao.AdExhibitionDAO;
import admin.model.Exhibition;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;


public class AdListExhibitionService {
	
	AdExhibitionDAO exhibitionDAO = new AdExhibitionDAO();
	int size = 10;//1페이지당 출력할 게시글수

	//총게시글수+목록조회 
	//파라미터  int pageNum : 보고싶은 페이지
	public ExhibitionPage getExhibitionPage(int pageNum) {
		Connection conn=null;
		try {
			conn = ConnectionProvider.getConnection();
			
		
			int total = exhibitionDAO.selectCount(conn); //총게시글수
			/*파라미터  int startRow-페이지에 따른 row시작번호 예)1page이면 limit 0,3
						int size    -1페이지당 출력할 게시글 수  */
			List<Exhibition> content = exhibitionDAO.select(conn,(pageNum-1)*size,size);
			
	
		
			ExhibitionPage ap = new ExhibitionPage(total, pageNum, size, content);
			System.out.println("ListExhibitionService- getExhibitionPage()의 결과 ap="+ap);
			return ap;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}

	
	
	public List<Exhibition> getExhibition(String sName) {
		
		
		Connection conn = null;
		List<Exhibition> exhibitionList = null;
		try {
			conn = ConnectionProvider.getConnection();

			exhibitionList = exhibitionDAO.searchExhibition(conn, sName);
	
			return exhibitionList;
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(conn);
		}
		return exhibitionList;
	}
}






