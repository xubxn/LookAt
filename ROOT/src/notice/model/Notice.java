package notice.model;

import java.util.Date;

//공지 게시판 DTO
public class Notice {
	
	private int notice_no;
	private String admin_id;
	private String n_title;
	private String n_details;
	private Date n_date;
	
	public Notice() {};
	
	public Notice(int notice_no, String admin_id, String n_title, String n_details, Date n_date) {
		this.notice_no = notice_no;
		this.admin_id = admin_id;
		this.n_title = n_title;
		this.n_details = n_details;
		this.n_date = n_date;
	}

	public Notice(String admin_id, String n_title, String n_details, Date n_date) {
		this.admin_id = admin_id;
		this.n_title = n_title;
		this.n_details = n_details;
		this.n_date = n_date;
	}
	
	public int getNotice_no() {
		return notice_no;
	}
	
	public String getAdmin_id() {
		return admin_id;
	}
	

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_details() {
		return n_details;
	}

	public void setN_details(String n_details) {
		this.n_details = n_details;
	}

	public Date getN_date() {
		return n_date;
	}

	public void setN_date(Date n_date) {
		this.n_date = n_date;
	}

	
	
	@Override
	public String toString() {
		return "Notice [notice_no=" + notice_no + ", admin_id=" + admin_id + ", n_title=" + n_title + ", n_details="
				+ n_details + ", n_date=" + n_date + "]";
	}
	
	
	
	
}
