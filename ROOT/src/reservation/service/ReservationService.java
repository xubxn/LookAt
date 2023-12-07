package reservation.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import reservation.Reservation_DAO;
import reservation.model.Reservation;

public class ReservationService {
	private Reservation_DAO reservationDAO = new Reservation_DAO();
	
	//reservation 상세정보
	public Reservation selectReservation(int exhibition_no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			Reservation reserve = reservationDAO.selectReservation(conn, exhibition_no);
			return reserve;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	
}
