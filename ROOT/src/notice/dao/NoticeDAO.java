package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import notice.model.Notice;

//공지 게시판 작성, 수정, 삭제 DAO
public class NoticeDAO {

	//insert
	public Notice insert(Connection conn, Notice notice) throws SQLException  {
			String sql = "insert into notice (member_id, n_details, n_date, n_title) "
					+"values(?,?,?,?)";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int notice_no = 0;
			
			try {
				pstmt =  conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, notice.getAdmin_id());
				pstmt.setString(2, notice.getN_details());
				pstmt.setTimestamp(3, new Timestamp(notice.getN_date().getTime()));
				pstmt.setString(4, notice.getN_title());
				
				int insertedCount = pstmt.executeUpdate();
				System.out.println(insertedCount);
				if (insertedCount>0) {
					rs = pstmt.getGeneratedKeys();
						if(rs.next()) {
							notice_no = rs.getInt(1);
							System.out.println(notice_no);
							notice.setNotice_no(notice_no);
						return new Notice (notice.getAdmin_id(), notice.getN_title(), notice.getN_details(), notice.getN_date());
					}
				}			
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//select - 총 게시글 조회
	public int selectCount(Connection conn) throws SQLException {
		String sql = "select count(*) from notice";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCNT = 0;	
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCNT = rs.getInt(1);
			}
			return totalCNT;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
	}
	
	//select - 게시글 목록 조회
	public List<Notice> select(Connection conn, int startRow, int size) throws SQLException {
		String sql = "select notice_no, member_id, n_title, n_details, n_date " + 
							  "from notice " + 
							  "order by notice_no desc limit ?,?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			
			List<Notice> list = new ArrayList<Notice>();
			while(rs.next()) {
				list.add(convertNotice(rs));
			} return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);			
		}
	}

	private Notice convertNotice(ResultSet rs) throws SQLException {
		
		return new Notice (rs.getInt("notice_no"), rs.getString("member_id"),
											rs.getString("n_title"), rs.getString("n_details"), rs.getDate("n_date"));
	}

	
	//게시글 상세 조회
	public Notice getDetail(Connection conn, int no) throws SQLException {
		String sql = "select notice_no, member_id, n_title, n_details, n_date " + 
							 "from notice " + 
							 "where notice_no = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null;
		
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					notice = new Notice();
					notice.setNotice_no(rs.getInt("notice_no"));
					notice.setN_title(rs.getString("n_title"));
					notice.setN_details(rs.getString("n_details"));
					notice.setN_date(rs.getDate("n_date"));
				}			
			return notice;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//게시글 수정
	public int update(Connection conn, int no, String title, String content) throws SQLException {
		String sql = "update notice " + 
							 "set n_title=?, n_details=?, "+
							 "n_date=DATE_FORMAT(now(),'%Y%m%d') " + 
							 "where notice_no = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, no);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}				
	}
	
	
	//게시글 삭제
	public int delete(Connection conn, int no) throws SQLException {
		String sql = "delete from notice " + 
							  "where notice_no = ?";
		PreparedStatement pstmt = null;
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);	
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}	
	
