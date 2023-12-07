package exhibition.service;

import java.sql.Connection;
import java.sql.SQLException;

import exhibition.dao.ExhibitionDAO;
import exhibition.exception.ExhibitionNotFoundException;
import exhibition.model.Exhibition;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteExhibitionService  {
	
	ExhibitionDAO exhibitionDAO = new ExhibitionDAO();
	
	//delete쿼리를 통한 글삭제
	public int delete(int no) throws ExhibitionNotFoundException {
	
		System.out.println("DeleteExhibitionService 진입");
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//1.article테이블에 delete하기전 해당글번호 가져오기
			Exhibition exhibition = exhibitionDAO.getInfo(conn, no);
			System.out.println("exhibition ="+exhibition);
			if(exhibition==null) {
				throw new ExhibitionNotFoundException();
			}
			
			//2.article테이블에 delete하는 메서드호출
			int deleteLoc = exhibitionDAO.deleteLoc(conn, exhibition.getPlace());
			int deletePrice = exhibitionDAO.deletePrice(conn, exhibition.getExhibition_no());
			int deleteExhibition = exhibitionDAO.deleteExhibition(conn, exhibition.getExhibition_no());
			int result = -1;
			
			if (deleteLoc==1 && deletePrice==1 && deleteExhibition==1){
				result=1;
			}
			
			conn.commit();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}
