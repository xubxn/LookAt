package qcomment.model;

import java.util.Date;

public class QComment {
	private int A_reply_no;
	private int QA_no;
	private String A_details;
	private Date A_date;

	public int getA_reply_no() {
		return A_reply_no;
	}
	public void setA_reply_no(int a_reply_no) {
		A_reply_no = a_reply_no;
	}
	public int getQA_no() {
		return QA_no;
	}
	public void setQA_no(int QA_no) {
		this.QA_no = QA_no;
	}
	public String getA_details() {
		return A_details;
	}
	public void setA_details(String a_details) {
		A_details = a_details;
	}
	public Date getA_date() {
		return A_date;
	}
	public void setA_date(Date a_date) {
		A_date = a_date;
	}
	public QComment(int A_reply_no, int QA_no, String A_details, Date A_date) {
		this.A_reply_no = A_reply_no;
		this.QA_no = QA_no;
		this.A_details = A_details;
		this.A_date = A_date;
	}
	
	public QComment(int QA_no, String A_details) {
		this.QA_no = QA_no;
		this.A_details = A_details;
	}
	public QComment() {
	}

	public QComment(int a_reply_no, int QA_no, String a_details) {
		A_reply_no = a_reply_no;
		this.QA_no = QA_no;
		A_details = a_details;
	}
	@Override
	public String toString() {
		return "QComment [A_reply_no=" + A_reply_no + ", QA_no=" + QA_no + ", A_details=" + A_details + ", A_date="
				+ A_date + "]";
	}

	
}
