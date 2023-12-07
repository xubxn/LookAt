package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class ResetPwdService {
	private MemberDao memberDao = new MemberDao();
	public int resetPwd(String tel, String setpwd, String setrepw) throws Exception {
		System.out.println("ResetPwdService-resetPwd");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int cnt = memberDao.updatePwd(conn, tel, setpwd);
			if(cnt == 0) {
				throw new LoginFailException();
			}
			
			//member.changePassword(setrepw);
			System.out.println("setpwd = "+setpwd);
			conn.commit();
			return cnt;
	}catch(SQLException e) {
		JdbcUtil.rollback(conn);
		throw new RuntimeException(e);
	}finally {
		JdbcUtil.close(conn);
	}
	}
	
}

