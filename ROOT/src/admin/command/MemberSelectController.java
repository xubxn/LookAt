package admin.command;

import java.io.PrintWriter;




import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import admin.service.MemberService;
import member.model.Member;
import mvc.command.CommandHandler;

//요청주소 http://localhost/sidoSelect.do
//동적 select 구현 담당컨트롤러이다

public class MemberSelectController implements CommandHandler {

	private MemberService  memberService = new MemberService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		String name = request.getParameter("searchValue");
		System.out.println("name = "+ name);
		//2.비즈니스로직<->Service<->DAO<->DB
		
		  List<Member> memberList = memberService.getMember(name);
		
		//3.Model & 4.View
		//view에 응답할 때에는 JSONObject객체를 문자열로 변환시켜서 넘긴다
		response.setCharacterEncoding("utf-8");
		//response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		JSONObject totalObj= new JSONObject();
		JSONArray memberArr = new JSONArray();
		
		for( int i=0; i<memberList.size(); i++ ) {
			JSONObject memberObj= new JSONObject();
			memberObj.put("member_id",memberList.get(i).getMember_id());
			memberObj.put("member_name",memberList.get(i).getMember_name());
			memberObj.put("member_email",memberList.get(i).getMember_email());
			memberObj.put("member_date",memberList.get(i).getMember_date().toString());
			memberObj.put("member_tel",memberList.get(i).getMember_tel());
			memberObj.put("quit_Y",memberList.get(i).getQuit_Y());
			memberArr.add(memberObj); //1명의 회원정보를 배열에 추가
		}

		totalObj.put("member",memberArr);
		
		String jsonStr = totalObj.toJSONString();
		System.out.println("jsonStr ="+jsonStr); //콘솔출력.확인용
		out.print(jsonStr); //client로 보내기
		return null;
	}

}




