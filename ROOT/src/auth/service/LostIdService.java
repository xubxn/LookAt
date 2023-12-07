package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.LostPwdDao;
import member.dao.MemberDao;
import member.model.Member;
import member.service.DuplicateIdException;

public class LostIdService {
  private MemberDao memberDao = new MemberDao();
  // 로그인에 성공한 회원정보(회원id, 회원명)
  public UserPwd lostPwd(String membertel) {
	  
	  Connection conn = null;
    try {
     conn = ConnectionProvider.getConnection();
      UserPwd userPwd = LostPwdDao.lostPwd(conn, membertel);
      
      if (userPwd == null || !userPwd.getTel().equals(membertel)) {
    	  return null;
      }
      
      return userPwd;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }finally {
		JdbcUtil.close(conn);
	}
  }
}
