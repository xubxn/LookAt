package review.model;
import java.util.Date;
import java.util.Map;

public class ModifyRequest {

	private String member_id;
	private int review_no;
	private int exhibition_no;
	private String review_img;
	private Date review_date;
	private String details_review;
	private String review_title;



	public ModifyRequest(String member_id, int review_no, int exhibition_no, String review_img, Date review_date,
				String details_review, String review_title) {
			this.member_id = member_id;
			this.review_no = review_no;
			this.exhibition_no = exhibition_no;
			this.review_img = review_img;
			this.review_date = review_date;
			this.details_review = details_review;
			this.review_title = review_title;
		}
		
	

	public ModifyRequest(String member_id, int review_no, String review_img, String details_review,
			String review_title) {
		this.member_id = member_id;
		this.review_no = review_no;
		this.review_img = review_img;
		this.details_review = details_review;
		this.review_title = review_title;
	}





	public String getMember_id() {
			return member_id;
		}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public int getReview_no() {
		return review_no;
	}


	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}


	public int getExhibition_no() {
		return exhibition_no;
	}


	public void setExhibition_no(int exhibition_no) {
		this.exhibition_no = exhibition_no;
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


	public String getDetails_review() {
		return details_review;
	}


	public void setDetails_review(String details_review) {
		this.details_review = details_review;
	}


	public String getReview_title() {
		return review_title;
	}


	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}



	public void validate(Map<String,Boolean>errors) {
		if(review_title==null||review_title.trim().isEmpty()) {
			errors.put("Q_title",Boolean.TRUE);
			if(details_review==null||details_review.trim().isEmpty()) {	
				errors.put("Q_details",Boolean.TRUE);
			}
		}	
	}
}
