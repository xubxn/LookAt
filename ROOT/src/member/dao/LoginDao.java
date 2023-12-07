package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class LoginDao {
	public int login(Connection conn, String member_id, String member_pw) throws SQLException {
		System.out.println("select진입");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select * from member where member_id =? and member_pw =?");
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(member_pw)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 로그인 실패
				}
			} // if
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return -2; //디비 오류
	}// try-catch

}
