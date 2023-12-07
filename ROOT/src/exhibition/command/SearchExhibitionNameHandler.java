package exhibition.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhibition.dao.ExhibitionPage;
import exhibition.service.ListExhibitionService;
import mvc.command.CommandHandler;

public class SearchExhibitionNameHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/SearchListExhibition.jsp";
	private ListExhibitionService listExhibitionService = new ListExhibitionService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("searchbar");
		
		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if(strPageNo != null) { 
			pageNo = Integer.parseInt(strPageNo); 
		}
		
		ExhibitionPage exhibitionPage = listExhibitionService.getExhibitionName(pageNo, name);
		
		request.setAttribute("exhibitionPage", exhibitionPage); //총 게시글수
		request.setAttribute("pageNo", pageNo); //현재페이지
		request.setAttribute("name", name); 		//검색어
		
		return request.getContextPath() + FORM_VIEW;
	}

}
