package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.User;

public class SessionFilter implements Filter{
	public void init(FilterConfig filterConfig)
			throws ServletException{
			}
			
			public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)
			throws IOException,ServletException{
				//System.out.println("filter通過");
				HttpSession session = ((HttpServletRequest)request).getSession();
				User user = null;
				user = (User)session.getAttribute("user");
				if(user == null){
					request.setAttribute("ERROR", "<div class=\"ui negative message\"><div class=\"header\">セッションIDが取得できません</div><p>再度ログインしてください．</p></div>");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
					dispatcher.forward(request, response);
					return;
				}
				chain.doFilter(request, response);
			}
			public void destroy(){	
			}
}