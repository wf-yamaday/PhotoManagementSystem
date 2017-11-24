package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
			doPost(request,response);
		}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		HttpSession session = request.getSession(false);
		System.out.println("Logout処理開始");
		if(session != null)
			session.invalidate();
		System.out.println("sessionを破棄しました．");
		response.sendRedirect(request.getContextPath()+"/jsp/");
	}
}