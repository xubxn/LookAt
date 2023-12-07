package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDAO;

public class NoticeDeleteService {

	private NoticeDAO noticeDAO = new NoticeDAO();
	
	public int delete (int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int resultNo = noticeDAO.delete(conn, no);
			
			if(resultNo==0) {
				throw new NoticeNotFoundException();
			}
			
			conn.commit();
			
			return resultNo;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
		JdbcUtil.close(conn);
		}
	}
}
