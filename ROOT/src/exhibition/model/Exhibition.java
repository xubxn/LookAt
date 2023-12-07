package exhibition.model;

import java.sql.Date;
import java.util.Map;

public class Exhibition {
	
		//필드
	private int exhibition_no;
	private String title;
	private Date open_date;
	private Date end_date;
	private String thumbnail;
	private String details_img;
	private String introduction;
	private int price_no;
	private int price_adult;
	private int price_student;
	private int price_baby;
	private int loc_no;
	private String place;
	private String loc;
	private String details_place;
	
		
		
		//생성자
		//기본생성자
		public Exhibition () {}
		
		//글번호없는 생성자
		public Exhibition(String title, Date open_date, Date end_date, String place, String thumbnail, String details_img,
				String introduction) {
			this.title = title;
			this.open_date = open_date;
			this.end_date = end_date;
			this.place = place;
			this.thumbnail = thumbnail;
			this.details_img = details_img;
			this.introduction = introduction;
		}
		
		//writeExhibitionHandler에서 첨부파일 기능을 구현하는데 필요해서 만든 생성자
		public Exhibition(String title, Date open_date, Date end_date, String thumbnail, String details_img,
				String introduction, int price_adult, int price_student, int price_baby, String place, String loc,
				String details_place) {
			this.title = title;
			this.open_date = open_date;
			this.end_date = end_date;
			this.thumbnail = thumbnail;
			this.details_img = details_img;
			this.introduction = introduction;
			this.price_adult = price_adult;
			this.price_student = price_student;
			this.price_baby = price_baby;
			this.place = place;
			this.loc = loc;
			this.details_place = details_place;
		}
		
		

		public Exhibition(String title, String thumbnail, String introduction) {
			super();
			this.title = title;
			this.thumbnail = thumbnail;
			this.introduction = introduction;
		}

		//필수입력 - p638 31라인
		public void validate(Map<String, Boolean> errors) {
			if ( title == null || title.isEmpty()) {
				errors.put("title", Boolean.TRUE);
			}
			if ( open_date == null) {
				errors.put("open_date", Boolean.TRUE);
			}
			if ( place == null || place.isEmpty()) {
				errors.put("place", Boolean.TRUE);
			}
			if ( thumbnail == null || thumbnail.isEmpty()) {
				errors.put("thumbnail", Boolean.TRUE);
			}
			if ( details_img == null || details_img.isEmpty()) {
				errors.put("details_img", Boolean.TRUE);
			}
			if ( details_place == null || details_place.isEmpty()) {
				errors.put("details_img", Boolean.TRUE);
			}
		}

		public Exhibition(int exhibition_no, String title, Date open_date, Date end_date, String thumbnail,
				String details_img, String introduction, int price_no, int price_adult, int price_student,
				int price_baby, int loc_no, String place, String loc, String details_place) {
			this.exhibition_no = exhibition_no;
			this.title = title;
			this.open_date = open_date;
			this.end_date = end_date;
			this.thumbnail = thumbnail;
			this.details_img = details_img;
			this.introduction = introduction;
			this.price_no = price_no;
			this.price_adult = price_adult;
			this.price_student = price_student;
			this.price_baby = price_baby;
			this.loc_no = loc_no;
			this.place = place;
			this.loc = loc;
			this.details_place = details_place;
		}

		@Override
		public String toString() {
			return "Exhibition [exhibition_no=" + exhibition_no + ", title=" + title + ", open_date=" + open_date
					+ ", end_date=" + end_date + ", thumbnail=" + thumbnail + ", details_img=" + details_img
					+ ", introduction=" + introduction + ", price_no=" + price_no + ", price_adult=" + price_adult
					+ ", price_student=" + price_student + ", price_baby=" + price_baby + ", loc_no=" + loc_no
					+ ", place=" + place + ", loc=" + loc + ", details_place=" + details_place + "]";
		}

		public int getExhibition_no() {
			return exhibition_no;
		}

		public void setExhibition_no(int exhibition_no) {
			this.exhibition_no = exhibition_no;
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

		public int getPrice_no() {
			return price_no;
		}

		public void setPrice_no(int price_no) {
			this.price_no = price_no;
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

		public int getLoc_no() {
			return loc_no;
		}

		public void setLoc_no(int loc_no) {
			this.loc_no = loc_no;
		}

		public String getPlace() {
			return place;
		}

		public void setPlace(String place) {
			this.place = place;
		}

		public String getLoc() {
			return loc;
		}

		public void setLoc(String loc) {
			this.loc = loc;
		}

		public String getDetails_place() {
			return details_place;
		}

		public void setDetails_place(String details_place) {
			this.details_place = details_place;
		}
}
