package exhibition.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhibition.dao.ExhibitionDAO;
import exhibition.model.Exhibition;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
public class WriteExhibitionService {

	private ExhibitionDAO exhibitionDAO = new ExhibitionDAO();

	// 글등록 처리
	// 리턴 Integer - 입력된 글번호
	public int insert(Exhibition exhibition) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			// 파라미터 Exhibition - 회원id, 회원name, 제목, 내용, 작성일, 수정일, 조회수
			// 리턴 Exhibition - 입력된 정보 (글번호, 회원id, 회원name), 제목, 작성일, 수정일
			int savedContentRow = exhibitionDAO.insert(conn, exhibition);
			if (savedContentRow == 0) {
				throw new RuntimeException("Fail to insert Exhibition_content");
			}
			conn.commit();

			return savedContentRow;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
