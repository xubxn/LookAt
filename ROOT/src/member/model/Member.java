package member.model;

import java.util.Map;

public class Member {
	//필드
	private String member_id;
	private String member_pw;
	private String confirmPassword;
	private String member_date;
	private String member_tel;
	private String member_email;
	private String member_name;
	private String quit_Y;
	
	public Member() {}
	
	//생성자
	public Member(String member_id, String member_pw, String member_date, String member_tel,  String member_email, String member_name, String quit_Y) {
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_date = member_date;
		this.member_tel = member_tel;
		this.member_email = member_email;
		this.member_name = member_name;
		this.quit_Y=quit_Y;
		
	}
	
	public Member(String member_id, String member_pw,  String confirmPassword, String member_tel, String member_email, String member_name) {
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.confirmPassword = confirmPassword;
		this.member_tel = member_tel;
		this.member_email = member_email;
		this.member_name = member_name;
	}
	

	public Member(String member_id, String member_pw, String member_tel, String member_email, String member_name) {
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_tel = member_tel;
		this.member_email = member_email;
		this.member_name = member_name;
	}

	//메서드
	public String getMember_id() {
		return member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public String getMember_date() {
		return member_date;
	}

	public  String getMember_tel() {
		return member_tel;
	}


	public String getMember_email() {
		return member_email;
	}

	public String getMember_name() {
		return member_name;
	}
	
	
	public String getQuit_Y() {
		return quit_Y;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public void setMember_date(String member_date) {
		this.member_date = member_date;
	}

	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}


	//암호 변경 기능을 구현할 때 사용
	public boolean matchPassword(String pwd) {
		return member_pw.equals(pwd);
	}
	
	public boolean isPasswordEqualToConfirm() {
		return member_pw != null && member_pw.equals(confirmPassword);
	}
	
	//새 암호 변경 기능을 구현할 때 사용
	public void changePassword(String setrepw) {
		System.out.println("changePassword");
		this.member_pw=setrepw;
	}
	
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value==null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
	
	//항목별 에러 체크
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, member_pw, "password");	
		checkEmpty(errors, confirmPassword, "confirmPassword");
		checkEmpty(errors, member_tel, "tel");
		checkEmpty(errors, member_email, "email");

		if(!errors.containsKey("confirmPassword")) {
			if(!isPasswordEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE);		
			}
		}
	}

	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", member_pw=" + member_pw + ", confirmPassword=" + confirmPassword
				+ ", member_tel=" + member_tel + ", member_email=" + member_email + ", member_name=" + member_name
				+ "]";
	}
	
}
