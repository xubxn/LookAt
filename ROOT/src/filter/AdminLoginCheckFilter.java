package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;

//로그인 여부 검사 기능 - p612
//비번 찾기 등과 같이 로그인 여부를 검사할 때 필터링 역할 클래스
//요청 주소 /checkPwd.do

public class AdminLoginCheckFilter implements Filter {

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		User user = (User)request.getSession().getAttribute("AUTH_USER");
		
		if(session==null || session.getAttribute("AUTH_USER") ==null) {
			HttpServletResponse response = (HttpServletResponse) res;
			response.sendRedirect(request.getContextPath()+"/login.do");
		} else if(!user.getId().equals("admin")) {
			HttpServletResponse response = (HttpServletResponse) res;
			response.sendRedirect(request.getContextPath()+"/login.do");
		}else {
		chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	
	}
	
	public void destroy() {
		
	}
	
}
