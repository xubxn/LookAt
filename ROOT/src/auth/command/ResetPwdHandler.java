package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.ResetPwdService;
import auth.service.SetService;
import auth.service.UserPwd;
import member.service.DuplicateIdException;
import mvc.command.CommandHandler;

public class ResetPwdHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "view/member/lostPwd.jsp";
	private ResetPwdService resetPwdService = new ResetPwdService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("ResetPwdHandler-process");
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		return null;
	}
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		 System.out.println("ResetPwdHandler-processSubmit 진입");
		 String settel = req.getParameter("settel"); //jsp에서 받아온 번호 
		 String setpwd = req.getParameter("setpw"); //새비번
	     String setrepw = req.getParameter("setrepw"); //비번확인
	     
	     System.out.println("settel: " + settel);
	     System.out.println("setpwd: " + setpwd);
	     System.out.println("setrepw: " + setrepw);

	     
	     Map<String, Boolean> errors = new HashMap<String, Boolean>();
	     req.setAttribute("errors", errors);
	     
	     //유효성검사
	     if(setpwd == null || setpwd.isEmpty()) {
	        	errors.put("setpw", Boolean.TRUE);
	        }
	     if(setrepw == null || setrepw.isEmpty()) {
	        	errors.put("setrepw", Boolean.TRUE);
	        }
	     if(!setpwd.equals(setrepw)) {
				errors.put("pwNot", Boolean.TRUE);
			}
	     //에러가 있으면 로그인폼페이지로 이동
			if(!errors.isEmpty()) {
				return req.getContextPath() + FORM_VIEW;
			}
	    
	     
	     //비즈니스로직
	     try {
	    	 int user = resetPwdService.resetPwd(settel,setpwd, setrepw); // MemberDao에서 해당 회원 검색하는 메서드 사용
	    	
	            System.out.println(user);
	            if(user == 0) {
	            	errors.put("nameOrTelNotMatch", Boolean.TRUE);
	            	return req.getContextPath()+FORM_VIEW;
	            }
	            return req.getContextPath() +"/view/member/login.jsp";
	        } catch (RuntimeException e) {
	        	errors.put("nameOrTelNotMatch", Boolean.TRUE);
	        	return req.getContextPath()+FORM_VIEW;
	       
	        }
	}
	}


