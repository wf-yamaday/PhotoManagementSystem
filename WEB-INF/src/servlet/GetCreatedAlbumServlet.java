package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Album;
import control.AlbumManager;

public class GetCreatedAlbumServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		ArrayList<Album> list = new ArrayList<>();
		AlbumManager albumManager = new AlbumManager();
		
		list = albumManager.GetCreatedAlbum();
		request.setAttribute("lsit", list);
		getServletContext().getRequestDispatcher("/jsp/common/upload.jsp").forward(request, response);
	}

}