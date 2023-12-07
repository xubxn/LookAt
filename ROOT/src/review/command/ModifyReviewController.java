package review.command;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import auth.service.User;
import mvc.command.CommandHandler;
import review.model.ModifyRequest;
import review.model.Review;
import review.service.ReviewNotFoundException;
import review.service.reviewService;


public class ModifyReviewController implements CommandHandler {
	
	private static final String FORM_VIEW ="/view/modifyreview.jsp";
	private reviewService reviewService = new reviewService();
	private static String imageRepository = "C:\\board\\image_repository"; //업로드된 이미지파일이 저장이 되는 실제위치

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String strNo =request.getParameter("review_no");
			int review_no = Integer.parseInt(strNo);
			System.out.println(review_no);
			Review review = reviewService.getDetail(review_no);
			User AUTH_USER=(User)request.getSession().getAttribute("AUTH_USER");
			
			if(!canModify(AUTH_USER,review)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}

			request.setAttribute("review",review);
			return request.getContextPath()+FORM_VIEW;
		}catch(ReviewNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	

	private boolean canModify(User AUTH_USER,Review review) {
		String writerid = review.getMember_id();
		return AUTH_USER.getId().equals(writerid);
	}

	
	private String processSubmit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("processSubmit 진입");
		Map<String,String> revMap = update(request,response);
		String imageFileName = revMap.get("imageFileName");
		System.out.println("imageFileName = "+imageFileName);
		
		int review_no = Integer.parseInt(revMap.get("review_no"));//수정할글번호
		
		ModifyRequest modReq= new ModifyRequest(
				revMap.get("writerId"),
				review_no, 
				imageFileName,
				revMap.get("content"), 
				revMap.get("title"));	
		
		reviewService.modify(modReq);//
		
		if(imageFileName!=null && imageFileName.length()!=0) {//첨부파일이 있는 경우에만
			String originalFileName = revMap.get("originalFileName");
			File oldFile = new File(imageRepository+"\\"+review_no+"\\"+originalFileName);
			oldFile.delete(); 
			
			File srcFile = new File(imageRepository+"\\"+"temp"+"\\"+imageFileName);
			File destDir = new File(imageRepository+"\\"+review_no);
			destDir.delete(); 
			destDir.mkdirs(); 
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			
		}
		
		return request.getContextPath()+"/reviewList.do";
	}//process
	
	private Map<String,String> update(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("update 진입");
		Map<String,String> revMap = new HashMap<String,String>();
				
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File dirPath = new File(imageRepository);
		factory.setRepository(dirPath); 
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);
			for (int i=0;i<items.size() ;i++) { 
				FileItem fileItem=(FileItem)items.get(i);
			    if (fileItem.isFormField()) {
			    	revMap.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
			    	
			    } else { 
			    	
			    	if(fileItem.getSize()>0) {
			    		int idx = fileItem.getName().lastIndexOf("\\"); 
			    		if(idx ==  -1) { 
			    			idx = fileItem.getName().lastIndexOf("/");
			    		}
				    	String fileName = fileItem.getName().substring(idx+1);
				      
				    	revMap.put(fileItem.getFieldName(), fileName);
				    	System.out.println("fileName="+fileName);
				    	
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

		return revMap;
	}
	
	
}