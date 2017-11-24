package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Album;
import beans.User;
import control.AlbumManager;
import control.InformationManager;

public class EditAlbumServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		//jspから値を取得
		String album_id = request.getParameter("id");
		String album_name = request.getParameter("album_name");
		//セッションの取得
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		AlbumManager albumManager = new AlbumManager();
		Album album = albumManager.SearchAlbum(Integer.parseInt(album_id));
		File new_file = new File(getServletContext().getRealPath("/Photo/"+album.getYear()+"/"+album_name));
		if(new_file.exists()){
			request.setAttribute("ERROR", "<div class= \"ui negative icon message\"><i class=\"warning sign icon\"></i> <div class=\"content\"><div class=\"header\">ERROR</div><p>そのアルバム名はすでに使用されています．</p></div></div>");
			getServletContext().getRequestDispatcher("/ViewPhotoServlet?id="+album_id).forward(request,response);
			return;
		}else{
			File old_file = new File(getServletContext().getRealPath("/Photo/"+album.getYear()+"/"+album.getAlbum_name()));
			if(!(old_file.renameTo(new_file))){
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				System.out.println("エラー");
				return;
			}
		}
		
		albumManager.EditAlbum(Integer.parseInt(album_id), album_name);
		InformationManager informationManager = new InformationManager();
		String text = "アルバム「"+album.getAlbum_name()+"」を「"+album_name+"」";
		informationManager.AddInfromation(user.getUser_id(), text, 3);
		
		request.setAttribute("message", "<div class= \"ui positive icon message\"><div class=\"content\"><div class=\"header\">アルバム名を変更しました．</div></div></div>");
		getServletContext().getRequestDispatcher("/ViewPhotoServlet?id="+album_id).forward(request,response);
	}
}
