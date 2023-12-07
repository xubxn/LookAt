package exhibition.command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import auth.service.User;
import exhibition.exception.ExhibitionNotFoundException;
import exhibition.exception.PermissionDeniedException;
import exhibition.model.Exhibition;
import exhibition.service.ModifyExhibitionService;
import exhibition.service.ReadExhibitionService;
import mvc.command.CommandHandler;
public class ModifyExhibitionHandler implements CommandHandler {

	private String FORM_VIEW = "/view/exhibition/modifyForm.jsp";
	private static String imageRepository = "C:\\exhibition\\image_repository";

	// 수정폼 보여줄때 상세 내용을 가져오기 위한 Service객체 생성
	private ReadExhibitionService readExhibitionService = new ReadExhibitionService();

	// 수정처리를 위한 Service
	private ModifyExhibitionService modifyExhibitionService = new ModifyExhibitionService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ModifyExhibitionHandler-process()진입");

		if (request.getMethod().equalsIgnoreCase("get")) { // 요청방식이 get 방식이면 FORM_VIEW 보여주기
			System.out.println("process-if-processForm");
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("post")) { // 요청방식이 post 방식이면 회원가입 처리
			System.out.println("process-else if-processSubmit");
			return processSubmit(request, response); 
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 405오류
			return null;
		}

	} // process

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ModifyExhibitionHandler-processForm() 진입");
		
		// 1.파라미터받기
		int no = Integer.parseInt(request.getParameter("no"));// 수정하고 싶은 글번호
		System.out.println("exhibitionNo: " + no);
		
		
		// 2.비즈니스로직
		// 상세정보 저장해줄 Exhibition타입의 변수 선언
		Exhibition exhibition = readExhibitionService.getDetail(no); 
		
		//수정 자격 체크, 로그인된 세션 가져와서 그 세션 중 id가 admin일 경우만 수정 가능
		User user = (User) request.getSession().getAttribute("AUTH_USER"); // 세션로그인유저정보
		if (!canModify(user, exhibition)) { // user에 저장된 아이디가 'admin'이 아니라면 수정불가
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			System.out.println(user.getId());
			return null;
		}
		
		
		// 3. request로 exhibition객체 전달
		request.setAttribute("modReq", exhibition);
		
		
		// 4.View
		return request.getContextPath() + FORM_VIEW;
	} // processForm

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws ExhibitionNotFoundException, IOException, PermissionDeniedException {
		
		System.out.println("ModifyExhibitionHandler-processSubmit() 진입");

		Map<String,String> modReqMap = update(request,response);
		String thumbnail = modReqMap.get("thumbnail");
		String details_img = modReqMap.get("details_img");
		
		UUID uuid = UUID.randomUUID();
		
		//수정 내용을 저장해주기 위한 코드
		Exhibition exhibition = new Exhibition();
		exhibition.setExhibition_no(Integer.parseInt(modReqMap.get("exhibition_no")));
		exhibition.setTitle(modReqMap.get("title"));
		exhibition.setOpen_date(Date.valueOf(modReqMap.get("open_date")));
		exhibition.setEnd_date(Date.valueOf(modReqMap.get("end_date")));
		exhibition.setPlace( modReqMap.get("place"));
		exhibition.setThumbnail(uuid + "/" + modReqMap.get("thumbnail"));
		exhibition.setDetails_img(uuid + "/" + modReqMap.get("details_img"));
		exhibition.setIntroduction(modReqMap.get("introduction"));
		exhibition.setPrice_no(Integer.parseInt(modReqMap.get("price_no")));
		exhibition.setPrice_adult( Integer.parseInt(modReqMap.get("price_adult")));
		exhibition.setPrice_student(Integer.parseInt(modReqMap.get("price_student")));
		exhibition.setPrice_baby(Integer.parseInt(modReqMap.get("price_baby")));
		exhibition.setLoc_no(Integer.parseInt(modReqMap.get("loc_no")));
		exhibition.setLoc(modReqMap.get("loc"));
		exhibition.setDetails_place(modReqMap.get("details_place"));
		
			
		// 3.Model
		modifyExhibitionService.modify(exhibition);

		saveFile (request, response, thumbnail, uuid);
		saveFile (request, response, details_img, uuid);
		
		System.out.println("request.getParameter(\"exhibition_no\") = " + request.getParameter("exhibition_no"));
		//4.View
		//response.sendRedirect(request.getContextPath()+ "/exhibition/read.do?no="+ request.getParameter("exhibition_no"));
		response.sendRedirect(request.getContextPath() + "/exhibition/read.do?no=" + modReqMap.get("exhibition_no"));
		return null;
	}// processSubmit

	private boolean canModify(User user, Exhibition exhibition) {
		return user.getId().equals("admin");
	}

	private Map<String,String> update(HttpServletRequest request, HttpServletResponse response) {
			
			Map<String,String> modReqMap = new HashMap<String,String>();
					
			DiskFileItemFactory factory = new DiskFileItemFactory();
			File dirPath = new File(imageRepository);
			factory.setRepository(dirPath); 
			factory.setSizeThreshold(1024*1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				List items = upload.parseRequest(request);
				for (int i = 0; i < items.size(); i++) { 
					FileItem fileItem=(FileItem)items.get(i);
				    if (fileItem.isFormField()) {
				    	modReqMap.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
				    } else { 
				    	if (fileItem.getSize() > 0) {
				    		int idx = fileItem.getName().lastIndexOf("\\"); 
				    		if(idx ==  -1) { 
				    			idx = fileItem.getName().lastIndexOf("/");
				    		}
					    	String fileName = fileItem.getName().substring(idx+1);
					      
					    	modReqMap.put(fileItem.getFieldName(), fileName);
					    	
					    	File uploadFile = new File(imageRepository+"\\temp\\"+fileName);
					    	fileItem.write(uploadFile);
				    	}
				    }
				}
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			return modReqMap;
		} 
	
	public void saveFile (HttpServletRequest request, HttpServletResponse response, String file,UUID uuid) throws IOException {
		if( file!=null  && file.length()!=0) { //첨부파일이 있는 경우에만
			File srcFile = new File(imageRepository+"\\"+"temp"+"\\"+file);
			File destDir = new File(imageRepository+"\\"+ uuid);
			destDir.mkdirs(); //(물리적인)폴더, 파일생성
			System.out.println("폴더생성확인해보삼~");
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		PrintWriter writer = response.getWriter(); //브라우저에 출력
		String msg = "<script>alert('inserted success!!');location.href="
				     +request.getContextPath()+"'/exhibition/list.do';</script>";		
		writer.print(msg);
	} 
}
