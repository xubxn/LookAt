package qcomment.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qcomment.model.QComment;
import qcomment.service.QCommentService;

public class InsertQCommentHandler implements CommandHandler {
    private static final String FORM_VIEW = "/view/readQuestion.jsp";
    private QCommentService qcommentService = new QCommentService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equalsIgnoreCase("get")) {
            return processForm(request, response);
        } else if (request.getMethod().equalsIgnoreCase("post")) {
            return processSubmit(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processForm(HttpServletRequest request, HttpServletResponse response) {
        return request.getContextPath() + FORM_VIEW;
    }

    private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors);
		
        int QA_no = Integer.parseInt(request.getParameter("QA_no"));
        String A_details = request.getParameter("A_details");

        QComment qcomment = new QComment();
        qcomment.setQA_no(QA_no);
        qcomment.setA_details(A_details);
        
        qcommentService.insertQComment(qcomment);
        System.out.println(QA_no);

        response.sendRedirect(request.getContextPath()+"/Qread.do?QA_no="+QA_no);
        return null;
    }

}