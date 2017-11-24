package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import control.LoginManager;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
	request.setCharacterEncoding("UTF-8");
	
	//jspの文字列を取得
	String user_id=request.getParameter("user_id");
	String password=request.getParameter("password");
	
	LoginManager manager = new LoginManager();
	
	User user = manager.certifyUser(user_id, password);
	
	HttpSession session = request.getSession();
	
	if(user==null){
		request.setAttribute("ERROR","<div class=\"ui negative message\"><div class=\"header\">ログインに失敗しました</div><p>IDまたはパスワードが違います</p></div>");
		getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}else if(user.getRole()==1){
		session.setAttribute("user", user);
		response.sendRedirect(response.encodeRedirectURL("/PhotoManagementSystem/Information"));
	}else{
		session.setAttribute("user", user);
		response.sendRedirect(response.encodeRedirectURL("/PhotoManagementSystem/jsp/User/toppage.jsp"));
	}
	}
}
