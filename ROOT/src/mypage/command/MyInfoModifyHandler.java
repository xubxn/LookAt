package mypage.command;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Member;
import member.service.DuplicateIdException;
import mvc.command.CommandHandler;
import mypage.service.MemberNotFoundException;
import mypage.service.MyPageService;
import notice.service.NoticeNotFoundException;

public class MyInfoModifyHandler implements CommandHandler {
	
	private static final String FORM_VIEW ="/view/mypage/myinfomodify.jsp";
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
		String member_id = request.getParameter("member_id");
		Member member = myPageService.getDetail(member_id);
		
		if(member==null) {
			throw new NoticeNotFoundException();
		}
		
		request.setAttribute("member", member);		
		return request.getContextPath()+ FORM_VIEW;
	}
	
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String member_pw = request.getParameter("member_pw");
		String confirm_pw = request.getParameter("confirm_pw");
		
		Member member = new Member(request.getParameter("member_id"),
				member_pw, confirm_pw, request.getParameter("member_tel"),
				request.getParameter("member_email"),				
				request.getParameter("member_name")
				);	
		request.setAttribute("member", member);
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		member.validate(errors);		

		if(!errors.isEmpty()) {
			return request.getContextPath()+FORM_VIEW;
		}
		 try {
			 
			myPageService.modify(member);
			
			PrintWriter writer = response.getWriter(); 
			String msg = "<script>location.href="
					     +request.getContextPath()+"'/myPageMain.do';</script>";		
			writer.print(msg);
			
			 return null;
			 
		 } catch (MemberNotFoundException e) {
			 response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			 return request.getContextPath()+FORM_VIEW;
		 } catch (DuplicateIdException e) {
			 errors.put("duplicateId", Boolean.TRUE);
		 }

		return null;			 
	}

}
