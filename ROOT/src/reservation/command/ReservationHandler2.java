package reservation.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import mvc.command.CommandHandler;
import reservation.Reservation_DAO;
import reservation.Reservation_DTO;
import reservation.model.Reservation;
import reservation.service.ReservationService;

//p531
//인터페이스란? - 상수, 추상메서드
//모든 핸들러(담당컨트롤러)는 반드시 interface CommandHandler를 구현해야 한다.
public class ReservationHandler2 implements CommandHandler{
	private static final String FORM_VIEW = "/view/reservation.jsp";
	private Reservation_DAO reservationDAO = new Reservation_DAO();
	private ReservationService reService = new ReservationService();

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {	

		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return null;
		
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user = (User)request.getSession().getAttribute("AUTH_USER");
		String id = user.getId();
		request.setAttribute("reservationid", id);
		
		String ex_noParam = request.getParameter("no");
		//       System.out.println(ex_noParam);
		int ex_no = 0; // 초기값 0으로 설정
		
		// 값이 null이 아니고 빈 문자열이 아닌 경우에만 처리
		if (ex_noParam != null && !ex_noParam.trim().isEmpty()) {
			try {
				ex_no = Integer.parseInt(ex_noParam);
				// 정상적으로 변환된 값(ex_no)을 사용하는 로직 작성
			} catch (NumberFormatException e) {
				// 예외 처리 페이지에서 오류 메시지를 출력할 수 있도록 설정
				throw new Exception("전시회 번호를 올바르게 입력해주세요.");
			}
		} else {
			throw new Exception("전시회 번호를 입력해주세요.");
		}
		
		Reservation res = reService.selectReservation(ex_no);
		System.out.println(res.getThumbnail());
		request.setAttribute("res", res);
		return request.getContextPath()+FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		Reservation_DTO reservation = new Reservation_DTO();
			
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("AUTH_USER");
			String memberid = user.getId();
//			String member_id = request.getParameter("member_id");
			int exhibition_no = Integer.parseInt(request.getParameter("exhibition_no"));
			int price_no = Integer.parseInt(request.getParameter("price_no"));
			Date going_date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("going_date"));
			int total_adult = Integer.parseInt(request.getParameter("total_adult"));
			int total_student = Integer.parseInt(request.getParameter("total_student"));
			int total_baby = Integer.parseInt(request.getParameter("total_baby"));
			int total_price = Integer.parseInt(request.getParameter("total_price"));

			reservation.setMember_id(memberid);
			reservation.setExhibition_no(exhibition_no);
			reservation.setPrice_no(price_no);
			reservation.setGoing_date(going_date);
			reservation.setTotal_adult(total_adult);
			reservation.setTotal_student(total_student);
			reservation.setTotal_baby(total_baby);
			reservation.setTotal_price(total_price);
			reservationDAO.saveReservation(reservation);


			System.out.println(reservation.toString());

			
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return request.getContextPath() + FORM_VIEW;
	}

}