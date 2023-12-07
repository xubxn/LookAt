package question.service;

import java.util.Date;
import java.util.Map;

public class QwriteRequest {

	private int Q_no; 
	private String member_id;
	private String Q_plus_file;
	private String Q_details;
	private Date Q_date;
	private String Q_title;
	
	
	public QwriteRequest(int Q_no,String member_id,String Q_title,
							String Q_details,String Q_plus_file,Date Q_date) {
		
		this.Q_no=Q_no;
		this.member_id = member_id;
		this.Q_plus_file = Q_plus_file;
		this.Q_details = Q_details;
		this.Q_date = Q_date;
		this.Q_title = Q_title;
		
	}

	public QwriteRequest(int Q_no, String Q_plus_file, String Q_details, Date Q_date, String Q_title) {
		this.Q_no=Q_no;
		this.Q_plus_file = Q_plus_file;
		this.Q_details = Q_details;
		this.Q_date = Q_date;
		this.Q_title = Q_title;
	}

	

	public int getQ_no() {
		return Q_no;
	}

	public String getMember_id() {
		return member_id;
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

	public Date getQ_date() {
		return Q_date;
	}
	
	public void validate(Map<String,Boolean> errors) {
		if(Q_title==null||Q_title.trim().isEmpty()) {
			errors.put("Q_title",Boolean.TRUE);}
		if(Q_details==null||Q_details.trim().isEmpty()){
			errors.put("Q_details",Boolean.TRUE);
		}
		}
	}
	
