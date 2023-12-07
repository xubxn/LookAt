package reviewcomment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import reviewcomment.service.RCService;

public class DeleteRCommentHandler implements CommandHandler{

	private RCService rcommentService=new RCService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DeleteRCommentHandler 진입");
		
		int review_comment_no =Integer.parseInt(request.getParameter("Review_comment_no"));
		System.out.println("review_comment_no = "+review_comment_no);
		rcommentService.deleteComment(review_comment_no);
		
		response.sendRedirect(request.getContextPath()+"reviewList.do");
		return null;
	}

}
