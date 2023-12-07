package mypage.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.model.Member;
import mvc.command.CommandHandler;
import mypage.model.MyReservation;
import mypage.service.MyPageService;

public class MypageHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/mypage/mypage_main.jsp";
	private MyPageService myService = new MyPageService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		User user = (User)request.getSession().getAttribute("AUTH_USER");
		String memberid = user.getId();
		
		Map<String, MyReservation>  myreservation =myService.getMyPage (memberid);
		MyReservation beforeContent = myreservation.get("beforeContent");
		MyReservation afterContent = myreservation.get("afterContent");
		Boolean result = false;
		if(beforeContent!=null) {
			int resultCnt = myService.getCountRevById(memberid, beforeContent.getExhibition_no());			
			Map<String, Boolean> errors = new HashMap<>();
			request.setAttribute("errors", errors);
			if (resultCnt==0) {
				result = true;
			} else {
				result = false; 
			}
		} 
		Member member = myService.getDetail(memberid);
		
		request.setAttribute("beforeContent", beforeContent);
		request.setAttribute("afterContent", afterContent);
		request.setAttribute("result", result);
		request.setAttribute("member", member);
		
		return request.getContextPath()+FORM_VIEW;
	}

}
