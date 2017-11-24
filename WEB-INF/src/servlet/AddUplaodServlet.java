package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.Album;
import beans.User;
import control.AlbumManager;
import control.InformationManager;
import control.PhotoManager;

@MultipartConfig(location="/tmp", maxFileSize=10485760)
public class AddUplaodServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		//jspからalbum_idを取得
		String album_id = request.getParameter("id");
		//セッションの取得
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		//DBへのアクセスの準備
		InformationManager informationManager = new InformationManager();
		AlbumManager albumManager = new AlbumManager();
		PhotoManager photoManager = new PhotoManager();
		
		//album情報の取得
		Album album = albumManager.SearchAlbum(Integer.parseInt(album_id));
		
		Part part = null;
		part = request.getPart("file");
		if(getFileName(part).equals("")){
			request.setAttribute("ERROR", "<div class= \"ui negative icon message\"><i class=\"warning sign icon\"></i> <div class=\"content\"><div class=\"header\">ERROR</div><p>写真ファイルを選択してください．</p></div></div>");
			getServletContext().getRequestDispatcher("/ViewPhotoServlet?=id"+album_id).forward(request,response);
			return;
		}
		String photo_name = getFileName(part);
		String file_suffix = getFileSuffix(photo_name);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'IMG_'yyyyMMdd_HHmmss");
		String file_name = simpleDateFormat.format(new Date(System.currentTimeMillis()))+"."+file_suffix;
		String path = getServletContext().getRealPath("/Photo"+"/"+album.getYear()+"/"+album.getAlbum_name()+"/"+file_name);
		part.write(path);
		
		//DBに写真情報を登録
		path = "/PhotoManagementSystem/Photo/"+album.getYear()+"/"+album.getAlbum_name()+"/"+file_name;
		photoManager.RegistPhoto(photo_name, path, album.getAlbum_id() ,user.getUser_id());
		informationManager.AddInfromation(user.getUser_id(),album.getAlbum_name(),2);
		
		getServletContext().getRequestDispatcher("/ViewPhotoServlet?id="+album_id).forward(request,response);
		
	}
	private String getFileName(Part part) {
		String name = null;
		for(String dispotion : part.getHeader("Content-Disposition").split(";")){
			if(dispotion.trim().startsWith("filename")){
				name = dispotion.substring(dispotion.indexOf("=")+1).replace("\"","").trim();
				name = name.substring(name.lastIndexOf("\\")+1);
				break;
			}
		}
		return name;
	}
	private String getFileSuffix(String file_name){
		if(file_name == null){
			return null;
		}else{
			int point = file_name.lastIndexOf(".");;
			if(point != -1){
				return file_name.substring(point + 1);
			}else{
				return file_name;
			}
		}
	}
}
