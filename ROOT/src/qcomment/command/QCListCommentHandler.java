package qcomment.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qcomment.model.QComment;
import qcomment.service.QCommentService;

public class QCListCommentHandler implements CommandHandler{
	
	QCommentService qcommentService=new QCommentService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int QA_no=Integer.parseInt((request.getParameter("QA_no")));
		List<QComment> qcomment=qcommentService.getQCommentList(QA_no);
		
		request.setAttribute("qcomment",qcomment);
		
		return null;
	}
}
