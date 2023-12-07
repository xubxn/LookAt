package member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import auth.service.UserPwd;
import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {
 public Member selectById(Connection conn, String id) throws SQLException{
	 System.out.println("MemberDao-select진입");
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
					rs.getString("member_pw"), 
					rs.getString("member_date"), 
					rs.getString("member_tel"), 
					rs.getString("member_email"), 
					rs.getString("member_name"), 
					rs.getString("quit_Y"));
					
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
	String sql = "insert into member(member_id,member_pw,member_date,member_tel,member_email,member_name) values(?,?,?,?,?,?)";
	PreparedStatement pstmt = null;
			
	
	try {
	
		pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1,mem.getMember_id());
			 pstmt.setString(2,mem.getMember_pw());
			 pstmt.setString(3,mem.getMember_date());
			 pstmt.setString(4,mem.getMember_tel());
			 pstmt.setString(5,mem.getMember_email());
			 pstmt.setString(6,mem.getMember_name());
			 int cnt =pstmt.executeUpdate();
			 System.out.println(cnt);
			 return cnt;
	} finally {
		 JdbcUtil.close(pstmt);
	 }
	 }
	//ID중복체크-ajax
	   //파라미터  String id-유저가 입력한 id
	   //리턴 int-이미 사용중인 id이면 1리턴, 사용가능한 id이면 0리턴,그외 -1
	   public int idCheck(Connection conn, String member_id) throws SQLException {
	      //3.객체준비
	      String sql = "select count(member_id) " + 
	                "from member " + 
	                "where member_id=?";
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      int result = -1;
	      try {
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1,member_id);
	         rs = stmt.executeQuery();  //4.쿼리실행
	         
	         if(rs.next()) { //이미 사용중인 id이면 1리턴, 사용가능한 id이면 0리턴
	            /*데이터타입 변수명 = rs.get데이터타입("컬럼명");
	              데이터타입 변수명 = rs.get데이터타입(int 컬럼순서); */
	             result = rs.getInt(1);
	         }
	      } finally { //5.자원반납
	         JdbcUtil.close(rs);
	         JdbcUtil.close(stmt);
	      }
	      return result;
	      
	   }
	 //비번 새로 셋팅
	 public int updatePwd(Connection conn, String tel, String setpwd) throws SQLException {
		System.out.println("updatePwd진입");
		String sql ="update member "
				+ "set member_pw=? "
				+ "where member_tel=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1,setpwd);
			 pstmt.setString(2,tel);
			 int cnt =pstmt.executeUpdate();
			 System.out.println("cnt"+cnt);
		 //1이면 입력, 0이면 안 됨S
		 return cnt;
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	 //아이디잃어버림
	 public Member lostid(Connection conn,String name, String tel) throws SQLException {
		    System.out.println("MemberDao -lostid진입");
		    
		    String sql = "select member_id, member_name from member where member_name = ? "
		    			+ "and member_tel = ?";
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    Member member = null;
		    
		    try {
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, name);
		      pstmt.setString(2, tel);
		      rs = pstmt.executeQuery();
		      
		      if(rs.next()) {
		    	  member = new Member();
			    member.setMember_id(rs.getString("member_id"));
			    member.setMember_name(rs.getString("member_name"));
			    System.out.println( member.getMember_id());
			    System.out.println(member.getMember_name());
			    return member;
		      }
		      return member;
		   } finally {
		      // 사용한 리소스 정리
		      JdbcUtil.close(rs);
		      JdbcUtil.close(pstmt);
		    }
		  }
	 
	
	 
}