package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Album;
import control.AlbumManager;

public class ViewAlbumListServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		String year = request.getParameter("year");

		AlbumManager albumManager = new AlbumManager();
		ArrayList<Album> list = new ArrayList<Album>();
		
		list=albumManager.ViewAlbum(Integer.parseInt(year));
		request.setAttribute("list", list);
		request.setAttribute("year", year);
		getServletContext().getRequestDispatcher("/jsp/common/viewalbumlist.jsp").forward(request,response);
		
	}
}
