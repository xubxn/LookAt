package reviewcomment.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import reviewcomment.model.RComment;
import reviewcomment.service.RCService;

public class InsertRCommentHandler implements CommandHandler {
    private static final String FORM_VIEW = "/view/reviewList/reviewList.jsp";
    private RCService rcommentService = new RCService();

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
    
		
        int review_no = Integer.parseInt(request.getParameter("review_no"));
        String member_id = request.getParameter("member_id");
        String details_comment = request.getParameter("details_comment");

        RComment rcomment = new RComment();
        rcomment.setReview_no(review_no);
        rcomment.setMember_id(member_id);
        rcomment.setDetails_comment(details_comment);
        
        rcommentService.insertQComment(rcomment);
        System.out.println(review_no);

        response.sendRedirect(request.getContextPath()+"/reviewRead.do?review_no="+review_no);
        return null;
    }

}