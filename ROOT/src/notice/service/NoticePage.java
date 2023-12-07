package notice.service;

import java.util.List;
import notice.model.Notice;

public class NoticePage {

	private int total;									//총 게시글 수
	private int currentPage;					//보고싶은 페이지 -> 현재 페이지
	private List<Notice> content;		//Notice 목록
	private int totalPages;						//총 페이지수	
	private int startPage;						//시작 페이지	
	private int endPage;							//끝 페이지


	public NoticePage(int total, int currentPage, int size, List<Notice> content) {
		this.total=total;										//총 게시글수
		this.currentPage = currentPage;		//보고싶은 페이지 -> 현재 페이지
		this.content = content;						//article 목록
		
		if(total==0) {						//게시글이 존재하지 않는 경우
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {									//게시글이 존재하는 경우
			totalPages=total/size;
			if(total%size>0) {
				totalPages++;
			}
		}
		
		int modVal = currentPage%5;
		startPage = currentPage/5*5+1;
		if(modVal==0) {
			startPage -= 5;
		}
		endPage = startPage + 4;
		if(endPage > totalPages) {
			endPage=totalPages;
		}
	}


	//총 게시글수가 0이면, 없으면 true 리턴 있으면 false 리턴
	public boolean hasNoNotices() {
		return total==0;
	}

	//총 게시글 수
	public int getTotal() {
		return total;
	}
	
	public List<Notice> getContent() {
		return content;
	}

	//보고 싶은 페이지 -> 현재 페이지
	public int getCurrentPage() {
		return currentPage;
	}

	//총 페이지수
	public int getTotalPages() {
		return totalPages;
	}

	//시작 페이지
	public int getStartPage() {
		return startPage;
	}

	//끝 페이지
	public int getEndPage() {
		return endPage;
	}

}
