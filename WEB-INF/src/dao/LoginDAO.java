package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;
import utility.DriverAccessor;

public class LoginDAO extends DriverAccessor{

	public User ceritfyUser(String user_id,Connection connection){
		try{
			String sql = "select * from user where user_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,user_id);
			
			ResultSet rs =stmt.executeQuery();
			rs.first();
		
			User user = new User();
			user.setUser_id(rs.getString("user_id"));
			user.setUser_name(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));
			user.setRole(rs.getInt("role"));
		
			stmt.close();
			rs.close();
		
			return user;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally {
		}
	}
}
