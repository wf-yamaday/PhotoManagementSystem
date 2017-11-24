package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.AlbumManager;
import control.PhotoManager;

public class RemoveAlbumServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		String album_id = request.getParameter("id");
		
		AlbumManager albumManager = new AlbumManager();
		int year = albumManager.RemoveAlbum(Integer.parseInt(album_id));
		PhotoManager photoManager = new PhotoManager();
		photoManager.RemovePhotoInAlbum(Integer.parseInt(album_id));
		if(year != -1){
			request.setAttribute("message", "<div class= \"ui positive icon message\"><div class=\"content\"><div class=\"header\">アルバムを削除しました．</div></div></div>");
			getServletContext().getRequestDispatcher("/ViewAlbumListServlet?year="+year).forward(request,response);
		}else{
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println("エラー");
			return;
		}
	}
}
