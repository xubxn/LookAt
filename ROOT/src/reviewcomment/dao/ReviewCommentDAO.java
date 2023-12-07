package reviewcomment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import reviewcomment.model.RComment;

public class ReviewCommentDAO {
	private PreparedStatement pstmt =null;
	private ResultSet rs=null;

	
	public ArrayList<RComment> select(Connection conn, int review_no) throws SQLException {
		String sql="select review_comment_no, review_no, details_comment, member_id, comment_date from review_comment " +
					"where review_no=? order by review_no desc";
		ArrayList<RComment> rcommenLlist = new ArrayList<RComment>();
	    try {
	    	
	    	pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, review_no);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            RComment comments = new RComment(
        		rs.getInt("review_comment_no"),
        		rs.getInt("review_No"),
	            rs.getString("details_comment"),
	            rs.getString("member_id"),
	            rs.getDate("comment_date"));
	            rcommenLlist.add(comments);
	        	}
	        }finally {
				rs.close();
				pstmt.close();
	        }
	        return rcommenLlist;
	}
	
	public int RCinsert(Connection conn, RComment rccomment) {
		String sql="insert into review_comment values(review_comment_no,?,?,?,now())";
	
	try{
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,rccomment.getReview_no());
		pstmt.setString(2,rccomment.getDetails_comment());
		pstmt.setString(3, rccomment.getMember_id());
		return pstmt.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}
	return -1; 
	
	}

public int updateRComment(Connection conn, RComment rccomment) throws SQLException {
	String sql = "update review_comment " + 
				"set details_comment=?, comment_date=NOW() " + 
				"where review_comment_no=?";
	PreparedStatement pstmt = null;
	int rowCnt=0;
	try {
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, rccomment.getDetails_comment());
		pstmt.setInt(2, rccomment.getReview_comment_no());
		rowCnt=pstmt.executeUpdate();
		return rowCnt;
	}finally {
		pstmt.close();
		JdbcUtil.close(pstmt);
	}
}

public int delete(Connection conn,int review_comment_no) throws SQLException {
	String sql="delete from review_comment where review_comment_no=?";
	Statement stmt = null;
	PreparedStatement pstmt = null;
	int result = 0;
	try {
		stmt = conn.createStatement();
		stmt.execute("SET FOREIGN_KEY_CHECKS=0");
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, review_comment_no);
		result = pstmt.executeUpdate();
		stmt.execute("SET FOREIGN_KEY_CHECKS=1");
		
		return result;

	}
	finally {
		JdbcUtil.close(stmt);
		JdbcUtil.close(pstmt);
	}
}


}



