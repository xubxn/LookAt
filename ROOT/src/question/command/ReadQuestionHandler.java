package question.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qcomment.model.QComment;
import qcomment.service.QCommentService;
import question.model.Question;
import question.service.QuestionNotFoundException;
import question.service.ReadQuestionService;

public class ReadQuestionHandler implements CommandHandler {

	private ReadQuestionService readService=new ReadQuestionService();
	private QCommentService commentService= new QCommentService();
	
@Override
public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String noVal=request.getParameter("QA_no").trim();
	int QA_no=Integer.parseInt(noVal);
	
	try {
		Question question=readService.getQuestion(QA_no);
		List<QComment> qcomment=commentService.getQCommentList(QA_no);
		
		request.setAttribute("question", question);
		request.setAttribute("qcomment", qcomment);
		return "/view/readQuestion.jsp";
	}catch(QuestionNotFoundException e) {
		request.getServletContext().log("no question",e);
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	
	return null;
	}
}
	
}
