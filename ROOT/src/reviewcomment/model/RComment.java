package reviewcomment.model;

import java.util.Date;
import java.util.Map;

public class RComment {
	private int  review_comment_no; //댓글번호. commentno(pk)
	private int  review_no; //원글번호. boardno
	private String  details_comment; //댓글작성자id. cmt_writerid
	private String  member_id; //댓글내용. cmt_content
	private Date  comment_date; //댓글등록일. cmt_writedate
	
	public RComment(RComment review_Comment_no, String details_comment, String member_id) {		
		this.details_comment=details_comment;
		this.member_id=member_id;		
	}

	
		
	
	public RComment(int review_comment_no, int review_no, String details_comment, String member_id, Date comment_date) {
		super();
		this.review_comment_no = review_comment_no;
		this.review_no = review_no;
		this.details_comment = details_comment;
		this.member_id = member_id;
		this.comment_date = comment_date;
	}




	public RComment(int review_comment_no, int review_no, String details_comment, String member_id) {
		this.review_comment_no = review_comment_no;
		this.review_no = review_no;
		this.details_comment = details_comment;
		this.member_id = member_id;
	}





	public RComment() {
		
		
	}
	
	
	



	
	
	
	public int getReview_comment_no() {
		return review_comment_no;
	}








	public void setReview_comment_no(int review_comment_no) {
		this.review_comment_no = review_comment_no;
	}








	public int getReview_no() {
		return review_no;
	}








	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}








	public String getDetails_comment() {
		return details_comment;
	}








	public void setDetails_comment(String details_comment) {
		this.details_comment = details_comment;
	}








	public String getMember_id() {
		return member_id;
	}








	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}








	public Date getComment_date() {
		return comment_date;
	}








	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}








	@Override
	public String toString() {
		return "ReviewComment [review_comment_no=" + review_comment_no + ", review_no=" + review_no + ", details_comment=" + details_comment + ", member_id="
				+ member_id + ", comment_date=" + comment_date + "]";
	}


	public void validate(Map<String, Boolean> errors) {
			if(  member_id==null ||  member_id.trim().isEmpty() ) {
				errors.put("title", Boolean.TRUE);
			}
			if(  details_comment==null ||  details_comment.isEmpty() ) {
				errors.put("content", Boolean.TRUE);
			}
		}
	}


		
	
	
	




