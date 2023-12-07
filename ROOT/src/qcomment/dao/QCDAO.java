package qcomment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import qcomment.model.QComment;

public class QCDAO {
	private PreparedStatement pstmt =null;
	private ResultSet rs=null;

	
	public ArrayList<QComment> select(Connection conn, int QA_no) throws SQLException {
		String sql="select A_reply_no,QA_no, A_details, A_date from question_comment " +
					"where QA_no=? order by A_reply_no desc";
		ArrayList<QComment> qcommentList =new ArrayList<QComment>();
	    try {
	    	
	    	pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, QA_no);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            QComment qcomment = new QComment(
        		rs.getInt("A_reply_no"),
	            rs.getInt("QA_no"),
	            rs.getString("A_details"),
	            rs.getDate("A_date"));
	            qcommentList.add(qcomment);
	        	}
	        }finally {
				rs.close();
				pstmt.close();
	        }
	        return qcommentList;
	}
	
	public int QCinsert(Connection conn,QComment qcomment) {
		String sql="insert into question_comment values(A_reply_no,?,?,now())";
	
	try{
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,qcomment.getQA_no());
		pstmt.setString(2,qcomment.getA_details());
		return pstmt.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}
	return -1; 
	
	}

public int updateQComment(Connection conn, QComment qcomment) throws SQLException {
	String sql = "update question_comment "
			+ " set A_details=? "
			+ " where A_reply_no=?";
	PreparedStatement pstmt = null;
	int rowCnt=0;
	try {
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, qcomment.getA_details());
		pstmt.setInt(2, qcomment.getA_reply_no());
		rowCnt=pstmt.executeUpdate();
		return rowCnt;
	}finally {
		pstmt.close();
	}
}

public int delete(Connection conn,int a_reply_no) throws SQLException {
	String sql="delete from question_comment where A_reply_no=?";
	PreparedStatement pstmt = null;
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, a_reply_no);
		return pstmt.executeUpdate();
	}
	finally {
		JdbcUtil.close(pstmt);
	}
}


}



