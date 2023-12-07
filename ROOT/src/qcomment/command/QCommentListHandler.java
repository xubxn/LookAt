package qcomment.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qcomment.model.QComment;
import qcomment.service.QCommentService;

public class QCommentListHandler implements CommandHandler {

	QCommentService qcommentService=new QCommentService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int QA_no=Integer.parseInt((request.getParameter("QA_no")));
		
		List<QComment> qcomments=qcommentService.getQCommentList(QA_no);
		
		System.out.println("qcomments="+qcomments);
		request.setAttribute("qcomments",qcomments);
		return null;
	}

}
