package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.JoinRequset;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {
	
	//필드
	private JoinService joinService = new JoinService();
    private static final String FORM_VIEW ="view/member/join.jsp";
   
    
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	System.out.println("process진입");
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
    
    //회원가입처리
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("processSubmit진입");
    	String member_id = req.getParameter("member_id");
    	System.out.println(member_id);
        JoinRequset joinReq = new JoinRequset();
        joinReq.setMember_id(req.getParameter("member_id"));
        joinReq.setMember_name(req.getParameter("member_Name"));
        joinReq.setMember_pw(req.getParameter("member_pw"));
        joinReq.setConfirmPw(req.getParameter("replay_pw"));
        joinReq.setMember_date(req.getParameter("member_date"));
        joinReq.setMember_tel(req.getParameter("member_tel"));
        joinReq.setMember_email(req.getParameter("member_email"));
        System.out.println("JoinHandler-processSubmit진입 joinReq="+joinReq);
        
        
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);
        //필수입력(유효성)검사
        joinReq.validate(errors);

        if (!errors.isEmpty()) {
            return FORM_VIEW;
        }
        
        //view 회원가입 후 로그인 페이지 보여줌
        try {
            joinService.join(joinReq);
            return req.getContextPath()+ "/view/member/login.jsp";
        } catch (DuplicateIdException e) {
            errors.put("duplicateId", Boolean.TRUE);
            return FORM_VIEW;
        }
        
    }
}
