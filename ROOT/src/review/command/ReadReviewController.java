package review.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import review.model.Review;
import review.service.ReviewNotFoundException;
import review.service.reviewService;
import reviewcomment.model.RComment;
import reviewcomment.service.RCService;

//상세보기 요청 컨트롤러이다
//요청주소  http://localhost/board/read.do?no=1
public class ReadReviewController implements CommandHandler {

	private reviewService reviewService = new reviewService();
	private RCService commentService = new RCService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		
		String strNo = request.getParameter("review_no"); //상세하게 보고 싶은 글번호
		System.out.println("나와"+request.getParameter("review_no"));
		int no = Integer.parseInt(strNo); //String을 int로 변환
		System.out.println("ReadController- process() no="+no);
		

		
		//2.비즈니스로직<->Service<->DAO<->DB
		try {
			Review review = reviewService.getDetail(no);  
			
			List<RComment> comment = commentService.getRCommentList(no);
			
			//3.Model
			request.setAttribute("review", review);	   //(원글)상세정보	
			request.setAttribute("comment",comment); //댓글목록
			
			//4.View
			return request.getContextPath()+"/view/readReview/readReview.jsp";
		}catch(ReviewNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404
			return null;
		}
		
	}

}
