package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utility.DriverAccessor;

public class Photo_manage_infoDAO extends DriverAccessor{
	public void Registhoto_manage_info(int photo_id,int album_id,Connection connection){
		try{
			String sql = "insert into photo_manage_info (photo_id,album_id) values (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,photo_id);
			stmt.setInt(2, album_id);
			stmt.executeUpdate();
			stmt.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
