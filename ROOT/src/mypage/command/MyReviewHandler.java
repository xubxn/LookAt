package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import mypage.service.MyPage;
import mypage.service.MyPageService;
import question.service.QuestionPage;

public class MyReviewHandler implements CommandHandler {
	
	private static final String FORM_VIEW ="/view/mypage/mypage_review.jsp";
	private MyPageService myPageService = new MyPageService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			User user = (User)request.getSession().getAttribute("AUTH_USER");
			String strNo=request.getParameter("pageNo");
			int pageNo=1;
			if(strNo!=null) {
				pageNo=Integer.parseInt(strNo);
			}
			MyPage myReviewPage=myPageService.getReviewPage(user.getId(), pageNo);
			request.setAttribute("myReviewPage",myReviewPage);
			
			return request.getContextPath()+FORM_VIEW;
		}
	}
