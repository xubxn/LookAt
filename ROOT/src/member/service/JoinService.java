package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

//회원가입 기능을 제공하는 클래스.
public class JoinService {
	private MemberDao memberDao = new MemberDao();

	public void join(JoinRequset joinReq) {
		System.out.println("JoinService-join진입 joinReq=" + joinReq);
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member member = memberDao.selectById(conn, joinReq.getMember_id());
			if (member != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}
			// 폼에서 받은 정보를 DAO로 보내줌
			Member member01 = new Member(joinReq.getMember_id(), joinReq.getMember_pw(), joinReq.getMember_date(),
					joinReq.getMember_tel(), joinReq.getMember_email(), joinReq.getMember_name(), null);
			memberDao.insert(conn, member01);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int join(String memberid, String password, String date, String tel, String email, String name) {

		Connection conn = null;
		int rowCnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = new Member(memberid, password, date, tel, email, name, null );
			rowCnt = memberDao.insert(conn, member);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return rowCnt;
	}
	
	//id중복체크-ajax이용
	   //파라미터  String id-유저가 입력한 id
	   //리턴유형  int-이미 사용중인 id이면 1리턴, 사용가능한 id이면 0리턴, 그외 -1
	   public int idCheck(String member_id) {
	      
	      Connection conn = null;
	      int result = -1;
	      try {
	         conn = ConnectionProvider.getConnection();
	         //autoCommit하지마 설정 
	         conn.setAutoCommit(false);
	         //리턴 int-이미 사용중인 id이면 1리턴, 사용가능한 id이면 0리턴, 그외 -1
	         result = memberDao.idCheck(conn,member_id);
	         conn.commit(); 
	         return result;
	      } catch (SQLException e) {
	         e.printStackTrace();
	         JdbcUtil.rollback(conn);//rollback 
	         throw new RuntimeException(e);
	      }finally {
	         JdbcUtil.close(conn);
	      }   
	   }
}
