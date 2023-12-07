package notice.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import notice.model.Notice;
import notice.service.NoticeWriteService;

//공지사항 작성 완료 페이지
public class NoticeWriteHandler implements CommandHandler {
	///view/notice/notice_success.jsp
	private static final String FORM_VIEW = "/view/notice/notice_writeForm.jsp";
	private NoticeWriteService writeService = new NoticeWriteService();

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
		return request.getContextPath()+FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Notice notice = new Notice();
		
		//1. 파라미터 받기
		String n_title = request.getParameter("n_title");
		String n_details = request.getParameter("n_details");
		User user =(User) request.getSession().getAttribute("AUTH_USER");
		String userid = user.getId();					
		notice.setN_title(n_title);
		notice.setN_details(n_details);
		notice.setAdmin_id(userid);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		
		if(!userid.equals("admin")) {
			errors.put("admin", Boolean.TRUE);
		}
		
		if(n_title==null || n_title.isEmpty()) {
			errors.put("n_title", Boolean.TRUE);
		}
		
		if(n_details==null || n_details.isEmpty()) {
			errors.put("n_details", Boolean.TRUE);
		}
			
		if(!errors.isEmpty()) {
			return request.getContextPath()+FORM_VIEW;
		}
		
		//2. 비즈니스 로직 처리 <-> Service <-> DAO <-> DB
		// 파라미터 : notice
		int newNotice_no = writeService.write(notice);
		
		request.setAttribute("newNotice_no", newNotice_no);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter(); 			
		String msg = "<script>alert('공지 작성을 완료하였습니다.');location.href="
				     +request.getContextPath()+"'/noticeList.do';</script>";
		writer.print(msg);
		
		return null;
	}

	

}
