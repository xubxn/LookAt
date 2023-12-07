package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LostPwdService;
import auth.service.User;
import auth.service.UserPwd;
import mvc.command.CommandHandler;

public class SetPwdHandler implements CommandHandler {
    private static final String FORM_VIEW = "/view/member/lostPwd.jsp";
    private LostPwdService pwdService = new LostPwdService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("SetPwdHandler-process 진입");

        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        return FORM_VIEW;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("LostPwdHandler-processSubmit 진입");
        String membername = req.getParameter("lostname");
        String membertel = req.getParameter("losttel");
        
        

        Map<String, Boolean> errors = new HashMap<String, Boolean>();
        req.setAttribute("errors", errors);

        if(membername == null || membername.isEmpty()) {
        	errors.put("name", Boolean.TRUE);
        }
        if(membertel == null || membertel.isEmpty()) {
        	errors.put("tel", Boolean.TRUE);
        }
      
        

        if(!errors.isEmpty()) {
        	return req.getContextPath()+FORM_VIEW;
        }

        try {
            // 데이터베이스에서 회원 정보 검색
            UserPwd user = pwdService.lostPwd(membertel); // MemberDao에서 해당 회원 검색하는 메서드 사용
            req.setAttribute("user", user); //jsp로 보냄
            
            if(user == null) {
            	errors.put("nameOrTelNotMatch", Boolean.TRUE);
            	return req.getContextPath()+FORM_VIEW;
            }
            

            
            return req.getContextPath()+"/view/member/setPwd.jsp";
            
            	
        } catch (LoginFailException e) {
        	errors.put("nameOrTelNotMatch", Boolean.TRUE);
        	return req.getContextPath()+FORM_VIEW;
        	
        }

        
    }
}