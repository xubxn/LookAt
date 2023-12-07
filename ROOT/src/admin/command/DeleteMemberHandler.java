package admin.command;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import admin.service.DeleteMemberService;
import admin.service.MemberNotFoundException;
import mvc.command.CommandHandler;

//삭제요청을 담당하는 컨트롤러이다
//요청주소  http://localhost/Member/delete.do
public class DeleteMemberHandler implements CommandHandler {
	private static final String FORM_VIEW = "/view/admin/memberList.jsp";
	private DeleteMemberService deleteService= new DeleteMemberService();

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
		return  request.getContextPath()+FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strId = request.getParameter("members");
		Boolean result = null;
		
		Map<String, Boolean> errors = new HashMap<>();
			request.setAttribute("errors", errors);
			
			if(strId==null || strId.isEmpty()) {
				errors.put("id", Boolean.TRUE);
			}		
			
			if(!errors.isEmpty()) {
				return request.getContextPath()+FORM_VIEW;
			}
			
			
			try {
				int resultCnt = deleteService.delete(strId);
				if(resultCnt>0) {
					result = true;
				} else {
					result = false;
				}
				request.setAttribute("result", result);
				
				PrintWriter writer = response.getWriter(); 
				String msg = "<script> alert('success!!'); location.href="
						     +request.getContextPath()+"'/memberList.do';</script>";		
				writer.print(msg);
				return null; 
			 } catch (MemberNotFoundException e) {
				 response.sendError(HttpServletResponse.SC_NOT_FOUND);
				 return null; 
			 }
		}
}
