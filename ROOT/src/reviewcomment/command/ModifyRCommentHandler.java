package reviewcomment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reviewcomment.model.RComment;
import reviewcomment.service.RCService;
import mvc.command.CommandHandler;

//요청주소 http://localhost/comment/modify.do
//댓글 수정처리 담당 컨트롤러
/*data:{ //ajax방식으로  서버로 전송되는 data=>서버입장에서는 파라미터안에 담겨진 data
	commentno:commentno,
	boardno:boardno,
	writerid:writerid,
	content:content  }*/
public class ModifyRCommentHandler implements CommandHandler {

	private RCService reviewCommentService = new RCService();
	@Override
	public String process(  HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		int review_comment_no = Integer.parseInt(request.getParameter("review_comment_no"));//댓글번호
		int review_no   = Integer.parseInt(request.getParameter("review_no"));
		String member_id= request.getParameter("member_id"); //댓글작성자id
		String details_comment = request.getParameter("details_comment"); //수정하려는 댓글 새내용
		System.out.println(details_comment);
		
		//2.비즈니스로직<->Serivce<->DAO<->DB
		//(int review_comment_no, int review_no, String details_comment, String member_id)
		RComment comment  = new RComment(review_comment_no,review_no,details_comment, member_id);
		//파라미터 Comment : 댓글번호,원글번호,댓글작성자id,댓글 새내용
		//리턴  int : 수정성공시 1반환, 수정실패시 0반환
		int rowCnt =reviewCommentService.modifyRComment(comment);
		
		String modifyResult = "";
		if(rowCnt==1) modifyResult = "success";  
		if(rowCnt==0) modifyResult = "fail";  
		
		System.out.println(comment);
		//3.Model &4.View
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(modifyResult);
		return null;
	}

}
