package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import review.model.Review;
import review.service.reviewFailException;
import review.service.reviewService;

public class ReviewWriteGoHandler implements CommandHandler{

	private static final String FORM_VIEW ="/view/exhibition/readExhibition.jsp";
	private reviewService reviewService = new reviewService();
	
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		
			if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
				return processForm(request,response);
			}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
				return processSubmit(request,response); //p607 25라인
			}else {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
				return null;
			}
		}//process
	
	
	
	
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		String memberid = request.getParameter("member_id");//user가 입력한 id
		int exhibitionNo = Integer.parseInt(request.getParameter("exhibition_no"));
		
		//p607 53라인
		try {
			//2.비즈니스로직<->Service<->DAO<->DB 
			//파라미터 String memberid, String password-유저가 입력한 id,비번
			//리턴     User - 로그인에 성공한 회원정보(회원id,회원명)=>세션에 저장될 정보
			Review review = reviewService.reviewWriteGo(memberid, exhibitionNo);
			
			//3.Model- request또는session.setAttribute("속성명",Object 값);
			HttpSession session = request.getSession();
			session.setAttribute("AUTH_USER", review); //p607 55라인
			//4.View - 성공시:index.jsp이동 
			return null;
		}catch(reviewFailException e) { //실패시:loginForm.jsp
			return request.getContextPath()+FORM_VIEW;
		}
		
	}
	
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
//		String ex_noParam = request.getParameter("no");
//	    int ex_no = 0; // 초기값 0으로 설정
		return request.getContextPath()+FORM_VIEW;
		
	}
	
		
	}



