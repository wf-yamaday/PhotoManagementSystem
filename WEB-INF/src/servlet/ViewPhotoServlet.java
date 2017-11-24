package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Album;
import beans.Photo;
import control.AlbumManager;
import control.PhotoManager;

public class ViewPhotoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		
		String album_id = request.getParameter("id");
		
		PhotoManager photoManager = new PhotoManager();
		ArrayList<Photo> list = new ArrayList<Photo>();
		list = photoManager.ViewPhoto(Integer.parseInt(album_id));
		
		AlbumManager albumManager = new AlbumManager();
		Album album = albumManager.SearchAlbum(Integer.parseInt(album_id));
		
		request.setAttribute("album",album);
		request.setAttribute("list",list);
		getServletContext().getRequestDispatcher("/jsp/common/viewphoto.jsp").forward(request,response);
		
	}
}