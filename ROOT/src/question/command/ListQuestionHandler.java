package question.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import question.service.ListQuestionService;
import question.service.QuestionPage;

public class ListQuestionHandler implements CommandHandler {

	private ListQuestionService listService=new ListQuestionService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User AUTH_USER=(User)request.getSession().getAttribute("AUTH_USER");
		String pageNoVal=request.getParameter("pageNo");
		int pageNo=1;
		if(pageNoVal!=null) {
			pageNo=Integer.parseInt(pageNoVal);
		}
		if(AUTH_USER.getId().equals("admin")) {
		QuestionPage questionPage=listService.getQuestionPageAD(pageNo);
		request.setAttribute("QuestionPage",questionPage);
		}else {
		QuestionPage questionPage=listService.getQuestionPage(pageNo, AUTH_USER.getId());
		request.setAttribute("QuestionPage",questionPage);}
		
		return request.getContextPath()+"/view/listQuestion.jsp";
	}
}
