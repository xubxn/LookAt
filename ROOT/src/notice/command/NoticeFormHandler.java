package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

//공지 작성 폼 화면을 보여주는 요청에 대한 담당 컨트롤러(핸들러)
//http://localhost/noticeForm.do
public class NoticeFormHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/notice/notice_list.jsp";

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
	
	//공지 작성 목록을 보여주는 메소드
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}

	//공지 작성 폼을 보여주는 메소드
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		return  request.getContextPath()+"/view/notice/notice_writeForm.jsp";
	}

}
