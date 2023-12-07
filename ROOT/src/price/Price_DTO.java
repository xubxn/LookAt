package price;

public class Price_DTO {
	private int price_no;
	private int exhibition_no;
	private int price_adult;
	private int price_student;
	private int price_baby;
	
	public Price_DTO() {
		
	}
	
	public Price_DTO(int price_no, int exhibition_no, int price_adult, int price_student, int price_baby) {
		this.price_no = price_no;
		this.exhibition_no = exhibition_no;
		this.price_adult = price_adult;
		this.price_student = price_student;
		this.price_baby = price_baby;
	}
	public int getPrice_no() {
		return price_no;
	}
	public int getExhibition_no() {
		return exhibition_no;
	}
	public int getPrice_adult() {
		return price_adult;
	}
	public int getPrice_student() {
		return price_student;
	}
	public int getPrice_baby() {
		return price_baby;
	}
	public void setPrice_no(int price_no) {
		this.price_no = price_no;
	}
	public void setExhibition_no(int exhibition_no) {
		this.exhibition_no = exhibition_no;
	}
	public void setPrice_adult(int price_adult) {
		this.price_adult = price_adult;
	}
	public void setPrice_student(int price_student) {
		this.price_student = price_student;
	}
	public void setPrice_baby(int price_baby) {
		this.price_baby = price_baby;
	}
}
