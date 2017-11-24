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
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{
		public void init(FilterConfig filterConfig)
		throws ServletException{
		}
		
		public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)
		throws IOException,ServletException{
			
			HttpServletRequest req = (HttpServletRequest) request;
			String user_id = req.getParameter("user_id");
			if(user_id.length()>16 || user_id.equals("")){
				req.setAttribute("ERROR", "<div class=\"ui negative message\"><div class=\"header\">ログインに失敗しました</div><p>IDまたはパスワードが違います</p></div>");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
				dispatcher.forward(req, (HttpServletResponse)response);
				return;
			}
			chain.doFilter(request, response);
		}
		public void destroy(){
		}
}
