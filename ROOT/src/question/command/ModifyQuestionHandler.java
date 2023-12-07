package question.command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import question.model.Question;
import question.service.ModifyQuestionService;
import question.service.ModifyRequest;
import question.service.QuestionNotFoundException;
import question.service.ReadQuestionService;

public class ModifyQuestionHandler implements CommandHandler{

	private static final String FORM_VIEW="/view/modifyForm.jsp";
	private static String imageRepository = "C:\\question\\image_repository";

	private ReadQuestionService readService =new ReadQuestionService();
	private ModifyQuestionService modifyService=new ModifyQuestionService();
	
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
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			String noVal=request.getParameter("QA_no");
		int QA_no=Integer.parseInt(noVal);
		Question question=readService.getQuestion(QA_no);
		User AUTH_USER=(User)request.getSession().getAttribute("AUTH_USER");
		if(!canModify(AUTH_USER,question)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		ModifyRequest modReq=new ModifyRequest(question.getQ_no(),question.getQ_plus_file(),
												question.getQ_details(), question.getQ_title(),QA_no); //AUTH_USER.getMember_id(), 맨앞에서 삭제함
		request.setAttribute("modReq",modReq);
		return FORM_VIEW;
		}catch(QuestionNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		}
		
	private boolean canModify(User AUTH_USER,Question question) {
		String writerid=question.getMember_id();
		return AUTH_USER.getId().equals(writerid)|| AUTH_USER.getId().equals("admin");
	}
	
	private String processSubmit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,String> modReqMap = update(request,response);
		String Q_plus_file = modReqMap.get("Q_plus_file"); 
		
		int QA_no = Integer.parseInt(modReqMap.get("QA_no"));//수정할글번호
		
		ModifyRequest modReq= new ModifyRequest(
				Integer.parseInt(modReqMap.get("qnacategory")), 
				Q_plus_file, 
				modReqMap.get("Q_details"), 
				modReqMap.get("Q_title"),
				QA_no);
		
		System.out.println("수정컨트롤러 modReq="+modReq);
		
		
		modifyService.modify(modReq);//
		
		if(Q_plus_file!=null && Q_plus_file.length()!=0) {//첨부파일이 있는 경우에만
			String originalFileName = modReqMap.get("originalFileName");
			File oldFile = new File(imageRepository+"\\"+QA_no+"\\"+originalFileName);
			oldFile.delete(); 
			
			File srcFile = new File(imageRepository+"\\"+"temp"+"\\"+Q_plus_file);
			File destDir = new File(imageRepository+"\\"+QA_no);
			destDir.delete(); 
			destDir.mkdirs(); 
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			
		}
		
		return request.getContextPath()+"/Qread.do?QA_no="+QA_no;
	}//process
	
	private Map<String,String> update(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String,String> modReqMap = new HashMap<String,String>();
				
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File dirPath = new File(imageRepository);
		factory.setRepository(dirPath); 
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);
			for (int i=0;i<items.size()  ;i++) { 
				FileItem fileItem=(FileItem)items.get(i);
			    if (fileItem.isFormField()) {
			    	modReqMap.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
			    	
			    } else { 
			    	
			    	if(fileItem.getSize()>0) {
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
	
	
}




