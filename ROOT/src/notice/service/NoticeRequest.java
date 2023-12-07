package notice.service;

import java.util.Map;

public class NoticeRequest {
	
	private int notice_no;
	private String admin_id;
	private String n_title;
	private String n_details;
	
	public NoticeRequest(int notice_no, String admin_id, String n_title, String n_details) {
		this.notice_no = notice_no;
		this.admin_id = admin_id;
		this.n_title = n_title;
		this.n_details = n_details;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public String getN_title() {
		return n_title;
	}

	public String getN_details() {
		return n_details;
	}

	public void validate(Map<String, Boolean> errors) {
		if(n_title == null || n_title.trim().isEmpty()) {
			errors.put("n_title", Boolean.TRUE);
		}
	}
	
}
