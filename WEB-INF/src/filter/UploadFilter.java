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

import org.apache.commons.lang3.StringUtils;

public class UploadFilter implements Filter{
	public void init(FilterConfig filterConfig)
	throws ServletException{
	}
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)
	throws IOException,ServletException{
		HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding("UTF-8");
		String album_name = req.getParameter("album_name");
		int flag = Integer.parseInt(req.getParameter("flag"));
		String album_id = req.getParameter("id");
		
		/*StringUtilsはApache CommonsのLangライブラリを利用*/
		if((album_name.length() > 64)|| !StringUtils.isNotBlank(album_name) || (album_name.indexOf("./") != -1) || (album_name.indexOf("../") != -1) ){
			req.setAttribute("ERROR", "<div class= \"ui negative icon message\"><i class=\"warning sign icon\"></i> <div class=\"content\"><div class=\"header\">ERROR</div><p>アルバム名を正しく入力してください</p></div></div>" );
			if(flag==1){
			RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/common/upload.jsp");
			dispatcher.forward(req, (HttpServletResponse)response);
			}else{
			RequestDispatcher dispatcher = req.getRequestDispatcher("/ViewPhotoServlet?id="+album_id);
			dispatcher.forward(req, (HttpServletResponse)response);
			}
			return;
		}
		
		album_name .replace("/",":");
		chain.doFilter(request, response);
	}
	public void destroy(){
	}
}
