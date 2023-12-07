package question.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class DownloadHandler implements CommandHandler {
	private static String imageRepository="C:\\question\\image_repository";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String QA_no= request.getParameter("QA_no");	//글번호
		String Q_plus_file= request.getParameter("Q_plus_file");	//이미지파일명

		//2.비즈니스로직 3.model
		//이미지파일저장소의 특정글번호)폴더에 저장되어잇는 file 가져오기
		String path = imageRepository+"\\"+ QA_no+"\\"+Q_plus_file;
		File imageFile=new File(path);
		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("content-disposition", "attachment;fileName="+Q_plus_file);
		
		FileInputStream in = new FileInputStream(imageFile);
		OutputStream out = response.getOutputStream();
		
		byte[] buffer=new byte[1024*8];
		while(true) {
			int cnt = in.read(buffer);
			if(cnt ==-1) break;
			out.write(buffer,0,cnt);
		}
		in.close();
		out.close();
		return null;
	}
}
