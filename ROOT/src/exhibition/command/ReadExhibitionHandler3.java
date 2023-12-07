package exhibition.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import exhibition.exception.ExhibitionNotFoundException;
import exhibition.model.ExhibitionJoin;
import exhibition.service.ReadExhibitionService2;
import mvc.command.CommandHandler;

public class ReadExhibitionHandler3 implements CommandHandler {
	private static final String FORM_VIEW = "/view/reservation.jsp";
	private ReadExhibitionService2 readExhibitionService = new ReadExhibitionService2();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return null;
		}
		private String processForm(HttpServletRequest request, HttpServletResponse response)  {	
			return FORM_VIEW;
		}
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		//1.파라미터받기
		String strNo = request.getParameter("no"); //상세하게 보고싶은 글번호
		  if (strNo == null || strNo.isEmpty()) {
		        response.sendError(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
		        return null;
		    }
		    
		    int no = Integer.parseInt(strNo); //String을 int로 변환
		    System.out.println("ReadExhibitionService - process() no= " + no);
		
		//2.비즈니스로직 <-> Service <-> DAO <-> DB
		try {
			System.out.println("접근완료");
			ExhibitionJoin detailData = readExhibitionService.getDetail(no);
			
			//3.Model
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("AUTH_USER");
			session.setAttribute("AUTH_USER", user); //p607 55라인
			request.setAttribute("detailData", detailData);
			
			//4.view
			return request.getContextPath() + "/view/exhibition/readExhibition.jsp";
			
		} catch (ExhibitionNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND); //404
			System.out.println("접근실패");
			return null;
		}
				
	}
	
}
