package exhibition.model;

import java.sql.Date;
import java.util.Map;

public class ExhibitionJoin {

	//필드
	private int exhibition_no;
	private String title;
	private Date open_date;
	private Date end_date;
	private String place;
	private String thumbnail;
	private String details_img;
	private String introduction;
	private int price_adult;
	private int price_student;
	private int price_baby;
	private String details_place;
	
	//생성자
	//기본생성자
	public ExhibitionJoin () {}

	
	//글번호 없는 생성자
	public ExhibitionJoin(String title, Date open_date, Date end_date, String place, String thumbnail,
			String details_img, String introduction, int price_adult, int price_student, int price_baby,
			String details_place) {
		this.title = title;
		this.open_date = open_date;
		this.end_date = end_date;
		this.place = place;
		this.thumbnail = thumbnail;
		this.details_img = details_img;
		this.introduction = introduction;
		this.price_adult = price_adult;
		this.price_student = price_student;
		this.price_baby = price_baby;
		this.details_place = details_place;
	}


	
	
	//글번호 있는 생성자
	public ExhibitionJoin(int exhibition_no, String title, Date open_date, Date end_date, String place,
			String thumbnail, String details_img, String introduction, int price_adult, int price_student,
			int price_baby, String details_place) {
		super();
		this.exhibition_no = exhibition_no;
		this.title = title;
		this.open_date = open_date;
		this.end_date = end_date;
		this.place = place;
		this.thumbnail = thumbnail;
		this.details_img = details_img;
		this.introduction = introduction;
		this.price_adult = price_adult;
		this.price_student = price_student;
		this.price_baby = price_baby;
		this.details_place = details_place;
	}

	public ExhibitionJoin(String id, int no, String title2, Date open_date2, Date end_date2, String place2,
			String thumbnail2, String details_img2, String introduction2, int price_adult2, int price_student2,
			int price_baby2, String details_place2) {
		// TODO Auto-generated constructor stub
	}


	//메소드
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

	//toString
	@Override
	public String toString() {
		return "ExhibitionJoin [exhibition_no=" + exhibition_no + ", title=" + title + ", open_date=" + open_date
				+ ", end_date=" + end_date + ", place=" + place + ", thumbnail=" + thumbnail + ", details_img="
				+ details_img + ", introduction=" + introduction + ", price_adult=" + price_adult + ", price_student="
				+ price_student + ", price_baby=" + price_baby + ", details_place=" + details_place + "]";
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
	
}
