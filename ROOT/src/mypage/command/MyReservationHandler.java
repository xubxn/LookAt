package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import mypage.service.MyPage;
import mypage.service.MyPageService;

public class MyReservationHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/mypage/mypage_reservation.jsp";
	private MyPageService myPageService = new MyPageService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			User user = (User)request.getSession().getAttribute("AUTH_USER");
			String strNo=request.getParameter("pageNo");
			int pageNo=1;
			if(strNo!=null) {
				pageNo=Integer.parseInt(strNo);
			}
			MyPage myReservationPage=myPageService.getReservationPage(user.getId(), pageNo);
			request.setAttribute("myReservationPage",myReservationPage);
			
			return request.getContextPath()+FORM_VIEW;
		}
	}

