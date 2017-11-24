package servlet;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
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
public class UploadServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("UTF8");
		//jspからアルバム名，年度を取得
		String album_name = request.getParameter("album_name");
		String year = request.getParameter("year");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String user_id = user.getUser_id();
		int album_id = -1;
		InformationManager informationManager = new InformationManager();
		Part part = request.getPart("file");
		//エラー処理
		if(getFileName(part).equals("")){
			request.setAttribute("ERROR", "<div class= \"ui negative icon message\"><i class=\"warning sign icon\"></i> <div class=\"content\"><div class=\"header\">ERROR</div><p>写真ファイルを選択してください．</p></div></div>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/upload.jsp");
			dispatcher.forward(request, response);
			return;
		}
		//ディレクトリを作る
		File newdir = new File(getServletContext().getRealPath("/Photo"+"/"+year+"/"+album_name+"/"));
		System.out.println(getServletContext().getRealPath("/Photo"+"/"+year+"/"+album_name+"/"));
		if(newdir.mkdirs()){
			//新規作成の場合
			System.out.println("フォルダ作成に成功");
			//DBにアルバム情報を登録
			AlbumManager album_manager = new AlbumManager();
			album_id = album_manager.RegistAlbum(album_name, year,user_id);
			informationManager.AddInfromation(user_id, album_name,1);
		}else{
			//失敗もしくは，すでにある
			System.out.println("フォルダ作成に失敗");
			//DBのアルバム情報を確認
			AlbumManager album_manager = new AlbumManager();
			Album album = album_manager.SearchAlbum(album_name,year);
			if(album!=null){
				System.out.println("登録済み");
				album_id=album.getAlbum_id();
				System.out.println(album_id);
				album_manager.UpdateAlbum(album_id);
			}else{
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				System.out.println("エラー");
				return;
			}
		}
		String photo_name = getFileName(part);
		String file_suffix = getFileSuffix(photo_name);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'IMG_'yyyyMMdd_HHmmss");
		String file_name = simpleDateFormat.format(new Date(System.currentTimeMillis()))+"."+file_suffix;
		String path = getServletContext().getRealPath("/Photo")+"/"+year+"/"+album_name+"/"+file_name;
		part.write(path);
		
		//DBに写真情報を登録
		path = "/PhotoManagementSystem/Photo/"+year+"/"+album_name+"/"+file_name;
		PhotoManager photo_manager = new PhotoManager();
		photo_manager.RegistPhoto(photo_name, path, album_id ,user_id);
		informationManager.AddInfromation(user_id,album_name,2);
		request.setAttribute("message", "<div class=\"ui positive message\"><div class=\"header\">アップロードに成功しました</div><p>アルバムから確認してください．</p></div>");
		getServletContext().getRequestDispatcher("/jsp/common/upload.jsp").forward(request, response);
		
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
