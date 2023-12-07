package exhibition.service;

import java.sql.Connection;
import java.sql.SQLException;

import exhibition.dao.ExhibitionDAO;
import exhibition.exception.ExhibitionNotFoundException;
import exhibition.model.Exhibition;
import exhibition.model.ExhibitionJoin;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//상세정보 읽는 Service
//여기서 처리할 작업은 조회수 증가대신 찜등록

public class ReadExhibitionService2 {
	
	private ExhibitionDAO exhibitionDAO = new ExhibitionDAO();
	
	public ExhibitionJoin getDetail (int no) throws ExhibitionNotFoundException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			//모든 컬럼조회하는 쿼리문 DAO에서 가져옴
			exhibitionDAO.getDetail(conn, no);
			
			ExhibitionJoin exhibitionJoin = exhibitionDAO.getDetail2(conn, no);
			if(exhibitionJoin == null) {
				throw new ExhibitionNotFoundException();
			}
			return exhibitionJoin;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
}
