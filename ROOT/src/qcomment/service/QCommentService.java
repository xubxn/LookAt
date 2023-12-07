package qcomment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import qcomment.dao.QCDAO;
import qcomment.model.QComment;

public class QCommentService {

	private QCDAO qcdao = new QCDAO();

	public List<QComment> getQCommentList(int QA_no) {
		Connection conn = null;
		List<QComment> commentList = null;
		try {
			conn = ConnectionProvider.getConnection();
			commentList = qcdao.select(conn, QA_no);
			if (commentList == null) {
				throw new RuntimeException("fail to select commentList=>쿼리문,db확인");
			}
			return commentList;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		return commentList;
	}

	public int modifyQComment(QComment qcomment) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int rowCnt = qcdao.updateQComment(conn, qcomment);
			if (rowCnt == 0) {
				throw new RuntimeException("fail to update qcomment");
			}
			conn.commit();
			return rowCnt;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}

		return 0;
	}

	public void delete(int A_reply_no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			qcdao.delete(conn, A_reply_no);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int insertQComment(QComment qcomment) {
		try (
			Connection conn = ConnectionProvider.getConnection()) {
        	qcdao = new QCDAO();
            int result = qcdao.QCinsert(conn, qcomment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
