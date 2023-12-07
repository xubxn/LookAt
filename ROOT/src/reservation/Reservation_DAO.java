package reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import exhibition.model.Exhibition;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import reservation.model.Reservation;

public class Reservation_DAO {
    // 예매 정보를 저장하는 메서드
    public void saveReservation(Reservation_DTO reservation) {
    	Connection conn=null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String query = "INSERT INTO reservation (member_id, exhibition_no, price_no, reser_date, going_date, total_adult, total_student, total_baby, total_price) " +
		    			"VALUES (?, ?, ?, now(), ?, ?, ?, ?, ?)";
        try {
            // 데이터베이스 연결
            conn = ConnectionProvider.getConnection();
            System.out.println("연결완료");
            // SQL 쿼리 작성
            // PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(query);
            
            // 매개변수 설정
            pstmt.setString(1, reservation.getMember_id());
            pstmt.setInt(2, reservation.getExhibition_no());
            pstmt.setInt(3, reservation.getPrice_no());
            pstmt.setDate(4, new java.sql.Date(reservation.getGoing_date().getTime()));
            pstmt.setInt(5, reservation.getTotal_adult());
            pstmt.setInt(6, reservation.getTotal_student());
            pstmt.setInt(7, reservation.getTotal_baby());
            pstmt.setInt(8, reservation.getTotal_price());
            
//            pstmt.setInt(1, 2); // 예약 번호 고정 값
//            pstmt.setString(2, "uu"); // 회원 ID 고정 값
//            pstmt.setInt(3, 1); // 전시회 번호 고정 값
//            pstmt.setInt(4, 20000); // 가격 번호 고정 값
//            pstmt.setString(5, "수원"); // 주소 고정 값
            // 쿼리 실행
            pstmt.executeUpdate();
            // 리소스 해제
            
        } catch (SQLException e) {
        	System.out.println("연결이 되지 않았습니다.");
            e.printStackTrace();
        } finally {
            // 데이터베이스 연결 종료
        	try { if(conn != null) conn.close(); }
			catch (Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); }
			catch (Exception e) { e.printStackTrace(); }
			try { if(rs != null) rs.close(); }
			catch (Exception e) { e.printStackTrace(); }
        }
    }
     
    
    // 예매 정보를 삭제하는 메서드, 예약 번호로 검색하여 삭제
    public void deleteReservation(int reservation_no) {
    	String query = "DELETE FROM reservation WHERE reservation_no=?";
    	Connection conn=null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
        try {
            // 데이터베이스 연결
            conn = ConnectionProvider.getConnection();
            
            // SQL 쿼리 작성
            
            // PreparedStatement 객체 생성
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            
            // 매개변수 설정
            preparedStatement.setInt(1, reservation_no);
            
            // 쿼리 실행
            preparedStatement.executeUpdate();
            
            // 리소스 해제
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 데이터베이스 연결 종료
        	try { if(conn != null) conn.close(); }
			catch (Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); }
			catch (Exception e) { e.printStackTrace(); }
			try { if(rs != null) rs.close(); }
			catch (Exception e) { e.printStackTrace(); }
        }
    }
    
    public Reservation selectReservation(Connection conn, int exhibition_no) {
        String query = "select e.exhibition_no, e.title, e.open_date, e.end_date, e.PLACE, e.thumbnail, e.details_img, e.introduction, " + 
        		"p.price_no, p.price_adult, p.price_student, p.price_baby, l.details_place " + 
        		"from exhibition e, price p, location l " + 
        		"where e.exhibition_no = p.exhibition_no " + 
        		"and e.PLACE = l.place "+
        		"GROUP BY e.exhibition_no "+
        		"having e.exhibition_no = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Reservation reserve = null; // Exhibition 객체 생성
        
        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, exhibition_no);
        
            rs = pstmt.executeQuery();
        
            if (rs.next()) {
                int no = rs.getInt("exhibition_no");
                int price_no = rs.getInt("price_no");
                String title = rs.getString("title");
                Date open_date = rs.getDate("open_date");
                Date end_date = rs.getDate("end_date");
                String PLACE = rs.getString("PLACE");
                String thumbnail = rs.getString("thumbnail");
                String details_img = rs.getString("details_img");
                String introduction = rs.getString("introduction");
                int price_adult = rs.getInt("price_adult");
                int price_student = rs.getInt("price_student");
                int price_baby = rs.getInt("price_baby");
                String details_place = rs.getString("details_place");
                // 필요한 값들을 가져와서 Exhibition 객체에 설정
                reserve = new Reservation(no, price_no, title, open_date, end_date, PLACE, thumbnail, details_img, introduction, price_adult, price_student, price_baby, details_place); // Exhibition 객체 생성 후 값 설정
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
        }
        
        return reserve; // Exhibition 객체 리턴
    }
}
