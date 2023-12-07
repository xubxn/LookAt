package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.service.NoticeListService;
import notice.service.NoticePage;

//목록 조회 요청 담당 컨트롤러
//요청 주소 
public class NoticeListHandler implements CommandHandler {
	
	private String FORM_VIEW = "/view/notice/notice_list.jsp";
	private NoticeListService noticeListService = new NoticeListService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//유저가 선택한 pageNo
		String strNo = request.getParameter("pageNo");	
		int pageNo = 1;
		if(strNo!=null) {
			pageNo = Integer.parseInt(strNo);
		}
		
		NoticePage noticePage = noticeListService.getNoticePage(pageNo);
		request.setAttribute("noticePage", noticePage);
		request.setAttribute("nowPage", pageNo);
		
		return request.getContextPath()+FORM_VIEW;
	}
	
	
}