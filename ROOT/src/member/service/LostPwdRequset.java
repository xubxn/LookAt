package member.service;

import java.util.Map;

public class LostPwdRequset {
	private String member_name;
	private String member_tel;
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_tel() {
		return member_tel;
	}
	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}
	
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors,member_name,"member_name");
		checkEmpty(errors,member_tel,"member_tel");
	
}
	private void checkEmpty(Map<String, Boolean> errors, String value, String fiedlName) {
		if(value == null || value.isEmpty())
			errors.put(fiedlName, Boolean.TRUE);
	}
}