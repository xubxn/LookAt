package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.model.Notice;
import notice.service.NoticeNotFoundException;
import notice.service.NoticeReadService;

//상세 보기 요청을 처리하는 담당 컨트롤러
//요청주소 http://localhost/noticeRead.do?no=글번호
public class NoticeReadHandler implements CommandHandler {
	
	private NoticeReadService noticeReadService = new NoticeReadService();
		
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//상세하게 보고싶은 글번호
		String strNo = request.getParameter("no");
		int no =Integer.parseInt(strNo);
		
		try {
			
		Notice notice =	noticeReadService.getDetail(no);
		
		//Model
		request.setAttribute("noticeData", notice);
		
		//View
		return request.getContextPath() + "/view/notice/notice_detail.jsp";
			
		} catch (NoticeNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return null;
	}

}
