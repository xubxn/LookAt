package mypage.model;

public class MyReview  {
	  private int review_no;
	   private String  member_id;
	   private int exhibition_no;
	   private String review_img;
	   private String title;
	   private String thumbnail;
	   
	   
	public MyReview(int review_no, String member_id, int exhibition_no, String review_img, String title,
			String thumbnail) {
		this.review_no = review_no;
		this.member_id = member_id;
		this.exhibition_no = exhibition_no;
		this.review_img = review_img;
		this.title = title;
		this.thumbnail = thumbnail;
	}


	public int getReview_no() {
		return review_no;
	}


	public void setReview_no(int review_no) {
		this.review_no = review_no;
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


	public String getReview_img() {
		return review_img;
	}


	public void setReview_img(String review_img) {
		this.review_img = review_img;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	   
	}
