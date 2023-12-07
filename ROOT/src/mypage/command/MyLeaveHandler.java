package mypage.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginFailException;
import auth.service.User;
import mvc.command.CommandHandler;
import mypage.service.MyPageService;

public class MyLeaveHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/mypage/myLeaveform.jsp";
	private MyPageService myPageService = new MyPageService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return processForm(request, response);
		} else if(request.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return null;
		}
	}
	
		private String processForm(HttpServletRequest request, HttpServletResponse response) {
			return request.getContextPath()+ FORM_VIEW;
	}

		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String member_id = request.getParameter("member_id");
			String member_pw = request.getParameter("member_pw");
			
			Map<String, Boolean> errors = new HashMap<>();
			
			//필수 입력(유효성 검사)
			if(member_id == null || member_id.isEmpty()) {
				errors.put("member_id", Boolean.TRUE);
			}
			if(member_pw == null || member_pw.isEmpty()) {
				errors.put("member_pw", Boolean.TRUE);
			}
			request.setAttribute("errors", errors);
			System.out.println("errors = "+errors.get("member_pw"));
			
			if(!errors.isEmpty()) {
				return request.getContextPath() + FORM_VIEW;
			}
			
			try {
				myPageService.checkId(member_id, member_pw);
				
				HttpSession session = request.getSession(false);
		        if (session != null) {
		            session.invalidate(); 
		        }
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter(); 			
				String msg = "<script>alert('회원 탈퇴가 되었습니다.');location.href="
						     +request.getContextPath()+"'/index.jsp';</script>";		
				writer.print(msg);
				return null;
			}catch(LoginFailException e) {
				errors.put("idpwNOTmatch", Boolean.TRUE);
				return request.getContextPath() +  FORM_VIEW;
			}	
		}

}
