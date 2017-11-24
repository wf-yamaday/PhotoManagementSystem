package control;

import java.sql.Connection;
import java.util.ArrayList;

import beans.Photo;
import dao.PhotoDAO;
import dao.Photo_manage_infoDAO;

public class PhotoManager {

	private Connection connection = null;
	public PhotoManager(){
	}
	
	public void RegistPhoto(String photo_name,String path,int album_id,String user_id){
		PhotoDAO photoDAO = new PhotoDAO();
		this.connection=photoDAO.createConnection();
		
		photoDAO.RegistPhoto(photo_name, path,album_id, connection);
		int photo_id = photoDAO.Get_photo_id(connection);
		photoDAO.closeConnection(connection);
		this.connection=null;
		
		Photo_manage_infoDAO photo_manage_infoDAO = new Photo_manage_infoDAO();
		this.connection=photo_manage_infoDAO.createConnection();
		photo_manage_infoDAO.Registhoto_manage_info(photo_id, album_id, connection);
		photo_manage_infoDAO.closeConnection(connection);
		this.connection=null;
		
	}
	
	public ArrayList<Photo> ViewPhoto(int album_id){
		
		PhotoDAO photoDAO = new PhotoDAO();
		this.connection = photoDAO.createConnection();
		
		ArrayList<Photo> list = new ArrayList<Photo>();
		list=photoDAO.ViewPhoto(album_id, connection);
		
		photoDAO.closeConnection(connection);
		this.connection=null;
		
		return list;
	}
	
	public int RemovePhoto(int photo_id){
		PhotoDAO photoDAO = new PhotoDAO();
		this.connection = photoDAO.createConnection();
		
		int album_id = photoDAO.RemovePhoto(photo_id, connection);
		
		photoDAO.closeConnection(connection);
		this.connection=null;
		
		return album_id;
		
	}
	public void RemovePhotoInAlbum(int album_id){
		PhotoDAO photoDAO = new PhotoDAO();
		this.connection = photoDAO.createConnection();
		photoDAO.RemovePhotoInAlbum(album_id, connection);
		
		photoDAO.closeConnection(connection);
		this.connection = null;
		return;
	}
}