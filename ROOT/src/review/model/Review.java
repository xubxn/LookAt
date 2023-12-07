package review.model;

import java.util.Date;
import java.util.Map;

public class Review {
	private Integer review_no;
	private String  member_id;
	//private Integer  exhibition_no;
	private String  review_img;
	private Date    review_date;
	private int     review_score;
	private String  review_title;
	private String  details_review;
	private int     exhibition_no;
	private String  thumbnail;
	

	public Review() {}
	
	
	public Review(String thumbnail){
		this.thumbnail=thumbnail;
	}
	
	
	
	//리뷰페이지로 넘어가기
	public Review(Integer review_no,String  member_id,String review_img,Date review_date,int review_score,String review_title, String details_review) {
		this.review_no=review_no;
		this.member_id=member_id;
		this.review_img=review_img;
		this.review_date=review_date;
		this.review_score=review_score;
		this.review_title=review_title;
		this.details_review=details_review;
	}
	
	
	//리뷰 입력 생성자
	public Review(Integer review_no, String member_id, String review_img, Date review_date, int review_score,
			String review_title, String details_review, int exhibition_no, String thumbnail) {
		this.review_no = review_no;
		this.member_id = member_id;
		this.review_img = review_img;
		this.review_date = review_date;
		this.review_score = review_score;
		this.review_title = review_title;
		this.details_review = details_review;
		this.exhibition_no = exhibition_no;
		this.thumbnail = thumbnail;
	}


	//리뷰글쓰기로 넘어가기
	public Review(String member_id, int exhibition_no) {
		this.member_id = member_id;
		this.exhibition_no = exhibition_no;
	}

	
	
	public Integer getReview_no() {
		return review_no;
	}
	public void setReview_no(Integer review_no) {
		this.review_no = review_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReview_img() {
		return review_img;
	}
	public void setReview_img(String review_img) {
		this.review_img = review_img;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public int getReview_score() {
		return review_score;
	}
	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
		
	public String getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
	public String getDetails_review() {
		return details_review;
	}
	public void setDetails_review(String details_review) {
		this.details_review = details_review;
	}
	public int getExhibition_no() {
		return exhibition_no;
	}
	
	public void setExhibition_no(int exhibition_no) {
		this.exhibition_no = exhibition_no;
	}
	
	
	public void validate(Map<String,Boolean> errors) {
		if(  review_title==null ||  review_title.trim().isEmpty() ) {
			errors.put("review_title", Boolean.TRUE);
		}
		if(  details_review==null ||  details_review.isEmpty() ) {
			errors.put("review_content", Boolean.TRUE);
		}
		if(  review_img==null ||  review_img.isEmpty() ) {
			errors.put("review_img", Boolean.TRUE);
		}
		if(  review_score==0 ) {
			errors.put("review_score", Boolean.TRUE);
		}
	}
	

	@Override
	public String toString() {
		return "Review [review_no=" + review_no + ", member_id=" + member_id + ", review_img=" + review_img
				+ ", review_date=" + review_date + ", review_score=" + review_score + ", review_title=" + review_title
				+ ", details_review=" + details_review + "]";
	}
	
	
	
	
}








