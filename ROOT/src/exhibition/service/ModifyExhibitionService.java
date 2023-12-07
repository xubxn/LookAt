package exhibition.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import exhibition.dao.ExhibitionDAO;
import exhibition.exception.ExhibitionNotFoundException;
import exhibition.exception.PermissionDeniedException;
import exhibition.model.Exhibition;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyExhibitionService {
	
	//필드
	private ExhibitionDAO exhibitionDAO = new ExhibitionDAO();

		//수정처리-p667-17라인
		//파라미터 ModifyRequest modReq-수정처리를 위한 세션에서 가져온 회원id, 글번호,제목,내용
		public void modify(Exhibition modReq) throws PermissionDeniedException, ExhibitionNotFoundException {
			
			System.out.println("ModifyExhibitionService-modify() 진입");
			System.out.println(modReq.getExhibition_no());
			
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				int modifyResult = exhibitionDAO.update(conn, modReq);
				if (modifyResult == 1) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				JdbcUtil.rollback(conn);
				throw new RuntimeException();
			}finally {
				JdbcUtil.close(conn);
			}
		}

		//수정가능여부체크-p668 47라인
		// 수정하고자하는 user의 id가 글작성자id와 일치하는 비교하여 동일하면 수정가능     
		//리턴 boolean - 수정할 수 있으면 true반환, 불가하면 false반환
		//private boolean canModify(Session session, User user) {
		//	return   modifyingUserId.equals(article.getWriter_id());
		//}
}
