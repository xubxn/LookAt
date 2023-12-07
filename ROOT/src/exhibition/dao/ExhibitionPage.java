package exhibition.dao;

import java.util.List;

import exhibition.model.Exhibition;

//p648
//총게시글수, 
public class ExhibitionPage {
	//필드
	private int total;
	private int currentPage; //pageNum => currentPage / 보고싶은 페이지 => 현재 페이지
	private List<Exhibition> content;	//article  목록
	private int totalPages;	//총페이지수		15
	private int startPage;		//시작페이지		12345	678910	1112131415
	private int endPage;		//끝페이지		5			10			15

	
	//생성자
	
	
	//ListArticleService에서 총게시글 수 + 목록조회 (페이징처리)등의 정보
	//int total //총게시글수
	//List<Article> content //article 목록 
	public ExhibitionPage (int total, int currentPage, int size, List<Exhibition> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if (total ==0) { //게시글이 존재하지 않는 경우
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		}  else { //게시글이 존재하는 경우
		
				this.totalPages = total/size;	//총페이지수 - p649 25라인 	11/3 => 총페이지수 4페이지
															    																//1page에는 123
																																//2page에는 456 
																																//3page에는 789
																																//4page에는 10  11
				if (total%size > 0) {		//5%3
					totalPages++;
				}
				
				int modVal = currentPage%5; //현재 페이지를 5로 나눈 나머지 => 5의 배수 5,10,15~
				this.startPage = currentPage/5*5+1;
				if (modVal == 0) startPage = startPage-5;
				
				endPage = startPage + 4;
				if (endPage > totalPages) endPage = totalPages;  
				if (endPage > 11) endPage = 11;  
				/* 시작페이지가 1이면 끝페이지 5
				 * 시작페이지가 6이면 끝페이지 10
				 * 시작페이지가 11이면 끝페이지 15 */
				}
		}
	//메소드
	//p650 38라인
	public int getTotal() {
		return total;
	}

	//p650 42라인 - 총 게시글(수가 0이면)없으면 true리턴, 그렇지 않으면 false리턴
	public boolean hasNoArticles() {
		return total == 0;
	}

	//p650 58라인
	public List<Exhibition> getContent() {
		return content;
	}
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	
	@Override
	public String toString() {
		return "ExhibitionPage [total=" + total + ", content=" + content + "]";
	}
	
}
