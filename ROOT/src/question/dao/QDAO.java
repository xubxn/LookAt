package question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import question.model.Question;

public class QDAO {
	private Connection conn;
	private ResultSet rs;

	public Question Qwrite(Connection conn, Question question) throws SQLException {

		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(
					"insert into question(Q_no,member_id,Q_plus_file,Q_details,Q_date,Q_title) values(?,?,?,?,?,?)");
			pstmt.setInt(1, question.getQ_no());
			pstmt.setString(2, question.getMember_id());
			pstmt.setString(3, question.getQ_plus_file());
			pstmt.setString(4, question.getQ_details());
			pstmt.setTimestamp(5, new Timestamp(question.getQ_date().getTime()));
			pstmt.setString(6, question.getQ_title());
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from question");
				if (rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Question(newNum, question.getQ_no(), question.getMember_id(), question.getQ_plus_file(),
							question.getQ_details(), question.getQ_date(), question.getQ_title());
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}

	}

	public int selectCount(Connection conn, String member_id) throws SQLException {
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
	public int selectCountAD(Connection conn) throws SQLException {
		String sql="select count(*) from question ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
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

	public List<Question> select(Connection conn,String member_id ,int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select QA_no,Q_title,member_id,Q_date from question "+
					" where member_id=? "	
					+ " order by QA_no desc limit ?,? ");
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
	public List<Question> selectAD(Connection conn ,int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select QA_no,Q_title,member_id,Q_date from question "+
					" order by QA_no desc limit ?,? ");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
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

	public Question selectById(Connection conn, int QA_no) throws SQLException {
		String sql = "select QA_no,Q_no, member_id, Q_plus_file, Q_details, Q_date, Q_title " + "from question " + " where QA_no = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Question question = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, QA_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				question = new Question();
				question.setQA_no(rs.getInt("QA_no"));
				question.setQ_no(rs.getInt("Q_no"));
				question.setMember_id(rs.getString("member_id"));
				question.setQ_plus_file(rs.getString("Q_plus_file"));
				question.setQ_details(rs.getString("Q_details"));
				question.setQ_date(rs.getDate("Q_date"));
				question.setQ_title(rs.getString("Q_title"));
			}
			return question;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int update(Connection conn, int Q_no ,String Q_plus_file, String Q_details,String Q_title , int QA_no) throws SQLException {
		String sql="update question "
				+ "set Q_no=?, Q_plus_file=?, Q_details=?, Q_title=? "
				+ " where QA_no=?";
		PreparedStatement pstmt=null;
		try {			
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Q_no);
				pstmt.setString(2, Q_plus_file);
				pstmt.setString(3, Q_details);
				pstmt.setString(4, Q_title);
				pstmt.setInt(5, QA_no);
				
				return pstmt.executeUpdate();
		}finally { 
				JdbcUtil.close(pstmt);
		}
	}

	
	public int delete(Connection conn,int QA_no) throws SQLException {
		String sql="delete from question where QA_no=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, QA_no);
			return pstmt.executeUpdate();
		}
		finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	
	public String getDate() {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("select now()");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/*public Question getDetail(Connection conn, int QA_no) throws SQLException {
		String sql="select q.QA_no, q.Q_no, c.Q_menu, q.member_id, q.Q_plus_file, q.Q_details, q.Q_date, q.Q_title "+
				" from question q, question_category c" +
				" where q.Q_no = c.Q_no and q.QA_no=?";
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, QA_no);
			return getDetail(conn, QA_no);
		}finally {
			JdbcUtil.close(pstmt);
	}
	}*/
	public Question getDetail(Connection conn, int QA_no) throws SQLException {
	    String sql = "select q.QA_no, q.Q_no, c.Q_menu, q.member_id, q.Q_plus_file, q.Q_details, q.Q_date, q.Q_title "+
	                 " from question q, question_category c" +
	                 " where q.Q_no = c.Q_no and q.QA_no=?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, QA_no);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            // 결과셋에서 데이터를 가져와서 Question 객체를 생성하고 반환합니다.
	            int q_no = rs.getInt("Q_no");
	            String member_id = rs.getString("member_id");
	            String Q_plus_file = rs.getString("Q_plus_file");
	            String Q_details = rs.getString("Q_details");
	            Date Q_date = rs.getDate("Q_date");
	            String Q_title = rs.getString("Q_title");

	            // Question 객체를 생성하여 반환합니다.
	            return new Question(QA_no, q_no,  member_id, Q_plus_file, Q_details, Q_date, Q_title);
	        } else {
	            return null; // 일치하는 질문이 없을 경우 null을 반환합니다.
	        }
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	}

}
