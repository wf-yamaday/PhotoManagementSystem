package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Album;
import utility.DriverAccessor;

public class AlbumDAO extends DriverAccessor{

	public void RegistAlbum(String album_name,int year,Connection connection){
		try{
			String sql = "insert into album (album_name,year) values (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, album_name);
			stmt.setInt(2, year);
			stmt.executeUpdate();
			stmt.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
		}
	}
	public int Get_album_id(Connection connection){
		try {
			String sql = "select * from album where album_id=(select max(album_id) from album);";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rSet = stmt.executeQuery();
			rSet.first();
			
			int album_id = rSet.getInt("album_id");
			
			stmt.close();
			rSet.close();
			
			return album_id;
			} catch (Exception e){
				e.printStackTrace();
				return -1;
		}finally {
		}
	}
	
	public ArrayList<Integer> ViewAlbum(Connection connection){
		try{
			String sql = "select year,album_activation from album where album_activation=0 group by year desc";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rSet = stmt.executeQuery();
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			while(rSet.next()){
				int year = rSet.getInt("year");
				list.add(year);
			}
			rSet.close();
			stmt.close();
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
		}
	}
	
	public ArrayList<Album> ViewAlbum(int year,Connection connection){
		try {
			String sql = "select * from album where year=? and album_activation=0;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, year);
			ResultSet rSet = stmt.executeQuery();
			
			ArrayList<Album> list = new ArrayList<Album>();
			
			while(rSet.next()){
				Album album = new Album();
				album.setAlbum_id(rSet.getInt("album_id"));
				album.setAlbum_name(rSet.getString("album_name"));
				album.setYear(year);
				list.add(album);
			}
			stmt.close();
			rSet.close();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
		}
	}
	
	public Album SearchAlbum(String album_name,int year,Connection connection){
		try{
			String sql = "select * from album where album_name=? and year=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, album_name);
			stmt.setInt(2, year);
			
			ResultSet rSet = stmt.executeQuery();
			
			if(rSet.next()){
				rSet.first();
				Album album = new Album();
				album.setAlbum_id(rSet.getInt("album_id"));
				album.setAlbum_name(rSet.getString("album_name"));
				album.setYear(rSet.getInt("year"));
				stmt.close();
				rSet.close();
				return album;
			}else{
				return null;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
		}
	}
	public Album SearchAlbum(int album_id,Connection connection){
		try{
			String sql = "select * from album where album_activation=0 and album_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, album_id);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.first();
			Album album = new Album();
			album.setAlbum_id(resultSet.getInt("album_id"));
			album.setAlbum_name(resultSet.getString("album_name"));
			album.setYear(resultSet.getInt("year"));
			
			statement.close();
			resultSet.close();
			
			return album;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Album> GetCreatedAlbum(Connection connection){
		try {
			String sql = "select * from album where album_activation=0";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			ArrayList<Album> list = new ArrayList<>();
			while(resultSet.next()){
				Album album = new Album();
				album.setAlbum_id(resultSet.getInt("album_id"));
				album.setAlbum_name(resultSet.getString("album_name"));
				album.setYear(resultSet.getInt("year"));
				list.add(album);
			}
			statement.close();
			resultSet.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void EditAlbum(int album_id,String album_name,Connection connection){
		try {
			String sql = "update album set album_name=? where album_id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, album_name);
			statement.setInt(2, album_id);
			
			statement.executeUpdate();
			statement.close();
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
	}
	public int RemoveAlbum(int album_id,Connection connection){
		try {
			String sql = "update album set album_activation=1 where album_id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, album_id);
			statement.executeUpdate();
			statement.close();
			
			sql="select year from album where album_id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, album_id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.first();
			int year = resultSet.getInt("year");
			return year;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	public void UpdateAlbum(int album_id,Connection connection){
		try {
			String sql = "update album set album_activation=0 where album_id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, album_id);
			statement.executeUpdate();
			statement.close();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}