package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Information_board;
import control.InformationManager;

public class GetInformationBoardServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		ArrayList<Information_board> list = new ArrayList<>();
		InformationManager informationManager = new InformationManager();
		
		list = informationManager.GetInformation();
		request.setAttribute("information", list);
		getServletContext().getRequestDispatcher("/jsp/Manager/toppage.jsp").forward(request, response);
	}

}
