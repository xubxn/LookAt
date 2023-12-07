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

//로그인 여부 검사 기능 - p612
//비번 찾기 등과 같이 로그인 여부를 검사할 때 필터링 역할 클래스
//요청 주소 /checkPwd.do

public class LoginCheckFilter implements Filter {

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		//세션에 저장된 속성 유무를 검사
		//로그인에 성공한 회원정보(회원 id, name)를 "AUTH_USER" 이름으로 User 클래스에 저장
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		
		if(session==null || session.getAttribute("AUTH_USER") ==null) {
			HttpServletResponse response = (HttpServletResponse) res;
			response.sendRedirect(request.getContextPath()+"/login.do");
		} else {
		chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	
	}
	
	public void destroy() {
		
	}
	
}
