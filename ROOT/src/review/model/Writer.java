package review.model;

public class Writer {
	//필드
		private String  id;   //writer_id	작성자id
		private String  name; //writer_name 작성자명

		//생성자
		public Writer(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}

		
		@Override
		public String toString() {
			return "Writer [id=" + id + "]";
		}
		
}

