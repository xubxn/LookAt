package mypage.service;

import java.util.List;

import question.model.Question;

public class MyPage {
	
	private int total;
	private int currentPage;
	private List content;
	private int totalPages;
	private int startPage;						
	private int endPage;
	
	public MyPage(int total, int currentPage ,int size, List content) {
		this.total=total;
		this.currentPage=currentPage;
		this.content=content;
		if(total==0) {
			totalPages=0;
			startPage=0;
			endPage=0;
		}else{ totalPages=total/size;
		if(total%size>0) {
			totalPages++;
			}
		int modVal=currentPage%1;
		startPage=currentPage/1*1+1;
		if(modVal==0)startPage-=1;
		
		endPage=startPage;
		if(endPage>totalPages)endPage=totalPages;
		}
		}



	public int getCurrentPage() {
		return currentPage;
	}

	public List getContent() {
		return content;
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


	public boolean hasNoWants() {
		return total==0;
	}

	public int getTotal() {
		return total;
	}
}
