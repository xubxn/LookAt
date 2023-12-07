package exhibition.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import exhibition.model.Exhibition;
import exhibition.service.SearchExhibitionService;
import mvc.command.CommandHandler;

public class SearchExhibitionHandler implements CommandHandler {

	// 필드
	private String FORM_VIEW = "/view/exhibition/searchExhibition.jsp";
	private SearchExhibitionService searchExhibitionService = new SearchExhibitionService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("SearchExhibitionHandler-process()진입");

		if (request.getMethod().equalsIgnoreCase("get")) { // 요청방식이 get 방식이면 FORM_VIEW 보여주기
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("post")) { // 요청방식이 post 방식이면 글 등록 처리
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 405오류
			return null;
		}
	}
	
	//폼 보여주기
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return request.getContextPath() + FORM_VIEW;
	}

	
	//결과 처리하기
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 파라미터받기
		String location = request.getParameter("location");
		String [] yearMonth = request.getParameterValues("yearMonth");

		System.out.println("location" + location);
		System.out.println("yearMonth0" + yearMonth[0]);
		System.out.println("yearMonth1" + yearMonth[1]);

		// 비즈니스로직 처리
		List<Exhibition> filterSearchResult = searchExhibitionService.filterSearch(location, yearMonth[0], yearMonth[1]);

		// Model
		request.setAttribute("filterSearchResult", filterSearchResult);
		

		// View
		return request.getContextPath() + FORM_VIEW;
		// 4.View - 성공시: view/newArticleSeuccess.jsp이동
		// 실패시 FORM_VIEW로 이동
		// return request.getContextPath() +
		// "/view/exhibition/writeExhibitionSuccess.jsp";
	}

}
