package mypage.model;

import java.util.Date;

//마이 페이지 - 예매 내역
public class MyReservation {
	private int reservation_no;
	private String member_id;
	private int exhibition_no;
	private int price_no;
	private Date reser_date;
	private Date going_date;
	private String title;
	private String place;
	private String thumbnail;
	private String details_img;
	private String introduction;
	private int total_adult;
	private int total_student;
	private int total_baby;
	private int total;
	private String details_place;
	
	
	//기본 생성자
	public MyReservation(){}
	
	//전체 - 상세 예매 페이지
	public MyReservation(int reservation_no, String member_id, int exhibition_no, int price_no, Date reser_date,
			Date going_date, String title, String place, String thumbnail, String details_img, String introduction,  int total_adult, int total_student,
			int total_baby, int total, String details_place) {
		this.reservation_no = reservation_no;
		this.member_id = member_id;
		this.exhibition_no = exhibition_no;
		this.price_no = price_no;
		this.reser_date = reser_date;
		this.going_date = going_date;
		this.title = title;
		this.place = place;
		this.thumbnail = thumbnail;
		this.details_img = details_img;
		this.introduction = introduction;
		this.total_adult = total_adult;
		this.total_student = total_student;
		this.total_baby = total_baby;
		this.total = total;
		this.details_place = details_place;
	}
	
	
	public Date getReser_date() {
		return reser_date;
	}

	public String getDetails_img() {
		return details_img;
	}

	public String getIntroduction() {
		return introduction;
	}

	public int getTotal_adult() {
		return total_adult;
	}

	public int getTotal_student() {
		return total_student;
	}

	public int getTotal_baby() {
		return total_baby;
	}

	public MyReservation(int reservation_no, String member_id, int exhibition_no, int price_no, Date going_date,
			String title, String place, String thumbnail, int total, String details_place) {
		this.reservation_no = reservation_no;
		this.member_id = member_id;
		this.exhibition_no = exhibition_no;
		this.price_no = price_no;
		this.going_date = going_date;
		this.title = title;
		this.place = place;
		this.thumbnail = thumbnail;
		this.total = total;
		this.details_place = details_place;
	}


	//모델
	public MyReservation(int reservation_no, String member_id, Date going_date, String title, String place,
			String thumbnail, int total, String details_place) {
		this.reservation_no = reservation_no;
		this.member_id = member_id;
		this.going_date = going_date;
		this.title = title;
		this.place = place;
		this.thumbnail = thumbnail;
		this.total = total;
		this.details_place = details_place;
	}
	

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

	public Date getGoing_date() {
		return going_date;
	}

	public void setGoing_date(Date going_date) {
		this.going_date = going_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDetails_place() {
		return details_place;
	}

	public void setDetails_place(String details_place) {
		this.details_place = details_place;
	}
	
}	
	

	