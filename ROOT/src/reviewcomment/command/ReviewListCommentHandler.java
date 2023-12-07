package reviewcomment.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import reviewcomment.model.RComment;
import reviewcomment.service.RCService;

public class ReviewListCommentHandler implements CommandHandler {

	private RCService commentService = new RCService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
				int review_no = Integer.parseInt(request.getParameter("no"));//원글번호
				System.out.println("no="+request.getParameter("no"));
				System.out.println("review_no"+request.getParameter("review_no"));
				//2.비즈니스로직<->Service<->DAO<->DB
				//파라미터 no - 원글번호
				//리턴     List<Comment> - 특정원글에 작성된 댓글정보(댓글번호,원글번호,댓글작성자,댓글내용,댓글등록일)
				List<RComment> comments = commentService.getRCommentList(review_no);
				
				//3.Model
				request.setAttribute("comments",comments);
				
				//4.View
				return null;
			}

		}





