package exhibition.service;

import java.sql.Connection;
import java.sql.SQLException;

import exhibition.dao.ExhibitionDAO;
import exhibition.exception.ExhibitionNotFoundException;
import exhibition.model.Exhibition;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//상세정보 읽는 Service
//여기서 처리할 작업은 조회수 증가대신 찜등록

public class ReadExhibitionService {
	
	private ExhibitionDAO exhibitionDAO = new ExhibitionDAO();
	
	public Exhibition getDetail (int no) throws ExhibitionNotFoundException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			//모든 컬럼조회하는 쿼리문 DAO에서 가져옴
			exhibitionDAO.getDetail(conn, no);
			
			Exhibition exhibition = exhibitionDAO.getDetail(conn, no);
			if(exhibition == null) {
				throw new ExhibitionNotFoundException();
			}
			return exhibition;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
}
