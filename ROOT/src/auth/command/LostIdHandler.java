package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.FindIdService;
import auth.service.LoginFailException;
import member.model.Member;
import mvc.command.CommandHandler;

public class LostIdHandler implements CommandHandler {
	private static final String FORM_VIEW = "view/member/lostid.jsp";
	private FindIdService findIdService = new FindIdService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("FindIdHandler-process 진입");
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
		return null;
    }

    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        return FORM_VIEW;
    }
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("FindIdHandler-processSubmit 진입");
        String membername = req.getParameter("member_name");
        String membertel = req.getParameter("member_tel");
        System.out.println(membername);
        System.out.println(membertel);
        
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
        	Member memberId = findIdService.lostid(membername, membertel);
        	
        	req.setAttribute("member", memberId);
            System.out.println(memberId);
            
            if(memberId == null) {
            	errors.put("nameOrTelNotMatch", Boolean.TRUE);
            	return req.getContextPath()+FORM_VIEW;
            }
            
            return req.getContextPath()+"/view/member/findid.jsp";
            
            	
        } catch (LoginFailException e) {
        	errors.put("nameOrTelNotMatch", Boolean.TRUE);
        	return req.getContextPath()+FORM_VIEW;
        	
        }

        
    }
}