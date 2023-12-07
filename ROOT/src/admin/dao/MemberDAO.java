package admin.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDAO {
	public Member selectBySearchKeyword(Connection conn, String searchKeyword) throws SQLException{
		 System.out.println("select진입");
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 try {
			 pstmt = conn.prepareStatement("select * from member where member_id like concat('%',?,'%')");
			 pstmt.setString(1, searchKeyword);
			 rs = pstmt.executeQuery();
			 Member member = null;
			 if(rs.next()) {
				 member = new Member(
						rs.getString("member_id"), 
						rs.getString("member_pw"), 
						rs.getString("member_name"),
						rs.getString("member_date"), 
						rs.getString("member_tel"), 
						rs.getString("member_email"), 
						rs.getString("quit_Y")
						); 
			 }//if
			 return member;
		 }finally{
			 JdbcUtil.close(rs);
			 JdbcUtil.close(pstmt);
		}//try-catch
	 } 	
	
	


	//회원가입처리
	 public int insert(Connection conn, Member mem) throws SQLException{
		 System.out.println("insert진입");
	String sql = "insert into member(member_id,member_pw,member_date,member_tel,member_email,member_name,quit_Y) values(?,?,?,?,?,?)";
			PreparedStatement pstmt =
			conn.prepareStatement(sql);
			 pstmt.setString(1,mem.getMember_id());
			 pstmt.setString(2,mem.getMember_pw());
			 pstmt.setString(3,mem.getMember_name());
			 pstmt.setString(4,mem.getMember_date());
			 pstmt.setString(5,mem.getMember_email());
			 pstmt.setString(6,mem.getMember_tel());
			 pstmt.setString(7,mem.getQuit_Y());
			 int cnt =pstmt.executeUpdate();
			 System.out.println(cnt);
		 return cnt;
	 }
	 
	 
	 public List<Member> select(Connection conn, int startRow, int size) throws SQLException {
			String sql="select member_id, member_name, member_email, member_date, member_tel, quit_Y " + 
					    "from member " + 
					    "order by member_id asc limit ?,?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,startRow);
				stmt.setInt(2,size);
				rs = stmt.executeQuery();
			
				List<Member> result = new ArrayList<Member>();
				//List참조변수.add(값); list에   값을 추가
				//List참조변수.get(int 인덱스번호); list에서 값을 가져오기
				while(rs.next()) {
					//데이터타입 변수명=rs.get데이터타입("컬럼명");
					//데이터타입 변수명=rs.get데이터타입(int 컬럼순서);
					result.add( convertMember(rs) ); 
				}//while
				
				return result;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
		}
		
	 private Member convertMember(ResultSet rs) throws SQLException {
		 
			
		 return  
			new Member(rs.getString("member_id"),
					null, 
					rs.getString("member_date"),
					rs.getString("member_tel"),
					rs.getString("member_email"),
					rs.getString("member_name"),
					    rs.getString("quit_Y")
					    );
		}
		
		
		
	 
	 public Member selectById(Connection conn, String id) throws SQLException{
		 System.out.println("select진입");
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 try {
			 pstmt = conn.prepareStatement("select * from member where member_id =?");
			 pstmt.setString(1, id);
			 rs = pstmt.executeQuery();
			 Member member = null;
			 if(rs.next()) {
				 member = new Member(
						rs.getString("member_id"),
						rs.getString("member_date"), 
						rs.getString("member_tel"), 
						rs.getString("member_email"), 
						rs.getString("member_name"),
						rs.getString("quit_Y")
						); 
			 }//if
			 return member;
		 }finally{
			 JdbcUtil.close(rs);
			 JdbcUtil.close(pstmt);
		}//try-catch
	 } 
	 
	 
	//총 회원수 조회
		public int selectCount(Connection conn) throws SQLException {
			String sql="select count(member_id) from member";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				int totalCNT = 0; //총 회원수를 저장하기 위한 변수 선언 및 초기화
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

		
		//회원 삭제
		public int delete(Connection conn, String id) throws SQLException {
			String sql = "delete from member " + 
								  "where member_id = ?";
			PreparedStatement pstmt = null;
			int result = -1;
			try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);	
					result = pstmt.executeUpdate();
				return result;
			} finally {
				JdbcUtil.close(pstmt);
			}
		}

		
		//회원정보검색-ajax
		public List<Member> searchMember(Connection conn, String sName) throws SQLException {
			//3.객체준비
			String sql = "select member_id, member_name, member_email, member_date, member_tel, quit_Y " + 
						 "from member " + 
						 "where member_id like concat('%',?,'%')";
			//"where name=?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			List<Member> memberList = new ArrayList<>();
			Member member = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,sName);
				rs = stmt.executeQuery();  //4.쿼리실행
				
			
				
				while(rs.next()) { 
					member = new Member(rs.getString("member_id"),
											null,
											rs.getString("member_date"),
											rs.getString("member_tel"),
											rs.getString("member_email"), 
											rs.getString("member_name"),
											rs.getString("quit_Y")
											);
					memberList.add(member);
				}
				
			} finally { //5.자원반납
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
			return memberList;
		}
	 
 }

