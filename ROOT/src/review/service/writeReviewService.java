package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import jdbc.JdbcUtil;
import review.dao.reviewDAO;
import review.model.Review;
import review.model.reviewPage;
import jdbc.connection.ConnectionProvider;

public class writeReviewService {

	//필드
		private reviewDAO reviewDAO = new reviewDAO();
		int size = 10;//1페이지당 출력할 게시글수
		
		
		
		
		
		
		
		public int deleteReviewList(int no) {
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
		
		
		
/*		
		//수정하기
		//파라미터 board:글번호,회원id,글수정제목,수정내용,수정첨부파일명
		public void modifyReviewList(Review review) {
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				
				//파라미터 board - board:글번호,회원id,글수정제목,수정내용,수정첨부파일명
				reviewDAO.modify(conn,review);
							
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
	*/	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		//글등록하기
		//파라미터 board: 세션의 회원id,글제목,내용,첨부파일명
		//리턴     boardno : 방금전 insert된 글번호
		public int insert(Review review) {
			
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				
				//파라미터 board   - 회원id,제목,내용,작성일,첨부파일명
				//리턴     int - inserted된 정보 글번호!!!
				Integer savedReviewno = reviewDAO.insert(conn,review);
				if(savedReviewno==null) {
					throw new RuntimeException("Fail to insert review");
				}
				
				conn.commit();
				return savedReviewno;
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
		
		

		
		
		//총게시글수+글목록조회 
	    //파라미터  int pageNum : 보고싶은 페이지
		public reviewPage getReviewPage(int pageNum) {
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				
				int total = reviewDAO.selectCount(conn); //총게시글수
				/*파라미터  int startRow-페이지에 따른 row시작번호 예)1page이면 limit 0,3
							int size    -1페이지당 출력할 게시글 수  */
				List<Review> content = reviewDAO.select(conn,(pageNum-1)*size,size);//목록조회-p651 19라인
				
				/*파라미터  int	total				//총게시글수
				 			int pageNum 			//보고싶은 페이지
				 			int size    			//1페이지당 출력할 게시글 수
							List<Board> content;    //board목록 */
				reviewPage bp = new reviewPage(total, pageNum, size, content);
				System.out.println("BrticleService- getReviewPage()의 결과 bp="+bp);
				
				return bp;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}finally {
				JdbcUtil.close(conn);
			}
		}




	




		


		
}
