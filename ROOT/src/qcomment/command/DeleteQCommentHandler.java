package qcomment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qcomment.service.QCommentService;

public class DeleteQCommentHandler implements CommandHandler{

	private QCommentService qcommentService=new QCommentService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int a_reply_no =Integer.parseInt(request.getParameter("A_reply_no"));
		qcommentService.delete(a_reply_no);
		
		response.sendRedirect(request.getContextPath()+"Qlist.do");
		return null;
	}

}
