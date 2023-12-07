package exhibition.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import exhibition.exception.ExhibitionNotFoundException;
import exhibition.model.Exhibition;
import exhibition.service.ReadExhibitionService;
import mvc.command.CommandHandler;

public class ReadExhibitionHandler implements CommandHandler {

   private ReadExhibitionService readExhibitionService = new ReadExhibitionService();
   
   @Override
   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      //1.파라미터받기
      String strNo = request.getParameter("no"); //상세하게 보고싶은 글번호
      int no = Integer.parseInt(strNo); //String을 int로 변환
      System.out.println("ReadExhibitionService - process() no= " + no);
      

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("AUTH_USER");
		session.setAttribute("AUTH_USER", user); //p607 55라인
	
      
      //2.비즈니스로직 <-> Service <-> DAO <-> DB
      try {
         Exhibition detailData = readExhibitionService.getDetail(no);
         
         //3.Model
         request.setAttribute("detailData", detailData);
         
         //4.view
         return request.getContextPath() + "/view/exhibition/readExhibition.jsp";
         
      } catch (ExhibitionNotFoundException e) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND); //404
         return null;
      }
            
   }
   
}