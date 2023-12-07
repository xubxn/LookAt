package reviewcomment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import reviewcomment.dao.ReviewCommentDAO;
import reviewcomment.model.RComment;

public class RCService {
		//필드

		private ReviewCommentDAO rcdao = new ReviewCommentDAO();

		public List<RComment> getReviewList(int review_no) {
			Connection conn = null;
			List<RComment> commentList = null;
			try {
				conn = ConnectionProvider.getConnection();
				commentList = rcdao.select(conn, review_no);
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
	
		
		public List<RComment> getRCommentList(int review_no) {
			Connection conn = null;
			List<RComment> commentList = null;
			try {
				conn = ConnectionProvider.getConnection();
				commentList = rcdao.select(conn, review_no);
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

		public int modifyRComment(RComment rcomment) {
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);

				int rowCnt = rcdao.updateRComment(conn, rcomment);
				if (rowCnt == 0) {
					throw new RuntimeException("fail to update rcomment");
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

		public void deleteComment(int review_comment_no) {
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				int result = rcdao.delete(conn, review_comment_no);
				if(result!=1) {
					JdbcUtil.rollback(conn);
				}
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				JdbcUtil.rollback(conn);
				throw new RuntimeException();
			} finally {
				JdbcUtil.close(conn);
			}
		}
		
		public int insertQComment(RComment rcomment) {
			try (
				Connection conn = ConnectionProvider.getConnection()) {
				rcdao = new ReviewCommentDAO();
	            int result = rcdao.RCinsert(conn, rcomment);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }

	}

		
		
		
		
		
		
		
		
		
		
		
		
		
	