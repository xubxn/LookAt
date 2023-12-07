package question.service;

import java.util.Map;

public class ModifyRequest {

	private String member_id;
	private int QA_no;
	private int Q_no;
	private String Q_plus_file;
	private String Q_details;
	private String Q_title;

	public ModifyRequest(String member_id, int QA_no, String Q_details, String Q_plus_file, String Q_title, int Q_no) {
		this.QA_no = QA_no;
		this.Q_no = Q_no;
		this.member_id = member_id;
		this.Q_plus_file = Q_plus_file;
		this.Q_details = Q_details;
		this.Q_title = Q_title;
	}
	public ModifyRequest(int Q_no, String Q_plus_file, String Q_details, String Q_title,int QA_no) {
		this.Q_no = Q_no;
		this.Q_plus_file = Q_plus_file;
		this.Q_details = Q_details;
		this.Q_title = Q_title;
		this.QA_no = QA_no;
	}
	

	public String getMember_id() {
		return member_id;
	}

	public int getQA_no() {
		return QA_no;
	}

	public int getQ_no() {
		return Q_no;
	}

	public String getQ_title() {
		return Q_title;
	}

	public String getQ_details() {
		return Q_details;
	}

	public String getQ_plus_file() {
		return Q_plus_file;
	}

public void validate(Map<String,Boolean>errors) {
	if(Q_title==null||Q_title.trim().isEmpty()) {
		errors.put("Q_title",Boolean.TRUE);
if(Q_details==null||Q_details.trim().isEmpty()) {	
			errors.put("Q_details",Boolean.TRUE);
}
}	
}
}


