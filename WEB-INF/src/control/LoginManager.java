package control;

import java.sql.Connection;

import beans.User;
import dao.LoginDAO;


public class LoginManager {

	private Connection connection = null;
	public LoginManager(){
	}
	//ユーザーの認証
	public User certifyUser(String user_id,String password){
		LoginDAO dao = new LoginDAO();
		this.connection = dao.createConnection();
		
		User user = dao.ceritfyUser(user_id, this.connection);
		
		dao.closeConnection(connection);
		this.connection = null;
		
		if(user==null){
			return null;
		}else if(password.equals(user.getPassword())){
			user.setPassword(null);
			return user;
		}else{
			return null;
		}
	}
}
