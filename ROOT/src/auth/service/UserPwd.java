package auth.service;

public class UserPwd {
  private String member_name;
  private String member_tel;
  private boolean isMatch;

  public UserPwd(String name, String tel) {
    this.member_name = name;
    this.member_tel = tel;
  }

  public UserPwd(boolean isMatch) {
	  this.isMatch= isMatch;
}

public UserPwd(boolean isMatch, String member_tel, String member_name) {
	this.isMatch = isMatch;
	this.member_tel =member_tel;
	this.member_name =member_name;
	
}



public String getName() {
    return member_name;
  }

  public String getTel() {
    return member_tel;
  }

  @Override
  public String toString() {
    return "UserPwd [name=" + member_name + ", tel=" + member_tel + "]";
  }
}
