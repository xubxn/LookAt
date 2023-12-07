package admin.command;

import java.io.PrintWriter;






import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import admin.model.Exhibition;
import admin.service.ExhibitionService;
import mvc.command.CommandHandler;

//요청주소 http://localhost/sidoSelect.do
//동적 select 구현 담당컨트롤러이다

public class ExhibitionSelectController implements CommandHandler {

	private ExhibitionService exhibitionService = new ExhibitionService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ExhibitionService 진입");
		//1.파라미터받기
		String strNo = request.getParameter("searchvalue");
		int no = Integer.parseInt(strNo);
		System.out.println("no = "+ no);
		//2.비즈니스로직<->Service<->DAO<->DB
		
		  List<Exhibition> exhibitionList = exhibitionService.getExhibition(no);
		
		//3.Model & 4.View
		//view에 응답할 때에는 JSONObject객체를 문자열로 변환시켜서 넘긴다
		response.setCharacterEncoding("utf-8");
		//response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		JSONObject totalObj= new JSONObject();
		JSONArray exhibitionArr = new JSONArray();
		
		for( int i=0; i<exhibitionList.size(); i++ ) {
			JSONObject exhibitionObj= new JSONObject();
			exhibitionObj.put("exhibition_no",exhibitionList.get(i).getExhibition_no());
			exhibitionObj.put("title",exhibitionList.get(i).getTitle());
			exhibitionArr.add(exhibitionObj); //1명의 회원정보를 배열에 추가
		}

		totalObj.put("exhibition",exhibitionArr);
		
		String jsonStr = totalObj.toJSONString();
		System.out.println("jsonStr ="+jsonStr); //콘솔출력.확인용
		out.print(jsonStr); //client로 보내기
		return null;
	}

}




