package member.service;

import java.util.Map;

//회원가입 기능을 구현할 때 필요한 요청데이터를 담는 클래스
public class JoinRequset {
	
	private String member_id;
	private String member_name;
	private String member_pw;
	private String confirmPw;
	private String member_date;
	private String member_tel;
	private String member_email;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getConfirmPw() {
		return confirmPw;
	}
	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}
	public String getMember_date() {
		return member_date;
	}
	public void setMember_date(String member_date) {
		this.member_date = member_date;
	}
	public String getMember_tel() {
		return member_tel;
	}
	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	
	public boolean isPasswordEqualToConfirm() {
		return member_pw != null && member_pw.equals(confirmPw);
	}
	
	//값이 올바른지 검사하는 기능. 올바르지 않으면 errors맵에 추가.
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors,member_id,"member_id");
		checkEmpty(errors,member_name,"member_name");
		checkEmpty(errors,member_pw,"member_pw");
		checkEmpty(errors,confirmPw,"confirmPw");
		checkEmpty(errors,member_date,"member_date");
		checkEmpty(errors,member_tel,"member_tel");
		checkEmpty(errors,member_email,"member_email");
		
		if(!errors.containsKey("confirmPassword")) {
			if(!isPasswordEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}
	private void checkEmpty(Map<String, Boolean> errors, String value, String fiedlName) {
		if(value == null || value.isEmpty())
			errors.put(fiedlName, Boolean.TRUE);
	}
	
	@Override
	public String toString() {
		return "JoinRequset [member_id=" + member_id + ", member_name=" + member_name + ", member_pw=" + member_pw
				+ ", confirmPw=" + confirmPw + ", member_date=" + member_date + ", member_tel=" + member_tel
				+ ", member_email=" + member_email + "]";
	}
	
}
