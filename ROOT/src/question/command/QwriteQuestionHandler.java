package question.command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import question.service.QwriteQuestionService;

public class QwriteQuestionHandler implements CommandHandler{
	private static final String FORM_VIEW="/view/newQuestionForm.jsp";
	private QwriteQuestionService qwriteService=new QwriteQuestionService();
	private static String imageRepository = "C:\\question\\image_repository";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return processForm(request, response);			
		} else if(request.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return null;
		}
	}
	private String processForm(HttpServletRequest request,HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;}
	
private String processSubmit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,String> questionMap = upload(request,response);
		String Q_plus_file = questionMap.get("Q_plus_file");
		
		Question question=new Question(
							Integer.parseInt(questionMap.get("qnacategory")),
							questionMap.get("member_id"),
							questionMap.get("Q_plus_file"),
							questionMap.get("Q_details"),
							questionMap.get("Q_title"));

		int QA_no= qwriteService.insert(question);
		
		if(Q_plus_file!=null  && Q_plus_file.length()!=0) {//첨부파일이 있는 경우에만
			File srcFile = new File(imageRepository+"\\"+"temp"+"\\"+Q_plus_file);
			File destDir = new File(imageRepository+"\\"+QA_no);
			destDir.mkdirs(); //(물리적인)폴더, 파일생성
			System.out.println("폴더생성확인해보삼~");
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		PrintWriter writer = response.getWriter(); //브라우저에 출력
		String msg = "<script>alert('inserted success!!');location.href="
				     +request.getContextPath()+"'/Qlist.do';</script>";		
		writer.print(msg);
		
		return null;
	}
private Map<String,String> upload(HttpServletRequest request, HttpServletResponse response) {
	
	Map<String,String> questionMap = new HashMap<String,String>();
			
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
		    	questionMap.put(fileItem.getFieldName(),fileItem.getString("utf-8"));
		    	
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
			      
			    	questionMap.put(fileItem.getFieldName(), fileName);
			    	
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
		
	return questionMap;
}//upload()끝

}











