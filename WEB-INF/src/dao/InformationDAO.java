package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Information_board;
import utility.DriverAccessor;

public class InformationDAO extends DriverAccessor{

	public void AddInformation(String user_id,String action,Connection connection){
		try {
			String sql = "insert into information (user_id,action) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user_id);
			statement.setString(2, action);
			statement.execute();
			statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
	}
	
	public ArrayList<Information_board> GetInformation(Connection connection){
		try {
			String sql = "select id,user_name,action,execution_time from information,user order by id desc limit 5";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			ArrayList<Information_board> list = new ArrayList<>();
			while(resultSet.next()){
				Information_board information_board = new Information_board();
				information_board.setUser_name(resultSet.getString("user_name"));
				information_board.setDate(resultSet.getTimestamp("execution_time"));
				information_board.setAction(resultSet.getString("action"));
				list.add(information_board);
			}
			statement.close();
			resultSet.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
