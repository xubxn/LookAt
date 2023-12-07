package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.JoinService;

public class JoinProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String memberid = req.getParameter("memberid");
		String memberpw = req.getParameter("memberpw");
		String memberdate = req.getParameter("memberdate");
		String membername = req.getParameter("membername");
		String membertel = req.getParameter("membertel");
		String memberemail = req.getParameter("memberemail");
		
		JoinService joinService = new JoinService();
		int rowCnt = joinService.join(memberid,memberpw,memberdate,membername,membertel,memberemail);
		
		req.setAttribute("rowCnt", rowCnt);
		req.setAttribute(membername, memberid);
		return null;
	}

}
