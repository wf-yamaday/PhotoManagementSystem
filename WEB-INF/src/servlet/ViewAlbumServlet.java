package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.AlbumManager;

public class ViewAlbumServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		
		AlbumManager albumManager = new AlbumManager();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		list = albumManager.ViewAlbum();
		request.setAttribute("list",list);
		getServletContext().getRequestDispatcher("/jsp/common/viewalbum.jsp").forward(request, response);
	}

}