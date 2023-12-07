package price.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

//p531
//인터페이스란? - 상수, 추상메서드
//모든 핸들러(담당컨트롤러)는 반드시 interface CommandHandler를 구현해야 한다.
public class PriceHandler implements CommandHandler{
	private static final String FORM_VIEW = "/view/payment.jsp";

	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return processForm(request, response);			
		} else if(request.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}