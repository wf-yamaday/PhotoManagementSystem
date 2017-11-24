package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import utility.DriverAccessor;

public class Album_manage_infoDAO extends DriverAccessor{

	public void RegistAlbum_manage_info(int album_id,String user_id,Connection connection){
		try{
			String sql = "insert into album_manage_info (album_id,user_id) values (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, album_id);
			stmt.setString(2, user_id);
			stmt.executeUpdate();
			stmt.close();
			
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
	}
	}
}
