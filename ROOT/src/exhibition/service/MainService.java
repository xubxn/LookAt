package exhibition.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import exhibition.dao.ExhibitionDAO;
import exhibition.dao.ExhibitionPage;
import exhibition.model.Exhibition;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MainService {

	ExhibitionDAO exhibitionDAO = new ExhibitionDAO();
	
	int size = 15;
	
	public ExhibitionPage getExhibitionPage(int pageNum) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int total = exhibitionDAO.selectCount(conn);
			List<Exhibition> content = exhibitionDAO.getList(conn, (pageNum-1) * size, size); 
			
			ExhibitionPage ep = new ExhibitionPage(total, pageNum, size, content);
			System.out.println("ListExhibitionService - getExhibitionPage()의 결과 ep= " + ep );
			return ep;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	 }

}
