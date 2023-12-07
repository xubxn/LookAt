package review.command;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import auth.service.User;
import mvc.command.CommandHandler;
import review.model.Review;
import review.service.writeReviewService;

public class reviewHandler implements CommandHandler {


	
		private static final String FORM_VIEW ="/view/reviewWrite/reviewWrite.jsp";
		private writeReviewService reviewService = new writeReviewService();
		private static String imageRepository = "C:\\board\\image_repository";
		
		
		//담당컨트롤러의 요청메서드
		//리턴유형 String:  client에게 보여주는 jsp문서의 경로와 파일명
		@Override
		public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("reviewHandler의 process()진입");
			
			
			if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
				return processForm(request,response);
			}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
				return processSubmit(request,response); //p607 25라인
			}else {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
				return null;
			}
			
		}//process

		//글등록폼을 보여주기
		private String processForm(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("AUTH_USER");
			session.setAttribute("AUTH_USER", user);
			return request.getContextPath()+FORM_VIEW;
		}
		
		//글등록처리
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//1.파라미터받기
			
			Map<String,String> reviewMap = upload(request,response);
			//Map참조변수.get(키명)=>Map에서 특정key의 값을 가져오기
			String imageFileName = reviewMap.get("review_img"); //스크린샷2023-05-04.png
			
			//2.비즈니스로직<->Service<->DAO<->DB
			
			//(Integer review_no, String member_id, String review_img, Date review_date, int review_score,
			//String review_title, String details_review, int exhibition_no, String thumbnail)
			Review review = new Review(null, 
					reviewMap.get("member_id"), 
					reviewMap.get("review_img"), 
					null,
					Integer.parseInt(reviewMap.get("review_score")),
					reviewMap.get("review_title"),
					reviewMap.get("details_review"),
					Integer.parseInt(reviewMap.get("exhibiton_no")),
					null);
					/*reviewMap.get("thumbnail"));*/
			System.out.println("Review review ="+review);
			//파라미터 review: 세션의 회원id,글제목,내용,첨부파일명
			//리턴     reviewno : 방금전 insert된 글번호
			int reviewno= reviewService.insert(review);
			
			
			if(imageFileName!=null && imageFileName.length()!=0) {//첨부파일이 있는 경우에만
				File srcFile = new File(imageRepository+"\\"+"temp"+"\\"+imageFileName);
				File destDir = new File(imageRepository+"\\"+reviewno);
				destDir.mkdirs(); //(물리적인)폴더, 파일생성
				System.out.println("폴더생성확인해보삼~");
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			
			
			
			//입력성공시 js를 이용하여 입력성공메세지를 alert창에 띄운다->user가 확인버튼클릭시->목록페이지로 이동
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter(); //브라우저에 출력
			String msg = "<script>alert('환영합니다!!');  location.href= "
					     +request.getContextPath()+"'reviewList.do';</script>";		
			writer.print(msg);
			
			return null;
		}

		//글등록(,수정시) 데이터를 가져오기
		//리턴 Map<String,String> : 작성자id,제목,내용,이미지파일명
		private Map<String,String> upload(HttpServletRequest request, HttpServletResponse response) {
			
			//작성자id,제목,내용,이미지파일명을 저장하기위한 변수 선언
			Map<String,String> reviewMap = new HashMap<String,String>();
					
			//디스크 기반 파일 항목에 대한 팩토리 생성
			/*DiskFileItemFactory클래스는 FileItem객체를 생성하는 클래스
			   항목이 일반 양식 필드인지(즉, 일반 텍스트 상자나 유사한 HTML 필드에서 가져온 데이터인지) 
			   또는 업로드된 파일인지에 따라 항목을 다르게 처리해야 할 수도 있습니다. 
			 *인터페이스 FileItem는 그러한 결정을 내리고 가장 적절한 방식으로 데이터에 액세스하는 방법을 제공
			 *ServletFileUpload클래스는 파일데이터를 취득하는 클래스로,
			 *   					    FileItem객체가 저장된 List를 반환한다*/
			DiskFileItemFactory factory = new DiskFileItemFactory();
			File dirPath = new File(imageRepository);
			factory.setRepository(dirPath); //업로드된 파일저장소를 설정
			factory.setSizeThreshold(1024*1024);//업로드파일최대사이즈 설정.byte단위.-1이면 무한
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				List items = upload.parseRequest(request);
				// 업로드된 항목 처리
				/*일반양식필드인 경우와 파일업로드인 경우를  분리하여 작업*/
				/*fileItem.isFormField()는 일반양식필드인 경우에는 true리턴*/
				for (int i=0;i<items.size();i++) { 
					FileItem fileItem=(FileItem)items.get(i);
				    if (fileItem.isFormField()) { //일반 양식이니? text필드,textarea등
				    //<input type="text" name="title">
				    //<textarea name="content">	
				    	System.out.println("fileItem.getFieldName()="+fileItem.getFieldName());
				    	System.out.println("fileItem.getString()="+fileItem.getString("utf-8"));
				    //boardMap.put("writerid", 로그인한유저id)	
				    //boardMap.put("title",  "user가입력한제목")	
				    //boardMap.put("content","user가입력한내용")	
				    	reviewMap.put(fileItem.getFieldName(),fileItem.getString("utf-8"));
				    	
				    } else { //input type="file"양식이니? //<input type="file" name="imageFileName" />
				    	System.out.println("fileItem.getFieldName()="+fileItem.getFieldName());//imageFileName
				    	//fileItem.getName()=스크린샷2023-05-04.png
				    	System.out.println("fileItem.getName()="+fileItem.getName());//user가 업로드한 파일명
				    	System.out.println("fileItem.getSize()="+fileItem.getSize());//이미지파일의 크기
				    	
				    	if(fileItem.getSize()>0) {
				    		int idx = fileItem.getName().lastIndexOf("\\"); //추출시작인덱스;
				    		if(idx ==  -1) { //  "\\"가 존재하지않으면
				    			idx = fileItem.getName().lastIndexOf("/");
				    		}
				    		
				    		//   \\ 또는  / 뒤의 문자부터 끝까지 추출		
					    	String fileName = fileItem.getName().substring(idx+1);
					    	System.out.println("fileName="+fileName);
					      
					      //boardMap.put("imageFileName","업로드파일명")
					    	reviewMap.put(fileItem.getFieldName(), fileName);
					    	System.out.println("boardMap.get(review_img)="+reviewMap.get("review_img"));
					    	
					    	File uploadFile = new File(imageRepository+"\\temp\\"+fileName);
					    	fileItem.write(uploadFile);
					    	System.out.println("temp거쳐갔음");
				    	}//if
				    }//if 
				    System.out.println("boardMap.get(필드명)="+reviewMap.get(fileItem.getFieldName())); 
				    
				}//for
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			return reviewMap;
		}//upload()끝

		
		
		
	}//class









