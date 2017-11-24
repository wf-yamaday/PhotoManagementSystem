package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Photo;
import utility.DriverAccessor;

public class PhotoDAO extends DriverAccessor{
	public void RegistPhoto(String photo_name,String path,int album_id,Connection connection){
		try{
			String sql = "insert into photo (photo_name,path,album_id) values (?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, photo_name);
			stmt.setString(2, path);
			stmt.setInt(3, album_id);
			stmt.executeUpdate();
			stmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
	}
	public int Get_photo_id(Connection connection){
		try{
			String sql = "select * from photo where photo_id=(select max(photo_id) from photo);";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rSet = stmt.executeQuery();
			rSet.first();
			
			int photo_id = rSet.getInt("photo_id");
			
			stmt.close();
			rSet.close();
			
			return photo_id;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public ArrayList<Photo> ViewPhoto(int album_id,Connection connection){
		try{
			String sql="select * from photo where album_id=? and activation=0;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,album_id);
			ResultSet rSet = statement.executeQuery();
			
			ArrayList<Photo> list = new ArrayList<Photo>();
			while(rSet.next()){
				Photo photo = new Photo();
				photo.setPhoto_id(rSet.getInt("photo_id"));
				photo.setPhoto_name(rSet.getString("photo_name"));
				photo.setPhoto_path(rSet.getString("path"));
				list.add(photo);
			}
			
			statement.close();
			rSet.close();
			
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int RemovePhoto(int photo_id,Connection connection){
		try {
			String sql = "update photo set activation=1 where photo_id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, photo_id);
			statement.executeUpdate();
			statement.close();
			
			sql="select * from photo where photo_id=(select max(photo_id) from photo)";
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			resultSet.first();
			int album_id = -1;
			album_id = resultSet.getInt("album_id");
			statement.close();
			resultSet.close();
			
			return album_id;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	public void RemovePhotoInAlbum(int album_id,Connection connection){
		try {
			String sql = "update photo set activation=1 where album_id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, album_id);
			statement.executeUpdate();
			statement.close();
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
	}
}
