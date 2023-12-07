package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class FindIdService {
    private MemberDao memberDao = new MemberDao();
    public Member lostid(String name, String tel) throws Exception {
        System.out.println("FindIdService-lostid 진입");
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            Member memberId = memberDao.lostid(conn, name, tel);
            if (memberId == null) {
                throw new LoginFailException();
            }

            System.out.println("memberId = " + memberId);
            conn.commit();
            return memberId;
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
    
}
	
	




