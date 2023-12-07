package member.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.JoinService;
import mvc.command.CommandHandler;

//요청주소 : /member/idCheck.do
//ID중복검사요청 담당 컨트롤러이다
public class IdCheckHandler implements CommandHandler {
	
	 JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//1. 파라미터받기 {inputId:유저가입력한id, //서버로 보내는 data}
		String member_id = request.getParameter("member_id"); //유저가 입력한 id
		System.out.println("IdCheckHandler-process=" + member_id);
		PrintWriter writer = response.getWriter();
		//2. 비즈니스로직 <-> service <-> dao <-> db 
		//파라미터 Stirng id - 유저가 입력한 id
		//리턴 int-이미 사용중인 id이면 1리턴, 사용가능한 id이면 0리턴, 그외 -1
		int result  = joinService.idCheck(member_id);
		System.out.println(result);
		if(result==0) { 
			writer.print("usable"); //브라우저에게 response할때 문자열 type으로 response한다. 
		}else { //여기에서는 간단하게 else로 처리
			writer.print("Not_usable"); 
		}
		/*리턴유형 문자열타입으로 view에게 응답
		사용 가능한  ID이면 "uasble"*/
		
		//3.model//4.view - 다른 페이지로 안감.
		return null;
	}

}
