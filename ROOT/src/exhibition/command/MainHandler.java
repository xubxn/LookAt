package exhibition.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhibition.dao.ExhibitionPage;
import exhibition.service.ListExhibitionService;
import mvc.command.CommandHandler;

public class MainHandler implements CommandHandler {

	private String FOEM_VIEW = "/view/index02.jsp";
	private ListExhibitionService listExhibitionService = new ListExhibitionService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MainHandler-process()진입");
		
		//1.파라미터받기
		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if(strPageNo != null) { 
			pageNo = Integer.parseInt(strPageNo); 
		}
		 
		
		//2.비즈니스로직 처리
		ExhibitionPage exhibitionPage = listExhibitionService.getExhibitionPage(pageNo);
		
		//3.Model
		request.setAttribute("exhibitionPage", exhibitionPage); //총 게시글수
		request.setAttribute("nowPage", pageNo); //현재페이지
		
		//request.setAttribute("content", content);
		//4.View
		return FOEM_VIEW;
	}

}
