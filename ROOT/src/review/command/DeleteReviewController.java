package review.command;
import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import review.service.reviewService;
import mvc.command.CommandHandler;


//요청주소  /board/delete.do?no=글번호
//삭제요청 담당 컨트롤이다
public class DeleteReviewController implements CommandHandler {

	
	private reviewService reviewService = new reviewService();
	private static String imageRepository = "C:\\board\\image_repository"; //업로드된 이미지파일이 저장이 되는 실제위치
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		int no = Integer.parseInt(request.getParameter("no"));//삭제할 글번호
		
		//2.비즈니스로직<->Service<->DAO<->DB
		//파라미터      no - 삭제할 글 번호
		//리턴 int 삭제성공시 삭제된 글 번호리턴, 실패시 0
		int reviewno = reviewService.deleteReview(no);
		
		//실제 업로드된 파일제거
		File imageDir = new File(imageRepository+"\\"+reviewno);
		if(imageDir.exists()) { //삭제할 폴더 존재여부 확인
			FileUtils.deleteDirectory(imageDir);//폴더삭제
		}
		
		//3.Model
		
		//4.View
		//삭제성공시 js의 alert()이용하여 삭제성공메세지를 출력 -> 목록보기
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter(); //브라우저에 출력
		String msg = "<script>alert('삭제 success!!');location.href="
				     +request.getContextPath()+"'/reviewList.do';</script>";		
		writer.print(msg);
		
		return null;
	}

	
}
