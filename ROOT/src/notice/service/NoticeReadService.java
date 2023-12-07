package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDAO;
import notice.model.Notice;

//게시글 읽기 기능을 제공하는 Service
public class NoticeReadService {
	
	private NoticeDAO noticeDAO = new NoticeDAO();

	public Notice getDetail(int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			Notice notice = noticeDAO.getDetail(conn,no);
			
			if(notice==null) {
				throw new NoticeNotFoundException();
			}
			
			return notice;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}


}
