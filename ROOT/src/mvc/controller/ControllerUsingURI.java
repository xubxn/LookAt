package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;


public class ControllerUsingURI extends HttpServlet {
	
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<String, CommandHandler>();
	
	public void init() throws ServletException {
		System.out.println("init() 호출");
		String configFile = getInitParameter("configFile");
		System.out.println("configFile="+configFile);
	    Properties prop = new Properties();											
	    System.out.println("prop="+prop);												
	    String configFilePath = getServletContext().getRealPath(configFile);
	    System.out.println("configFilePath="+configFilePath);
	    
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        
        Iterator keyIter = prop.keySet().iterator();
        while (keyIter.hasNext()) {
            String command = (String) keyIter.next();										
            String handlerClassName = prop.getProperty(command);
            try {
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance);
                System.out.println(command+" : " + commandHandlerMap.get(command));
            } catch (ClassNotFoundException | InstantiationException 
            		| IllegalAccessException e) {
                throw new ServletException(e);												
            }
        }
	}

	public void destroy() {
		System.out.println("destroy() 호출");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출");
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출");
		process(request, response);
	}

    private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {       
		String command = request.getRequestURI();	
		System.out.println("indexOf = "+command.indexOf(request.getContextPath()));
		if(command.indexOf(request.getContextPath())==0) {
			command = command.substring(request.getContextPath().length());
			System.out.println("command="+command);
		}
		CommandHandler handler  = commandHandlerMap.get(command);				
		if(handler==null) {
			handler = new NullHandler();		
		}
		String viewpage = null;
		try {		
			viewpage = handler.process(request, response)	;
		} catch (Throwable e) {
			throw new ServletException(e);
		}	
		System.out.println("viewpage = "+viewpage);

		if(viewpage!=null) {		
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
			dispatcher.forward(request, response);
		}
    }
}
