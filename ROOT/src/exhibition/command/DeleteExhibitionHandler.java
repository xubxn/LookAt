package exhibition.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhibition.service.DeleteExhibitionService;
import mvc.command.CommandHandler;

public class DeleteExhibitionHandler implements CommandHandler {

	private DeleteExhibitionService deleteExhibitionService = new DeleteExhibitionService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("DeleteExhibitionHandler-process() 진입");
		
		//1.파라미터받기
		int no = Integer.parseInt(request.getParameter("exhibitions"));//삭제하고 싶은 글번호
		System.out.println(no);
		
		//2.비즈니스로직<->Service<->DAO<->DB
		deleteExhibitionService.delete(no);
		
		//3.Model
		//4.View
		//여기에서는  성공시  목록보기요청
		response.sendRedirect(request.getContextPath()+"/exhibition/list.do");
		return null;
		}

}
