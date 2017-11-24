package control;

import java.sql.Connection;
import java.util.ArrayList;

import beans.Album;
import dao.AlbumDAO;
import dao.Album_manage_infoDAO;

public class AlbumManager {

	private Connection connection = null;
	public AlbumManager(){
	}
	//アルバムを新規登録
	public int RegistAlbum(String album_name,String year,String user_id){
		//AlbumDAOを使ってアルバム情報を登録する
		AlbumDAO album_dao = new AlbumDAO();
		this.connection = album_dao.createConnection();
		
		int year_label = Integer.parseInt(year);
		album_dao.RegistAlbum(album_name, year_label,connection);
		int album_id = album_dao.Get_album_id(connection);
		album_dao.closeConnection(connection);
		this.connection = null;
		
		Album_manage_infoDAO album_manage_infoDAO= new Album_manage_infoDAO();
		this.connection = album_manage_infoDAO.createConnection();
		
		album_manage_infoDAO.RegistAlbum_manage_info(album_id,user_id, connection);
		album_manage_infoDAO.closeConnection(connection);
		this.connection=null;
		
		return album_id;
	}
	
	public Album SearchAlbum(String album_name,String year){
		
		int year_label = Integer.parseInt(year);
		
		AlbumDAO albumDAO = new AlbumDAO();
		this.connection = albumDAO.createConnection();
		
		Album album = albumDAO.SearchAlbum(album_name,year_label,connection);
		albumDAO.closeConnection(connection);
		this.connection = null;
		
		return album;
	}
	public Album SearchAlbum(int album_id){
		AlbumDAO albumDAO = new AlbumDAO();
		this.connection = albumDAO.createConnection();
		Album album = albumDAO.SearchAlbum(album_id, connection);
		albumDAO.closeConnection(connection);
		this.connection = null;
		return album;
	}
	public ArrayList<Integer> ViewAlbum(){
		AlbumDAO albumDAO = new AlbumDAO();
		this.connection = albumDAO.createConnection();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		list = albumDAO.ViewAlbum(connection);
		
		albumDAO.closeConnection(connection);
		this.connection = null;
		
		return list;
	}
	public ArrayList<Album> ViewAlbum(int year){
		AlbumDAO albumDAO = new AlbumDAO();
		this.connection=albumDAO.createConnection();
		ArrayList<Album> list = new ArrayList<Album>();
		
		list=albumDAO.ViewAlbum(year,connection);
		
		albumDAO.closeConnection(connection);
		this.connection = null;
		
		return list;
	}
	public ArrayList<Album> GetCreatedAlbum(){
		AlbumDAO albumDAO = new AlbumDAO();
		this.connection = albumDAO.createConnection();
		ArrayList<Album> list = new ArrayList<>();
		
		list = albumDAO.GetCreatedAlbum(connection);
		albumDAO.closeConnection(connection);
		this.connection=null;
		return list;
	}
	public void EditAlbum(int album_id,String album_name){
		AlbumDAO albumDAO = new AlbumDAO();
		this.connection = albumDAO.createConnection();
		albumDAO.EditAlbum(album_id, album_name, connection);
		
		albumDAO.closeConnection(connection);
		this.connection = null;
		return;
	}
	public int RemoveAlbum(int album_id){
		AlbumDAO albumDAO = new AlbumDAO();
		this.connection = albumDAO.createConnection();
		int year = -1;
		year = albumDAO.RemoveAlbum(album_id, connection);
		albumDAO.closeConnection(connection);
		this.connection = null;
		
		return year;
	}
	public void UpdateAlbum(int album_id){
		AlbumDAO albumDAO = new AlbumDAO();
		this.connection = albumDAO.createConnection();
		
		albumDAO.UpdateAlbum(album_id, connection);
		albumDAO.closeConnection(connection);
		this.connection = null;
		return;
	}
}
