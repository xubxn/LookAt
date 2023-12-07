package exhibition.command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import auth.service.User;
import exhibition.model.Exhibition;
import exhibition.service.WriteExhibitionService;
import mvc.command.CommandHandler;

public class WriteExhibitionHandler implements CommandHandler {

	// write service 객체 생성
	private static final String FORM_VIEW = "/view/exhibition/writeExhibition.jsp";
	private WriteExhibitionService writeExhibitionService = new WriteExhibitionService();
	private static String imageRepository = "C:\\exhibition\\image_repository";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteExhibitionService의 process()진입");
		request.setCharacterEncoding("UTF-8");
		if (request.getMethod().equalsIgnoreCase("get")) { // 요청방식이 get 방식이면 FORM_VIEW 보여주기
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("post")) { // 요청방식이 post 방식이면 글 등록 처리
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 405오류
			return null;
		}

	}

	// 글등록 폼을 보여주기 - 641 31라인
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

		User user = (User) request.getSession(false).getAttribute("AUTH_USER");
		
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.do");
		}
		else if (user.getId().equals("admin")) { 
			  return FORM_VIEW; 
		} 
		
		return null;
	}

	// 글등록 처리 p641 35라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 에러 정보가 담기는 Map - p641 36라인
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);

		// 1.파라미터 받기
		User user = (User) request.getSession(false).getAttribute("AUTH_USER");
		HttpSession session = request.getSession();


		// 파일 기능
		Map<String,String> exhibitionMap = upload(request,response);
		//Map참조변수.get(키명)=>Map에서 특정key의 값을 가져오기
		String thumbnail = exhibitionMap.get("thumbnail");
		String details_img = exhibitionMap.get("details_img");
		
		UUID uuid = UUID.randomUUID();
		
		Exhibition writeRequest = createWriteUploadRequest(exhibitionMap,uuid);
		writeRequest.validate(errors); // 필수입력검사

		if (!errors.isEmpty()) { // 에러가 있으면
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}

		// 2.비즈니스 로직 <-> Service <-> DAO <-> DB
		int exhibition_no = writeExhibitionService.insert(writeRequest);

		saveFile(request, response, thumbnail, uuid);
		saveFile(request, response, details_img, uuid);
		
		
		// 4.View - 성공시: view/newArticleSeuccess.jsp이동
		// 실패시 FORM_VIEW로 이동
		PrintWriter writer = response.getWriter(); //브라우저에 출력
		String msg = "<script>alert('inserted success!!');location.href="
				     +request.getContextPath()+"'/exhibitionList.do';</script>";		
		writer.print(msg);
		return null;
	}
	
	// 작성자 정보
	private Exhibition createWriteRequest(HttpServletRequest request) throws SQLException {
		Exhibition exhibition = new Exhibition();
		exhibition.setTitle(request.getParameter("title"));
		exhibition.setOpen_date(Date.valueOf(request.getParameter("open_date")));
		exhibition.setEnd_date(Date.valueOf(request.getParameter("end_date")));
		exhibition.setPlace( request.getParameter("place"));
		exhibition.setThumbnail(request.getParameter("thumbnail"));
		exhibition.setDetails_img(request.getParameter("details_img"));
		exhibition.setIntroduction(request.getParameter("introduction"));
		exhibition.setPrice_adult( Integer.parseInt(request.getParameter("price_adult")));
		exhibition.setPrice_student(Integer.parseInt(request.getParameter("price_student")));
		exhibition.setPrice_baby(Integer.parseInt(request.getParameter("price_baby")));
		exhibition.setLoc(request.getParameter("loc"));
		exhibition.setDetails_place(request.getParameter("details_place"));
		return exhibition;
	}
	
	private Exhibition createWriteUploadRequest (Map<String,String> exhibitionMap, UUID uuid) {
		Exhibition exhibition = new Exhibition();
		exhibition.setTitle(exhibitionMap.get("title"));
		exhibition.setOpen_date(Date.valueOf(exhibitionMap.get("open_date")));
		exhibition.setEnd_date(Date.valueOf(exhibitionMap.get("end_date")));
		exhibition.setPlace( exhibitionMap.get("place"));
		exhibition.setThumbnail(uuid + "/" + exhibitionMap.get("thumbnail"));
		exhibition.setDetails_img(uuid + "/" + exhibitionMap.get("details_img"));
		exhibition.setIntroduction(exhibitionMap.get("introduction"));
		exhibition.setPrice_adult( Integer.parseInt(exhibitionMap.get("price_adult")));
		exhibition.setPrice_student(Integer.parseInt(exhibitionMap.get("price_student")));
		exhibition.setPrice_baby(Integer.parseInt(exhibitionMap.get("price_baby")));
		exhibition.setLoc(exhibitionMap.get("loc"));
		exhibition.setDetails_place(exhibitionMap.get("details_place"));
		return exhibition;
	}

	private Map<String,String> upload(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String,String> exhibitionMap = new HashMap<String,String>();
				
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File dirPath = new File(imageRepository);
		factory.setRepository(dirPath); //업로드된 파일저장소를 설정
		factory.setSizeThreshold(1024*1024);//업로드파일최대사이즈 설정.byte단위.-1이면 무한
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);
			for (int i=0;i<items.size()  ;i++) { 
				FileItem fileItem=(FileItem)items.get(i);
			    if (fileItem.isFormField()) { //일반 양식이니? text필드,textarea등
			    	System.out.println("fileItem.getFieldName()="+fileItem.getFieldName());
			    	System.out.println("fileItem.getString()="+fileItem.getString("utf-8"));
			    	exhibitionMap.put(fileItem.getFieldName(),fileItem.getString("utf-8"));
			    	
			    } else { //input type="file"양식이니? //<input type="file" name="imageFileName" />
			    	System.out.println("fileItem.getFieldName()="+fileItem.getFieldName());//imageFileName
			    	System.out.println("fileItem.getName()="+fileItem.getName());//user가 업로드한 파일명
			    	System.out.println("fileItem.getSize()="+fileItem.getSize());//이미지파일의 크기
			    	
			    	if(fileItem.getSize()>0) {
			    		int idx = fileItem.getName().lastIndexOf("\\"); //추출시작인덱스;
			    		if(idx ==  -1) { //  "\\"가 존재하지않으면
			    			idx = fileItem.getName().lastIndexOf("/");
			    		}
			    		
				    	String fileName = fileItem.getName().substring(idx+1);
				    	System.out.println("fileName="+fileName);
				      
				    	exhibitionMap.put(fileItem.getFieldName(), fileName);
				    	
				    	File uploadFile = new File(imageRepository+"\\temp\\"+fileName);
				    	fileItem.write(uploadFile);
			    	}//if
			    }//if 
			}//for
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return exhibitionMap;
	}//upload()끝
	
	public void saveFile (HttpServletRequest request, HttpServletResponse response, String file,UUID uuid) throws IOException {
		if(file!=null  && file.length()!=0) {//첨부파일이 있는 경우에만
			File srcFile = new File(imageRepository+"\\"+"temp"+"\\"+file);
			File destDir = new File(imageRepository+"\\"+ uuid);
			destDir.mkdirs(); //(물리적인)폴더, 파일생성
			System.out.println("폴더생성확인해보삼~");
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		PrintWriter writer = response.getWriter(); //브라우저에 출력
		String msg = "<script>alert('inserted success!!');location.href="
				     +request.getContextPath()+"'/exhibitionList.do';</script>";		
		writer.print(msg);
	} 
}
