package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.PhotoManager;

public class RemovePhotoServlet extends HttpServlet{

private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		
		String photo_id = request.getParameter("id");
		PhotoManager photoManager = new PhotoManager();
		int album_id = photoManager.RemovePhoto(Integer.parseInt(photo_id));
		
		//album_idが-1なら500エラー
		if(album_id != -1){
		request.setAttribute("message", "<div class= \"ui positive icon message\"><div class=\"content\"><div class=\"header\">写真を1枚削除しました．</div></div></div>");
		getServletContext().getRequestDispatcher("/ViewPhotoServlet?id="+album_id).forward(request,response);
		}else{
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		System.out.println("エラー");
		return;
		}
	}
}