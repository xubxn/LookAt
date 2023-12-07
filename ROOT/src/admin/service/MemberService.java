package admin.service;

import java.sql.Connection;





import java.sql.SQLException;
import java.util.List;

import admin.dao.MemberDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.model.Member;


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
public class MemberService {
	
	private MemberDAO memberDAO = new MemberDAO();
	int size = 10;//1페이지당 출력할 게시글수

	//총회원수+목록조회 
	//파라미터  int pageNum : 보고싶은 페이지
	public  MemberPage getMemberPage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = memberDAO.selectCount(conn); //총회원수
			/*파라미터  int startRow-페이지에 따른 row시작번호 예)1page이면 limit 0,3
						int size    -1페이지당 출력할 게시글 수  */
			List<Member> content = memberDAO.select(conn,(pageNum-1)*size,size);//목록조회
			
			/*파라미터  int	total				//총게시글수
			 			int pageNum 			//보고싶은 페이지
			 			int size    			//1페이지당 출력할 게시글 수
						List<Article> content;  //article목록 */
			MemberPage mp = new MemberPage(total, pageNum, size, content);
			System.out.println("MemberService- getMemberPage()의 결과 ap="+mp);
			return mp;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}

	
	public List<Member> getMember(String sName) {
		
		Connection conn = null;
		List<Member> memberList = null;
		try {
			conn = ConnectionProvider.getConnection();

			//변수 String sName : user가 검색시 사용한 회원명
			//리턴 List<Member>: 회원들정보(no,memberid,name,regdate,password,isshow)	
			memberList = memberDAO.searchMember(conn, sName);
	
			return memberList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return memberList;
		
	}

}










