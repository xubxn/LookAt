package reservation.model;

import java.util.Date;

public class Reservation {
	private int exhibition_no;
	private int price_no;
	private String title;
	private Date open_date;
	private Date end_date;
	private String PLACE;
	private String thumbnail;
	private String details_img;
	private String introduction;
	private int price_adult;
	private int price_student;
	private int price_baby;
	private String details_place;
	
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getOpen_date() {
		return open_date;
	}
	public void setOpen_date(Date open_date) {
		this.open_date = open_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getPLACE() {
		return PLACE;
	}
	public void setPLACE(String pLACE) {
		PLACE = pLACE;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getDetails_img() {
		return details_img;
	}
	public void setDetails_img(String details_img) {
		this.details_img = details_img;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getPrice_adult() {
		return price_adult;
	}
	public void setPrice_adult(int price_adult) {
		this.price_adult = price_adult;
	}
	public int getPrice_student() {
		return price_student;
	}
	public void setPrice_student(int price_student) {
		this.price_student = price_student;
	}
	public int getPrice_baby() {
		return price_baby;
	}
	public void setPrice_baby(int price_baby) {
		this.price_baby = price_baby;
	}
	public String getDetails_place() {
		return details_place;
	}
	public void setDetails_place(String details_place) {
		this.details_place = details_place;
	}
	public Reservation() {}
	
	public Reservation(int exhibition_no, int price_no, String title, Date open_date, Date end_date, String PLACE, String thumbnail,
			String details_img, String introduction, int price_adult, int price_student, int price_baby,
			String details_place) {
		this.exhibition_no = exhibition_no;
		this.price_no=price_no;
		this.title = title;
		this.open_date = open_date;
		this.end_date = end_date;
		this.PLACE = PLACE;
		this.thumbnail = thumbnail;
		this.details_img = details_img;
		this.introduction = introduction;
		this.price_adult = price_adult;
		this.price_student = price_student;
		this.price_baby = price_baby;
		this.details_place = details_place;
	}
	
}