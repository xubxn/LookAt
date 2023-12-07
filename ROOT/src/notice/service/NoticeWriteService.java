 package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDAO;
import notice.model.Notice;

public class NoticeWriteService {
	
	private NoticeDAO noticeDAO = new NoticeDAO();
	
	public int write(Notice notice) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//시간 적용 
			Notice newNotice = toNotice(notice);
			Notice savedNotice = noticeDAO.insert(conn, newNotice);
			if(savedNotice==null) {
				throw new RuntimeException("fail to insert notice");
			}
			
			conn.commit();
			
			return savedNotice.getNotice_no();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
		JdbcUtil.close(conn);
		}
	}

	private Notice toNotice(Notice notice) {
		Date now = new Date();
		return new Notice(notice.getAdmin_id(), notice.getN_title(), notice.getN_details(), now);
	}

}
