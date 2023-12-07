package admin.service;

import java.sql.Connection;






import java.sql.SQLException;
import java.util.List;

import admin.dao.MemberDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.model.Member;


//ListArticleHandler<->Service<->DAO<->DB
public class ListMemberService {
	
	MemberDAO memberDAO = new MemberDAO();
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
			System.out.println("ListMemberService- getMemberPage()의 결과 ap="+mp);
			return mp;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
}






