package question.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import question.service.DeleteQuestionService;

public class DeleteQuestionHandler implements CommandHandler{

	private DeleteQuestionService deleteQuestionService=new DeleteQuestionService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터 받기 삭제하고싶은 글번호 받기
		int no =Integer.parseInt(request.getParameter("QA_no"));
		System.out.println(no);
		//2.비즈니스 로직 <->service
		deleteQuestionService.delete(no);
		//3.
		  
		//4.
		response.sendRedirect(request.getContextPath()+"Qlist.do");
		return null;
	}

		
}
