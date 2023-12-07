package admin.service;

import java.util.List;




import admin.model.Exhibition;

//총게시글수,article목록,페이징처리정보
public class ExhibitionPage {
	private int total;				//총게시글수
	private int currentPage;		//보고싶은 페이지=>현재 페이지
	private List<Exhibition> content; 
	private int  totalPages;		//총페이지수   15
	private int  startPage;			//시작페이지   12345  678910   11~15
	private int  endPage;			//끝페이지
	
	
	ExhibitionPage(int total, int currentPage, int size, List<Exhibition> content){
		this.total=total;		//총게시글수
		this.currentPage=currentPage; //보고싶은 페이지=>현재 페이지
		this.content=content;	
		if(total==0) { //게시글이 존재하지 않는 경우
			totalPages=0;
			startPage=0;
			endPage=0;
		}else { //게시글이 존재하는 경우
		
			totalPages=total/size;	
			if(total%size>0) { 
				totalPages++; 
			}
	        
			int modVal = currentPage%5;	//현재페이지를 5로 나눈 나머지=> 5의 배수 5,10,15~
			//시작페이지                                                
			startPage=currentPage/5*5+1;
			if(modVal==0)  startPage=startPage-5;
/*			1  2  3  4  5            
			6  7  8  9  10           
			11 12 13 14 15
			보고싶은페이지(currentPage)가 1이면   1/5*5+1; =>시작페이지는 1
			보고싶은페이지(currentPage)가 2이면   2/5*5+1; =>시작페이지는 1
			보고싶은페이지(currentPage)가 3이면   3/5*5+1; =>시작페이지는 1
			보고싶은페이지(currentPage)가 4이면   4/5*5+1; =>시작페이지는 1
			보고싶은페이지(currentPage)가 5이면   5/5*5+1; =>6이면 강제로 시작페이지는 1
	
			보고싶은페이지(currentPage)가 6이면   6/5*5+1; =>시작페이지는 6
			보고싶은페이지(currentPage)가 9이면   9/5*5+1; =>시작페이지는 6
			보고싶은페이지(currentPage)가 10이면  10/5*5+1;=>11 강제로  시작페이지는 6*/
			                                                  
			endPage=startPage+4;	
			if(endPage>totalPages) endPage=totalPages;
			/*시작페이지가 1이면  끝페이지 5
			 *시작페이지가 6이면  끝페이지 10 
			 *시작페이지가 11이면 끝페이지 15 */
		}//게시글이 존재하는 경우의 끝
	}

	public int getTotal() {
		return total; //총게시글수
	}
	
	public boolean hasNoExhibitions() {
		return total==0;
	}

	public List<Exhibition> getContent() {
		return content; 
	}

	//보고싶은 페이지=>현재 페이지	
	public int getCurrentPage() {
		return currentPage; 
	}

	//총페이지수
	public int getTotalPages() {
		return totalPages;
	}
	
	//시작페이지
	public int getStartPage() {
		return startPage; 
	}

	//끝페이지
	public int getEndPage() {
		return endPage;  
	}
	
	

	@Override
	public String toString() {
		return "ExhibitionPage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPages="
				+ totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}









