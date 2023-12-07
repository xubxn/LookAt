package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDAO;
import notice.model.Notice;

//목록 조회 담당 Service
public class NoticeListService {
	
	private NoticeDAO noticeDAO = new NoticeDAO();
	private int size = 5;						//1페이지당 게시글 5개 출력
	
	//pageNo : 유저가 요청한 페이지
	public NoticePage getNoticePage (int pageNo) throws SQLException {
		Connection conn=null;
		try {
			conn = ConnectionProvider.getConnection();
			//총 게시글 수
			int total = noticeDAO.selectCount(conn);
			
			//게시글 목록 조회
			List <Notice> content = noticeDAO.select(conn,(pageNo-1)*size, size);
			NoticePage noticePage = new NoticePage(total, pageNo, size, content);
			return noticePage;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
