package admin.service;

import java.util.Map;




//글등록폼에서 입력한 제목,내용/회원정보(여기에서는 session에  담긴 회원id, 회원name)
//필수입력 기능 제공
public class MemberRequest {
	//필드
	private String title; //제목
	private String content; //내용
	
	//생성자
	public MemberRequest( String title, String content) {
		this.title = title;
		this.content = content;
	}


	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

		public void validate(Map<String,Boolean> errors) {
		if(  title==null ||  title.trim().isEmpty() ) {
			errors.put("title", Boolean.TRUE);
		}
		if(  content==null ||  content.isEmpty() ) {
			errors.put("content", Boolean.TRUE);
		}
	}
	
}







