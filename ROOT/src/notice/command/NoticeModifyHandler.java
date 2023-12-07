package notice.command;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.model.Notice;
import notice.service.NoticeModifyService;
import notice.service.NoticeNotFoundException;
import notice.service.NoticeReadService;
import notice.service.NoticeRequest;
import notice.service.PermissionDeniedException;

//게시글 수정 컨트롤러
public class NoticeModifyHandler implements CommandHandler{

	private static final String FORM_VIEW = "/view/notice/notice_modifyForm.jsp";
	
	private NoticeReadService readService = new NoticeReadService();
	private NoticeModifyService modifyService = new NoticeModifyService();
	
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
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		Notice notice = readService.getDetail(no);
		
		if(notice==null) {
			throw new NoticeNotFoundException();
		}
		
		NoticeRequest noticeReq = new NoticeRequest(no, "admin", notice.getN_title(), notice.getN_details());
		request.setAttribute("noticeReq", noticeReq);		
		return request.getContextPath()+ FORM_VIEW;
	}
	
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		String strNo = request.getParameter("no");
		String strPageNo = request.getParameter("pageNo");
		int no = Integer.parseInt(strNo);
		int pageNo = Integer.parseInt(strPageNo);
		
		NoticeRequest noticeReq = new NoticeRequest(no, "admin", request.getParameter("n_title"), request.getParameter("n_details"));
		request.setAttribute("noticeReq", noticeReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		noticeReq.validate(errors);
		if(!errors.isEmpty()) {
			return request.getContextPath()+FORM_VIEW;
		}
		 try {
			 modifyService.modify(noticeReq);
			 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter(); 			
				String msg = "<script>alert('공지 수정을 완료하였습니다.');location.href="
						     +request.getContextPath()+"'/noticeRead.do?no="+no+"&pageNo="+pageNo+"';</script>";
				writer.print(msg);
			 return null;
		 } catch (NoticeNotFoundException e) {
			 response.sendError(HttpServletResponse.SC_NOT_FOUND);
			 return null;
		 } catch (PermissionDeniedException e) {
			 response.sendError(HttpServletResponse.SC_FORBIDDEN);
			 return null;			 
		 }
	}

}
