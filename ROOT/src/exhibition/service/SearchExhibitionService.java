package exhibition.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import exhibition.dao.ExhibitionDAO;
import exhibition.exception.ExhibitionNotFoundException;
import exhibition.model.Exhibition;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class SearchExhibitionService {

	private ExhibitionDAO exhibitionDAO = new ExhibitionDAO();
	
	public List<Exhibition> filterSearch (String location, String open_date, String end_date) throws ExhibitionNotFoundException {
		Connection conn = null;
		List<Exhibition> result= null;
		try {
			conn = ConnectionProvider.getConnection();
			result = exhibitionDAO.filterExhibition01(conn, location, open_date, end_date);
			
			if (result == null) {
				throw new ExhibitionNotFoundException();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	} 
}
