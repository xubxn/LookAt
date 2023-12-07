package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDAO;

public class NoticeModifyService {
	
	private NoticeDAO noticeDAO = new NoticeDAO();

	public void modify(NoticeRequest noticeReq) {
		Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				
				noticeDAO.update(conn, noticeReq.getNotice_no(), noticeReq.getN_title(), noticeReq.getN_details());
				
				conn.commit();
			} catch (SQLException e) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException(e);
			} catch (PermissionDeniedException e) {
				JdbcUtil.rollback(conn);
				throw e;
			} finally {
				JdbcUtil.close(conn);
			}
	}

}
