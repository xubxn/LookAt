package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import auth.service.UserPwd;
import jdbc.JdbcUtil;

public class LostPwdDao {
  public static UserPwd lostPwd(Connection conn, String member_tel) throws SQLException {
    System.out.println("select진입");
    
    String sql = "select member_name, member_tel from member where member_tel =?";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    UserPwd userPwd = null;
    
    try {
    	pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, member_tel);
      rs = pstmt.executeQuery();
      
      if (rs.next()) {
    	String membername = rs.getString("member_name");
    	String membertel = rs.getString("member_tel");
    	
    	userPwd = new UserPwd(membername,membertel);
    	return userPwd;
      }
      
      return null;
   } finally {
      // 사용한 리소스 정리
      JdbcUtil.close(rs);
      JdbcUtil.close(pstmt);
    }
  }
}
