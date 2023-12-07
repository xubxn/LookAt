package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import admin.dao.MemberDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//삭제처리하는 서비스클래스이다
public class DeleteMemberService {
	
	 MemberDAO  memberDAO = new  MemberDAO();

	 /*
	//update쿼리를 통한 삭제
	public void deleteUp(int id) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			
			
			//2.Member테이블에 update하는 메서드호출
			 memberDAO.deleteUp(conn, id);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}		
		
	}
	*/
	
	
	//delete쿼리를 통한 회원삭제
	public int delete(String id) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
		
			 int resultCnt = memberDAO.delete(conn, id);
			
			if(resultCnt==0) {
				throw new MemberNotFoundException();
			}
			conn.commit();
			
			return resultCnt;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
	}


}
