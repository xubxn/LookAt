package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auth.service.LoginFailException;
import auth.service.User;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import mypage.dao.MyPageDAO;
import mypage.model.MyReservation;
import mypage.model.MyReview;
import notice.service.NoticeNotFoundException;
import notice.service.PermissionDeniedException;
import question.model.Question;
import question.service.QuestionPage;

public class MyPageService {
	
	
	private MyPageDAO mypageDAO = new MyPageDAO();
	private MemberDao memberDao = new MemberDao();
	

	//Want 목록
	public Map<String, MyReservation>  getMyPage(String userid) {
		Connection conn=null;
		Map<String, MyReservation> resMap = new HashMap<>();
		try{
			conn=ConnectionProvider.getConnection();
			MyReservation  beforeContent=mypageDAO.planNResById(conn, userid);
			MyReservation  afterContent=mypageDAO.planYResById(conn, userid);
			resMap.put("beforeContent", beforeContent);
			resMap.put("afterContent", afterContent);
			return resMap;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}	finally {
			JdbcUtil.close(conn);
		}
	}
	
	//리뷰 작성 여부 
	public int getCountRevById(String memberid, int exhibition_no) {
		Connection conn=null;
		int result = -1; 
		try{
			conn=ConnectionProvider.getConnection();
			result=mypageDAO.selectRevCountById(conn, memberid, exhibition_no);
			return result;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}	finally {
			JdbcUtil.close(conn);
		}
	}
	
	//Member 정보
	public Member getDetail(String member_id) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			Member member = mypageDAO.getInfoDetail(conn,member_id);
			
			if(member==null) {
				throw new NoticeNotFoundException();
			}
			
			return member;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	//멤버 정보 수정
	public int modify(Member member) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int cntRow = mypageDAO.updateInfo(conn, member);
			
			if (cntRow==0 ) {
				return 0;
			}		
			conn.commit();
			return cntRow;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
}
	
	//QA 목록
	public QuestionPage getQuestionPage(String member_id, int pageNo) {
		int size = 4;
		Connection conn=null;
		try{
			conn=ConnectionProvider.getConnection();
			int total=mypageDAO.selectQCount(conn, member_id);
			List<Question> content=mypageDAO.selectQ(conn, member_id, (pageNo-1)*size, size);
			return new QuestionPage(total, pageNo, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}	finally {
			JdbcUtil.close(conn);
		}
	}
	
	//리뷰 목록
	public MyPage getReviewPage(String member_id, int pageNo) {
		int size = 2;
			Connection conn=null;
			try{
				conn=ConnectionProvider.getConnection();
				int total=mypageDAO.selectRevCount(conn, member_id);
				List<MyReview> content=mypageDAO.selectRev(conn, member_id, (pageNo-1)*size, size);
				return new MyPage(total, pageNo, size, content);
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}	finally {
				JdbcUtil.close(conn);
			}
	}
	
	//예약 목록
	public MyPage getReservationPage(String member_id, int pageNo) {
		int size = 2;
			Connection conn=null;
			try{
				conn=ConnectionProvider.getConnection();
				int total=mypageDAO.selectResCount(conn, member_id);
				List<MyReservation> content=mypageDAO.selectRes(conn, member_id, (pageNo-1)*size, size);
				return new MyPage(total, pageNo, size, content);
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}	finally {
				JdbcUtil.close(conn);
			}
	}

	public User checkId(String member_id, String member_pw) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			Member member = memberDao.selectById(conn, member_id);
			if(member == null) {
				throw new LoginFailException();
			}
			if(!member.matchPassword(member_pw)) {
				throw new LoginFailException();
			}			
			int result = mypageDAO.leave(conn, member_id);
			if (result!=1) {
				throw new LoginFailException();
			}
			return new User(member.getMember_id(), member.getMember_name());
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}//login
	
	//예매 상세페이지
	public MyReservation getReservationRead(int reservation_no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			MyReservation reservation = mypageDAO.getReservationRead(conn,reservation_no);
			
			if(reservation==null) {
				throw new NoticeNotFoundException();
			}
			
			return reservation;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
