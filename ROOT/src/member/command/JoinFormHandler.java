package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//회원가입 폼 화면 보여주는 요청에 대한 담당 컨트롤러
public class JoinFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return req.getContextPath() + "view/member/Join.jsp";
	}

}
