package control;

import java.sql.Connection;
import java.util.ArrayList;


import beans.Information_board;
import dao.InformationDAO;

public class InformationManager {

	private Connection connection = null;
	public InformationManager(){
	}
	
	public void AddInfromation(String user_id,String album_name,int action_flag){
		InformationDAO informationDAO = new InformationDAO();
		this.connection = informationDAO.createConnection();
		
		String action = null;
		
		switch (action_flag) {
		case 1:
			action = "新規アルバム「"+album_name+"」を作成しました";
			break;
		case 2:
			action = "アルバム「"+album_name+"」に写真を追加しました";
			break;
		case 3:
			action = album_name+"に変更しました";
			break;
		default:
			break;
		}
		informationDAO.AddInformation(user_id,action,connection);
		informationDAO.closeConnection(connection);
		this.connection = null;
	}
	
	public ArrayList<Information_board> GetInformation(){
		InformationDAO informationDAO = new InformationDAO();
		this.connection = informationDAO.createConnection();
		
		ArrayList<Information_board> list = new ArrayList<>();
		list = informationDAO.GetInformation(connection);
		
		informationDAO.closeConnection(connection);
		this.connection = null;
		
		return list;
	}
}
