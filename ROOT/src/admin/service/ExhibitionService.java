package admin.service;

import java.sql.Connection;






import java.sql.SQLException;
import java.util.List;

import admin.dao.AdExhibitionDAO;
import admin.model.Exhibition;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;


//등록처리 서비스클래스이다
/*WriteMemberHandler<->서비스<->MemberDAO<->DB
		insert into Member(writer_id,writer_name,title,regdate,moddate,read_cnt)
		values(세션작성자id,세션작성자명,제목,작성일,수정일,0);
		
		방금 직전에 입력된 글번호를 DB에서 가져온다
		->Member_content 테이블에 insert시 번호로 사용
  								 MemberContentDAO<->DB
	  	insert into Member_content(article_no,content)
		values(글번호,내용);

		
 */   
public class ExhibitionService {
	
	private AdExhibitionDAO exhibitionDAO = new AdExhibitionDAO();
	
	//글등록처리
	//파라미터 WriteRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성
	//리턴 Integer - 입력된 글번호->교재에서는 글등록성공시  글등록축하.jsp에서  글상세보기  기능 제공용으로 사용
/*	public Member member(MemberRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			
			//파라미터 MemberRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성
			//리턴 Member - MemberRequest+작성일,수정일,조회수
			 Member  member = member(req);
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
*/
	
	public List<Exhibition> getExhibition(int no) {
		
		Connection conn = null;
		List<Exhibition> exhibitionList = null;
		try {
			conn = ConnectionProvider.getConnection();

			exhibitionList = exhibitionDAO.searchExhibition(conn, no);
	
			return exhibitionList;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.close(conn);
		}
		return exhibitionList;
		
	}

}










