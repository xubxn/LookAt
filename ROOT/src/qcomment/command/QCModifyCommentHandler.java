package qcomment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qcomment.model.QComment;
import qcomment.service.QCommentService;

public class QCModifyCommentHandler implements CommandHandler {

   private QCommentService qcommentService=new QCommentService();
   
   @Override
   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      int a_reply_no=Integer.parseInt(request.getParameter("a_reply_no"));
      int QA_no=Integer.parseInt(request.getParameter("QA_no"));
      String a_details=request.getParameter("a_details");
      
      QComment qcomment = new QComment(a_reply_no,QA_no,a_details);
      int rowCnt =qcommentService.modifyQComment(qcomment);
      String modifyResult = "";
      if(rowCnt==1) modifyResult = "success";  
      if(rowCnt==0) modifyResult = "fail";  
      response.setCharacterEncoding("utf-8");
      response.getWriter().write(modifyResult);
      
      return null;
   }

}