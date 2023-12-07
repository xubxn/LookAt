package admin.model;



public class Exhibition {

	private Integer exhibition_no;	//Exhibition_no 글번호.PK
	private String  title;  //title 제목
	
	
	public Exhibition(Integer exhibition_no, String title) {
		this.exhibition_no = exhibition_no;
		this.title = title;
	}


	public Integer getExhibition_no() {
		return exhibition_no;
	}

	
	public String getTitle() {
		return title;
	}





}



