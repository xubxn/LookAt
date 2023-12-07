package notice.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.service.NoticeNotFoundException;
import notice.service.NoticeDeleteService;
import notice.service.PermissionDeniedException;

public class NoticeDeleteHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/notice/notice_detail.jsp";

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
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		if(strNo==null || strNo.isEmpty()) {
			errors.put("no", Boolean.TRUE);
		}		
		
		if(!errors.isEmpty()) {
			return request.getContextPath()+FORM_VIEW;
		}
		
		NoticeDeleteService deleteService= new NoticeDeleteService();
		
		try {
			int resultNo = deleteService.delete(no);
			request.setAttribute("resultNo", resultNo);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter(); 			
			String msg = "<script>alert('공지를 삭제하였습니다.');location.href="
					     +request.getContextPath()+"'/noticeList.do';</script>";
			writer.print(msg);
			 return null;
		 } catch (NoticeNotFoundException e) {
			 response.sendError(HttpServletResponse.SC_NOT_FOUND);
			 return null; 
		 }
	}

}
