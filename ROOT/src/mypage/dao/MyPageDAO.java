package mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import member.model.Member;
import mypage.model.MyReservation;
import mypage.model.MyReview;
import mypage.model.Want;
import question.model.Question;

public class MyPageDAO {

	
	public List<Want> selectWant(Connection conn, String userid, int startRow, int size) throws SQLException {
		String sql = "select w.want_no, w.exhibition_no, w.member_id,  e.thumbnail " + 
							 "from want w, exhibition e " + 
							  "where w.exhibition_no = e.exhibition_no " + 
							  "and w.member_id = ? " +
								"order by want_no desc limit ?,? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();	
			List<Want> result = new ArrayList<Want>();
			while(rs.next()) {
				result.add(convertWant(rs)); 
			}			
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Want convertWant(ResultSet rs) throws SQLException {
		return new Want(rs.getInt("want_no"), rs.getInt("exhibition_no"), rs.getString("member_id"), rs.getString("thumbnail"));
	}

	
	public int selectWCount(Connection conn, String member_id) throws SQLException {
		String sql="select count(*) from want "+
							"where member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			int totalCNT = 0; 
			if(rs.next()) {
				totalCNT = rs.getInt(1);
			}
			return totalCNT;			
		}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
		}		
	}

	public int selectQCount(Connection conn, String member_id) throws SQLException {
		String sql="select count(*) from question "
						+  "where member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			int totalCNT = 0; 
			if(rs.next()) {
				totalCNT = rs.getInt(1);
			}
			return totalCNT;			
		}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
		}	
	}
	
	public List<Question> selectQ(Connection conn, String member_id,  int startRow, int size) throws SQLException {
		String sql = "select QA_no,Q_title,member_id,Q_date from question "+
							"where member_id =? "+
							"order by QA_no desc limit ?,? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			List<Question> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertQuestion(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Question convertQuestion(ResultSet rs) throws SQLException {
		return new Question(rs.getInt("QA_no"), rs.getString("Q_title"), rs.getString("member_id"),
				rs.getDate("Q_date"));
	}
	
	public Member getInfoDetail(Connection conn, String member_id) throws SQLException {
		String sql = "select member_id, member_pw, member_tel, member_email, member_name " + 
							 "from member " + 
							 "where member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = new Member(
						rs.getString("member_id"),
						rs.getString("member_pw"),
						rs.getString("member_tel"),
						rs.getString("member_email"),
						rs.getString("member_name")
						);
			}
			return member;
		}  finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int updateInfo(Connection conn, Member member) throws SQLException {
		String sql ="update member " + 
							 "set member_pw = ?, member_tel = ?, member_email =? " + 
							 "where member_id = ? ";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,member.getMember_pw());
			pstmt.setString(2, member.getMember_tel());
			pstmt.setString(3, member.getMember_email());	
			pstmt.setString(4, member.getMember_id());	
			return pstmt.executeUpdate();
		}  finally {
			JdbcUtil.close(pstmt);
		}		
	}

	public int selectRevCount(Connection conn, String member_id) throws SQLException {
		String sql="select count(*) from review " +
							"where member_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			int totalCNT = 0; 
			if(rs.next()) {
				totalCNT = rs.getInt(1);
			}
			return totalCNT;			
		}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
		}		
	}

	public List<MyReview> selectRev(Connection conn, String member_id, int startRow, int size) throws SQLException {
		String sql = "select r.review_no, r.member_id, r.exhibition_no, r.review_img, e.title, e.thumbnail " + 
							 "from review r, exhibition e " + 
							 "where r.exhibition_no = e.exhibition_no " + 
							 "and member_id=? " +
							 "order by review_no desc limit ?,? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			List<MyReview> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertReview(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	
	private MyReview convertReview(ResultSet rs) throws SQLException {
		return new MyReview(rs.getInt("review_no"), rs.getString("member_id"), rs.getInt("exhibition_no"), rs.getString("review_img"),  rs.getString("title"), rs.getString("thumbnail"));
	}

	public int selectResCount(Connection conn, String member_id) throws SQLException {
		String sql="select count(*) from reservation " +
							"where member_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member_id);
		rs = pstmt.executeQuery();
		int totalCNT = 0; 
		if(rs.next()) {
			totalCNT = rs.getInt(1);
		}
			return totalCNT;			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}	
	}

	public List<MyReservation> selectRes(Connection conn, String member_id, int startRow, int size) throws SQLException {
		String sql = "select e.exhibition_no, e.title, e.PLACE, e.thumbnail, r.reservation_no, r.member_id, r.price_no, r.going_date, " + 
							"(r.total_adult*p.price_adult+r.total_student*p.price_student+r.total_baby*p.price_baby) as total, l.details_place " + 
							"from exhibition e, reservation r, price p, location l " + 
							"where e.exhibition_no = r.exhibition_no " + 
							"and r.price_no = p.price_no " + 
							"and e.PLACE = l.place " + 
							"and member_id =? " +
							"order by reservation_no desc limit ?,?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			List<MyReservation> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertResrvation(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private MyReservation convertResrvation(ResultSet rs) throws SQLException {
		return new MyReservation(rs.getInt("reservation_no"), rs.getString("member_id"), rs.getDate("going_date"), rs.getString("title"), rs.getString("place"), rs.getString("thumbnail"), rs.getInt("total"), rs.getString("details_place"));
	}
	
	public int leave(Connection conn, String member_id) throws SQLException {
		String sql="update member " + 
							"set quit_Y = 'Y' " + 
							"where member_id=?";
		PreparedStatement pstmt = null;
		int result = -1;
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member_id);
		result = pstmt.executeUpdate();
		return result;			
		}finally {
		JdbcUtil.close(pstmt);
		}	
		
	}
	
	//리뷰 작성 여부
	public int selectRevCountById(Connection conn, String userid, int no) throws SQLException {
		String sql = "select count(*) from review " + 
							"where member_id = ? and exhibition_no = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCNT = 0; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCNT = rs.getInt(1);
				System.out.println("totalCNT = "+totalCNT);
			}
			return totalCNT;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//볼 전시회
	public MyReservation planNResById(Connection conn, String userid) throws SQLException {
		String sql = "SELECT r.reservation_no, r.member_id, r.going_date, r.exhibition_no, e.title, e.thumbnail, e.PLACE " + 
							"FROM reservation r, exhibition e " + 
							"WHERE r.going_date < DATE_SUB(NOW(), INTERVAL 1 DAY) " + 
							"and r.exhibition_no = e.exhibition_no " + 
							"and r.member_id = ? " + 
							"ORDER BY r.going_date desc limit 0,1 ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyReservation myreservation = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				myreservation = new MyReservation(rs.getInt("reservation_no"), 
									rs.getString("member_id"), rs.getInt("exhibition_no"), 0, rs.getDate("going_date"), rs.getString("title"),
									rs.getString("PLACE"), rs.getString("thumbnail"), 0, null);}
			return myreservation;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//봤던 전시회
	public MyReservation planYResById(Connection conn, String userid) throws SQLException {
		String sql = "SELECT r.reservation_no, r.member_id, r.going_date, r.exhibition_no, e.title, e.thumbnail, e.PLACE " + 
							"FROM reservation r, exhibition e " + 
							"WHERE r.going_date >= DATE_SUB(NOW(), INTERVAL 1 DAY) " + 
							"and r.exhibition_no = e.exhibition_no " + 
							"and r.member_id = ? " + 
							"ORDER BY r.going_date limit 0,1 ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyReservation myreservation = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//int reservation_no, String member_id, Date going_date, String title, String place, String thumbnail, int total, String details_place
				myreservation = new MyReservation(rs.getInt("reservation_no"), 
									rs.getString("member_id"), rs.getDate("going_date"), rs.getString("title"),
									rs.getString("PLACE"), rs.getString("thumbnail"), 0, null);}
			return myreservation;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//int reservation_no, String member_id, int exhibition_no, int price_no, Date reser_date,
	//Date going_date, String title, String place, String thumbnail, int total_adult, int total_student,
	//int total_baby, int total, String details_place
	public MyReservation getReservationRead(Connection conn, int reservation_no) throws SQLException {
		String sql = "select e.exhibition_no, e.title, e.PLACE, e.thumbnail, e.details_img, e.introduction, r.reservation_no, r.member_id, r.reser_date, r.going_date, " + 
							"r.total_adult, r.total_student, r.total_baby, (r.total_adult*p.price_adult+r.total_student*p.price_student+r.total_baby*p.price_baby) as total, l.details_place " + 
							"from exhibition e, reservation r,  price p, location l " + 
							"where e.exhibition_no = r.exhibition_no " + 
							"and r.price_no = p.price_no  " + 
							"and e.PLACE = l.place " + 
							"and r.reservation_no = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyReservation myreservation = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservation_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				myreservation = new MyReservation(rs.getInt("reservation_no"), 
									rs.getString("member_id"), rs.getInt("exhibition_no"), 0, rs.getDate("reser_date"), rs.getDate("going_date"), rs.getString("title"),
									rs.getString("PLACE"), rs.getString("thumbnail"), rs.getString("details_img"), rs.getString("introduction"), rs.getInt("total_adult"), rs.getInt("total_adult"), rs.getInt("total_baby"), rs.getInt("total"), rs.getString("details_place"));}
			System.out.println("상세이미지 = "+myreservation.getDetails_img());
			return myreservation;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}


}
