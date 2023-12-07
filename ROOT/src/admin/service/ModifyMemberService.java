package admin.service;

import java.sql.Connection;



import java.sql.SQLException;

import admin.dao.MemberDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.model.Member;


//수정처리를 위한 서비스클래스이다
public class ModifyMemberService {
	//필드
	private MemberDAO memberDAO = new MemberDAO();

	//수정처리
	//파라미터 ModifyRequest modReq-수정처리를 위한 세션에서 가져온 회원정보
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}catch(PermissionDeniedException e){ //수정불가시
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	//수정가능여부체크
	// 수정하고자하는 user의 id가 글작성자id와 일치하는 비교하여 동일하면 수정가능     
	//리턴 boolean - 수정할 수 있으면 true반환, 불가하면 false반환
	private boolean canModify(String modifyingUserId, OurMemberData Member) {
		return   modifyingUserId.equals(Member.getWriter_id());
	}

	
}











