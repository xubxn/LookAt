package mypage.model;

public class Want  {
	private int want_no;
	private int exhibition_no;
	private String member_id;
	private String thumbnail;
	
	
	public Want(int want_no, int exhibition_no, String member_id, String thumbnail) {
		this.want_no = want_no;
		this.exhibition_no = exhibition_no;
		this.member_id = member_id;
		this.thumbnail = thumbnail;
	}


	public int getWant_no() {
		return want_no;
	}


	public void setWant_no(int want_no) {
		this.want_no = want_no;
	}


	public int getExhibition_no() {
		return exhibition_no;
	}


	public void setExhibition_no(int exhibition_no) {
		this.exhibition_no = exhibition_no;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}
