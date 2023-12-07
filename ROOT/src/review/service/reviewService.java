package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import review.dao.reviewDAO;
import review.model.ModifyRequest;
import review.model.Review;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class reviewService {
	
	private reviewDAO reviewDAO = new reviewDAO();
	
	public Review reviewWriteGo(String member_id, int exhibition_no) {
	
	try {
		Connection conn = ConnectionProvider.getConnection();
		
		//p605 16라인
		//파라미터  String id-유저가 입력한 id
		//리턴      review   -유저가 입력한 id에 해당하는 회원정보(회원번호,id,비번,이름,가입일)
		Review review = reviewDAO.reviewData(conn, exhibition_no, member_id);
		if(review==null) {
			throw new reviewFailException();
		}
		
		return reviewWriteGo(review.getMember_id(), review.getExhibition_no());
		
	}catch(SQLException e) {
		throw new RuntimeException(e);
	} 
	

}

	public Review getDetail(int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			Review review = reviewDAO.getDetail(conn, no);
			if(review==null) {
				throw new ReviewNotFoundException();
			}
			
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}		
	}

	
	
	public int deleteReview(int no) {
		Connection conn = null;
		int deletedNo = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//파라미터 board - board:글번호,회원id,글수정제목,수정내용,수정첨부파일명
			//리턴 int 삭제성공시 삭제된 글번호 리턴, 실패시0
			reviewDAO.delete(conn,no);
						
			conn.commit();
			return deletedNo;
			
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


	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//파라미터 board - board:글번호,회원id,글수정제목,수정내용,수정첨부파일명
			reviewDAO.modify(conn,modReq);
						
			conn.commit();
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
