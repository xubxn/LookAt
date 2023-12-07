package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import mypage.model.MyReservation;
import mypage.service.MyPageService;

public class ReservationReadHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/reservation_detail.jsp";
	private MyPageService myPageService = new MyPageService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User)request.getSession().getAttribute("AUTH_USER");
		String strNo=request.getParameter("no");
		int reservation_no=0;
		if(strNo!=null) {
			reservation_no=Integer.parseInt(strNo);
		}
		MyReservation myReservation = myPageService.getReservationRead(reservation_no);
		request.setAttribute("myReservation", myReservation);
		
		return request.getContextPath()+FORM_VIEW;
	}
}
