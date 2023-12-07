package admin.dao;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import admin.model.Exhibition;
import admin.service.OurExhibitionData;
import jdbc.JdbcUtil;

//exhibition테이블관련 DB작업용 클래스이다
public class AdExhibitionDAO {

	//update쿼리를 통한 글삭제
	public int deleteUp(Connection conn, String no)   throws SQLException {
		String sql = "update exhibition set isshow='N' where exhibition_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,no);
			return stmt.executeUpdate();
			//update가 성공되면 1리턴, 실패시 0리턴
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
		
	//삭제-delete쿼리를 통한 글삭제
	public int delete(Connection conn, String strNo)  throws SQLException {
		String sql = "delete from exhibition where exhibition_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,strNo);
			return stmt.executeUpdate();
			//delete가 성공되면 1리턴, 실패시 0리턴
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	
	
	public int update(Connection conn, int no, String title)  throws SQLException {
		String sql = "update exhibition " + 
					 "set title=?, regdate=now() " + 
					 "where exhibition_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,title);
			stmt.setInt(2,no);
			return stmt.executeUpdate();
			//update가 성공되면 1리턴, 실패시 0리턴
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	
	
	
	//여기에서는 2개의 테이블의 내용을 join하여 처리하는 방식으로 진행
	//파라미터 int no : 상세조회할 글 번호
	//리턴     OurArticleData : 글번호,작성자id,작성자명,제목,작성일,수정일,조회수,내용
//	public OurExhibitionData getDetail(Connection conn, int no) throws SQLException {
////		String sql="select exhibition_no, title, " + 
////					"from exhibition ";
//					     
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		try {
//			stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, no);
//			rs = stmt.executeQuery();
//			OurExhibitionData ora = null;
//			if(rs.next()) {
//				ora = new OurExhibitionData();
//				ora.setExhibition_no(rs.getInt("exhibition_no"));
//				ora.setTitle(rs.getString("title"));
//				
//				//콘솔출력
//				System.out.println("ExhibitionDAO에서  getDetail() OurExhibitionData ora ="+ora);
//			}
//			return ora;			
//		}finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(stmt);
//		}	
//	}
	
	//파라미터 int no : 상세조회할 글 번호
	public void increaseReadCount(Connection conn, int no)  throws SQLException {
		String sql = "update exhibition " + 
					 "where exhibition_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			stmt.executeUpdate();
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	
	
	
	public List<Exhibition> select(Connection conn,int startRow, int size) throws SQLException {
		String sql="select exhibition_no, title " + 					    
				    "from exhibition " + 
				    "order by exhibition_no desc  limit ?,?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,startRow);
			stmt.setInt(2,size);
			rs = stmt.executeQuery();
		
			List<Exhibition> result = new ArrayList<Exhibition>();
			//List참조변수.add(값); list에   값을 추가
			//List참조변수.get(int 인덱스번호); list에서 값을 가져오기
			while(rs.next()) {
				//데이터타입 변수명=rs.get데이터타입("컬럼명");
				//데이터타입 변수명=rs.get데이터타입(int 컬럼순서);
				result.add( convertExhibition(rs) );
			}//while
			
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	//select쿼리를 실행한 결과집합(ResultSet)을 이용하여 클래스의 객체를 생성
	private Exhibition convertExhibition(ResultSet rs) throws SQLException {
		return  
		new Exhibition(rs.getInt("exhibition_no"),
				    rs.getString("title")  );
	}
	
	
	
	public int selectCount(Connection conn) throws SQLException {
		String sql="select count(*) from exhibition";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int totalCNT = 0; //총 게시물수를 저장하기 위한 변수 선언 및 초기화
			if(rs.next()) {
	//			데이터타입 변수명=rs.get데이터타입("컬럼명");
	//			데이터타입 변수명=rs.get데이터타입(int 컬럼순서);
				totalCNT = rs.getInt(1);
			}
			return totalCNT;			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	
	

	//전시회검색-ajax
	public List<Exhibition> searchExhibition(Connection conn, int no) throws SQLException {
		//3.객체준비
		String sql = "select exhibition_no,title " + 
					 "from exhibition " + 
					 "where exhibition_no = ?";
		//"where name=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Exhibition> exhibitionList = new ArrayList<>();
		Exhibition exhibition = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();  //4.쿼리실행
			
		
			
			while(rs.next()) { 
				exhibition = new Exhibition(rs.getInt("exhibition_no"), 
										rs.getString("title"));
				exhibitionList.add(exhibition);
			}
			
		} finally { //5.자원반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return exhibitionList;
	}

	
	

	public Exhibition insert(Connection conn, Exhibition exhibition) throws SQLException {
		System.out.println("ExhibitionDAO-insert()진입");
		
		//3.객체준비
		String sql =
		"insert into exhibition(exhibition_no,title) " + 
		"values(?,?,'Y')";
		PreparedStatement stmt  = null; //insert용
		PreparedStatement stmt2 = null; //select용
		ResultSet rs = null;
		
		try {
		stmt = conn.prepareStatement(sql);
		
		//4.쿼리실행
		//stmt.set데이터타입(?순서,값);
		stmt.setInt(1,exhibition.getExhibition_no());
		stmt.setString(3,exhibition.getTitle());
		int insertedCount = stmt.executeUpdate();
		//입력성공시 1리턴, 실패시 0리턴
		if(insertedCount>0) { 
			//방금 직전에 입력된 글번호를 DB에서 가져온다
			stmt2 =	conn.prepareStatement("select last_insert_id() from Exhibition");
			rs = stmt2.executeQuery();
			if(rs.next()) {
				Integer newNum = rs.getInt(1);
				return new Exhibition(newNum,exhibition.getTitle());
			}
		}
		
		return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt2);
			JdbcUtil.close(stmt);
		}
		
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}


	public static OurExhibitionData getDetail(Connection conn, int no) {
		return null;
	}


	public List<Exhibition> searchExhibition(Connection conn, String sName) {
		return null;
	}


	
	
	
	
}









