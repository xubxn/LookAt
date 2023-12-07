package question.model;

import java.util.Date;

public class Question {

	private Integer QA_no; //글번호
	private int Q_no;
	private String member_id;
	private String Q_plus_file;
	private String Q_details;
	private String Q_title;
	private Date Q_date;
	
	public Question() {}
	
	public Question(Integer QA_no, int Q_no, String member_id, String Q_plus_file, String Q_details,
			Date Q_date, String Q_title) {
		this.QA_no=QA_no;
		this.Q_no = Q_no;
		this.member_id = member_id;
		this.Q_plus_file = Q_plus_file;
		this.Q_details = Q_details;
		this.Q_date = Q_date;
		this.Q_title = Q_title;
	}
	
	public Question( int Q_no, String Q_plus_file, String Q_details, String Q_title,Integer QA_no) {
		this.Q_no = Q_no;
		this.Q_plus_file = Q_plus_file;
		this.Q_details = Q_details;
		this.Q_title = Q_title;
		this.QA_no= QA_no;
	}
	public Question( int Q_no, String member_id, String Q_plus_file, String Q_details, String Q_title) {
		this.Q_no = Q_no;
		this.member_id = member_id;
		this.Q_plus_file = Q_plus_file;
		this.Q_details = Q_details;
		this.Q_title = Q_title;
	}
	public Question( int Q_no, String member_id,String Q_plus_file, String Q_title, String Q_details,
			Date Q_date) {
		this.Q_no = Q_no;
		this.member_id = member_id;
		this.Q_plus_file = Q_plus_file;
		this.Q_details = Q_details;
		this.Q_date = Q_date;
		this.Q_title = Q_title;
	}
	public Question( int Q_no, String member_id, String Q_plus_file, String Q_details, String Q_title,Integer QA_no
			) {
		this.Q_no = Q_no;
		this.member_id = member_id;
		this.Q_plus_file = Q_plus_file;
		this.Q_details = Q_details;
		this.Q_title = Q_title;
		this.QA_no = QA_no;
	}

	public Question(Integer QA_no, String Q_title, String member_id,Date Q_date) {
		this.QA_no=QA_no;
		this.member_id = member_id;
		this.Q_title = Q_title;	
		this.Q_date = Q_date;
		
		}
	

	public Question(int Q_no, String member_id, String Q_plus_file, String Q_details, Date Q_date, String Q_title) {
		this.Q_no = Q_no;
		this.member_id = member_id;
		this.Q_plus_file = Q_plus_file;
		this.Q_details = Q_details;
		this.Q_date = Q_date;
		this.Q_title = Q_title;
	}



	public int getQA_no() {
		return QA_no;
	}
	public void setQA_no(int QA_no) {
		this.QA_no=QA_no;
	}
	public int getQ_no() {
		return Q_no;
	}
	public void setQ_no(int Q_no) {
		this.Q_no = Q_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getQ_plus_file() {
		return Q_plus_file;
	}
	public void setQ_plus_file(String Q_plus_file) {
		this.Q_plus_file = Q_plus_file;
	}
	public String getQ_details() {
		return Q_details;
	}
	public void setQ_details(String Q_details) {
		this.Q_details = Q_details;
	}
	public Date getQ_date() {
		return Q_date;
	}
	public void setQ_date(Date Q_date) {
		this.Q_date = Q_date;
	}
	public String getQ_title() {
		return Q_title;
	}
	public void setQ_title(String Q_title) {
		this.Q_title = Q_title;
	}

	public void setQA_no(Integer qA_no) {
		QA_no = qA_no;
	}

	
}
