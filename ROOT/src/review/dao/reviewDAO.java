package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import review.model.ModifyRequest;
import review.model.Review;

public class reviewDAO {


	//글등록
	public Integer insert(Connection conn, Review review) throws SQLException {
		System.out.println("reviewDAO-insert()진입");
		
		//3.객체준비
		String sql =
				"insert into review(member_id, exhibition_no, review_img, review_date, review_score, review_title, details_review) " +
				"values(?,?,?,now(),?,?,?)";
		PreparedStatement stmt  = null; //insert용
		PreparedStatement stmt2 = null; //select용
		ResultSet rs = null;
		
		try {
		stmt = conn.prepareStatement(sql);
		
		//4.쿼리실행
		//stmt.set데이터타입(?순서,값);
		stmt.setString(1,review.getMember_id());
		stmt.setInt(2, review.getExhibition_no());
		stmt.setString(3,review.getReview_img());
		stmt.setInt(4,review.getReview_score());
		stmt.setString(5,review.getReview_title());
		stmt.setString(6,review.getDetails_review());
		int insertedCount = stmt.executeUpdate();
		//입력성공시 1리턴, 실패시 0리턴
		if(insertedCount>0) { //p635 31라인
			//방금 직전에 입력된 글번호를 DB에서 가져온다
			//->article_content 테이블에 insert시 글번호로 사용
			stmt2 =	conn.prepareStatement("select last_insert_id() from review");
			rs = stmt2.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		
		return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt2);
			JdbcUtil.close(stmt);
		}
		
	}
			


	private Timestamp toTimestamp(Date review_date) {
		return new Timestamp(review_date.getTime());
	}




	public int selectCount(Connection conn) throws SQLException {
		String sql="select count(*) from review";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int totalCNT = 0; //총 게시물수를 저장하기 위한 변수 선언 및 초기화
			if(rs.next()) {
				totalCNT = rs.getInt(1);
			}
			return totalCNT;			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}



	
	
//	public List<Review> select(Connection conn,int startRow, int size) throws SQLException {
//		String sql="select exhibition_no, thumbnail " + 
//				   "from review " + 
//				   "order by review_no desc limit ?,?";
//		
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		try {
//			stmt = conn.prepareStatement(sql);
//			stmt.setInt(1,startRow);
//			stmt.setInt(2,size);
//			rs = stmt.executeQuery();
//		
//			List<Review> result = new ArrayList<Review>();
//			while(rs.next()) {
//				result.add( convertReview(rs) ); 
//			}
//			
//			return result;
//		}finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(stmt);
//		}
//	}
	






	public List<Review> select(Connection conn,int startRow, int size) throws SQLException {
		String sql="select review_no, member_id, review_img, review_date, review_score, review_title, details_review " + 
				   "from review " + 
				   "order by review_no desc limit ?,?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,startRow);
			stmt.setInt(2,size);
			rs = stmt.executeQuery();
		
			List<Review> result = new ArrayList<Review>();
			while(rs.next()) {
				result.add( convertReview(rs) ); 
			}
			
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	

	
	// 리뷰쓰기
		public Review reviewData (Connection conn, int num, String id) throws SQLException {
			String sql ="select e.exhibition_no,e.thumbnail, m.member_id " + 
					"from exhibition e, member m, review r " + 
					"where e.exhibition_no = r.exhibition_no " + 
					"and m.member_id = r.member_id " + 
					"order by m.member_id = ?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,num);
				rs = stmt.executeQuery();
				Review reviewData = null;
				if (rs.next()) {
					reviewData = new Review();
					reviewData.setMember_id(rs.getString("member_id"));
					reviewData.setExhibition_no(rs.getInt("exhibition_no"));
				}
				return reviewData;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
			
		}		
	
	

	//select쿼리를 실행한 결과집합(ResultSet)을 이용하여 Board클래스의 객체를 생성
		private Review convertReview(ResultSet rs) throws SQLException {
			return  new Review(rs.getInt("review_no"), 
							  rs.getString("member_id"),
							  rs.getString("review_img"),
							  rs.getDate("review_date"), 
							  rs.getInt("review_score"),
							  rs.getString("review_title"),
							  rs.getString("details_review")
							  );	
		}




		
		
		
		
		
		public int delete(Connection conn, int no) throws SQLException {
			String sql = "delete from review " +
					 "where review_no=?";   
		PreparedStatement stmt  = null; //delete용
		int rno = 0;
		
		try {
			stmt = conn.prepareStatement(sql);
			
			//4.쿼리실행
			//stmt.set데이터타입(?순서,값);
			stmt.setInt(1,no);
					
			int deletedCount = stmt.executeUpdate();
			//성공성공시 삭제된글번호리턴, 실패시 0리턴
			if(deletedCount>0) {
					return no;
				}
			
		}finally {
			JdbcUtil.close(stmt);
		}
		return 0; //삭제 실패시 0을 리턴
	}
		



		//수정하기
		//파라미터 board - board:글번호,회원id,글수정제목,수정내용,수정첨부파일명
		public void modify(Connection conn, ModifyRequest modReq) throws SQLException {
			System.out.println("BoardDAO-modify()진입");
			//3.객체준비
			String sql ="update review " + 
						"set review_title=?, details_review=?, review_img=?, review_date=now() " + 
						"where review_no=?";
			PreparedStatement stmt  = null; //insert용
			
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,modReq.getReview_title());
				stmt.setString(2,modReq.getDetails_review());
				stmt.setString(3,modReq.getReview_img());
				stmt.setInt(4,modReq.getReview_no());
			
				int updatedCount = stmt.executeUpdate();
				//성공성공시 1리턴, 실패시 0리턴
				System.out.println("dao의 수정레코드수="+updatedCount);
			}finally {
				JdbcUtil.close(stmt);
			}
		}



		
		
		
		
		
		
		
		
		
		


		/*댓글 작성---------------------------------------------------------------------------------------------*/	

		

		
		
		/*댓글 내용수정---------------------------------------------------------------------------------------------*/	
		
		
		public Review getDetail(Connection conn, int no) throws SQLException {
			String sql="select review_no,member_id,review_title,details_review,review_date,review_img " + 
					   "from review " + 
					   "where review_no=?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				rs = stmt.executeQuery();
				Review review = null;
				if(rs.next()) {
					review = new Review();
					//조회된 각 컬럼의 값을 가져와 Board클래스 객체로 생성한다
					review.setReview_no(rs.getInt("review_no"));
					review.setMember_id(rs.getString("member_id"));
					review.setReview_title(rs.getString("review_title"));
					review.setDetails_review(rs.getString("details_review"));
					review.setReview_date(rs.getDate("review_date"));
					review.setReview_img(rs.getString("review_img"));
					
					//콘솔출력
					System.out.println("ReviewDAO에서  getDetail() Review review ="+review);
				}
				return review;			
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}	
		}




















		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
















		
		

	

}












