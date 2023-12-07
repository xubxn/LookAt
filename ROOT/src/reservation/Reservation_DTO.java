package reservation;

import java.util.Date;

public class Reservation_DTO {
	private int reservation_no;
	private String member_id;
	private int exhibition_no;
	private int price_no;
	private Date reser_date;
	private Date going_date;
	private int total_adult;
	private int total_student;
	private int total_baby;
	private int total_price;
	
	
	public int getReservation_no() {
		return reservation_no;
	}
	public void setReservation_no(int reservation_no) {
		this.reservation_no = reservation_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getExhibition_no() {
		return exhibition_no;
	}
	public void setExhibition_no(int exhibition_no) {
		this.exhibition_no = exhibition_no;
	}
	public int getPrice_no() {
		return price_no;
	}
	public void setPrice_no(int price_no) {
		this.price_no = price_no;
	}
	public Date getReser_date() {
		return reser_date;
	}
	public void setReser_date(Date reser_date) {
		this.reser_date = reser_date;
	}
	public Date getGoing_date() {
		return going_date;
	}
	public void setGoing_date(Date going_date) {
		this.going_date = going_date;
	}
	public int getTotal_adult() {
		return total_adult;
	}
	public void setTotal_adult(int total_adult) {
		this.total_adult = total_adult;
	}
	public int getTotal_student() {
		return total_student;
	}
	public void setTotal_student(int total_student) {
		this.total_student = total_student;
	}
	public int getTotal_baby() {
		return total_baby;
	}
	public void setTotal_baby(int total_baby) {
		this.total_baby = total_baby;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;

	}
	public Reservation_DTO(int reservation_no, String member_id, int exhibition_no, int price_no, Date reser_date,
		Date going_date, int total_adult, int total_student, int total_baby, int total_price) {
		this.reservation_no = reservation_no;
		this.member_id = member_id;
		this.exhibition_no = exhibition_no;
		this.price_no = price_no;
		this.reser_date = reser_date;
		this.going_date = going_date;
		this.total_adult = total_adult;
		this.total_student = total_student;
		this.total_baby = total_baby;
		this.total_price = total_price;
	}
	@Override
	public String toString() {
		return "Reservation_DTO [reservation_no=" + reservation_no + ", member_id=" + member_id + ", exhibition_no="
				+ exhibition_no + ", price_no=" + price_no + ", reser_date=" + reser_date + ", going_date=" + going_date
				+ ", total_adult=" + total_adult + ", total_student=" + total_student + ", total_baby=" + total_baby
				+ ", total_price=" + total_price + "]";
	}
	public Reservation_DTO() {
		
	}
}
