package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginFailException;
import auth.service.SetService;
import auth.service.User;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler{
	//필드
	
	private static final String FORM_VIEW = "view/member/login.jsp";
	private SetService loginService = new SetService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("LoginHandler-process 진입");
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		return null;
	}//process
	
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("errorMessage", "");
		return FORM_VIEW;
	}
	
	//로그인처리
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String memberId = (req.getParameter("member_id"));
		String memberPw = trim(req.getParameter("member_pw"));
		
		//에러 정보가 담기는 Map
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		//필수 입력(유효성 검사)
		if(memberId == null || memberId.isEmpty()) {
			errors.put(memberId, Boolean.TRUE);
		}
		if(memberPw == null || memberPw.isEmpty()) {
			errors.put(memberPw, Boolean.TRUE);
		}
		
		//에러가 있으면 로그인폼페이지로 이동
		if (!errors.isEmpty()) {
	        String errorMessage = "아이디 또는 비밀번호가 일치하지 않습니다.";
	        System.out.println(errorMessage);
	        req.setAttribute("errorMessage", errorMessage);
	        return FORM_VIEW;
	    }
		
		//비즈니스로직
		try {
			User user = loginService.login(memberId, memberPw);
			
			HttpSession session = req.getSession();
			session.setAttribute("AUTH_USER", user);
			
			res.sendRedirect(req.getContextPath() + "/index.do");
			return null;
		}catch(LoginFailException e) {
	        req.setAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return req.getContextPath() +  FORM_VIEW;
		}
	
	}
	//좌우 공백 제거 
	private String trim(String str) {
		return str==null ? null : str.trim();
	}


}
