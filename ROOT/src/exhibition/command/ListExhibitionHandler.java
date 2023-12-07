package exhibition.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhibition.dao.ExhibitionPage;
import exhibition.model.Exhibition;
import exhibition.service.ListExhibitionService;
import mvc.command.CommandHandler;

public class ListExhibitionHandler implements CommandHandler{

	private String FORM_VIEW = "/view/exhibition/listExhibition.jsp";
	private ListExhibitionService listExhibitionService = new ListExhibitionService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ListExhibitionHandler-process()진입");
		
		//1.파라미터받기
		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if(strPageNo != null) { 
			pageNo = Integer.parseInt(strPageNo); 
		}
		 
		
		//2.비즈니스로직 처리
		//전시회 목록 페이지에 들어갈 요소들은 exhibitionPage라는 변수로 선언
		ExhibitionPage exhibitionPage = listExhibitionService.getExhibitionPage(pageNo);

		//3.Model
		request.setAttribute("exhibitionPage", exhibitionPage); //총 게시글수
		request.setAttribute("pageNo", pageNo); //현재페이지
		
		//4.View
		return request.getContextPath() + FORM_VIEW;
	}

	
}
