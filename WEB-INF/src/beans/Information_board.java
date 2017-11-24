package beans;

import java.sql.Timestamp;

public class Information_board {
	private String user_name = null;
	private String action = null;
	private Timestamp date = null;
	
	public Information_board(String user_name,String action,Timestamp date){
		this.user_name=user_name;
		this.action=action;
		this.date = date;
	}

	public Information_board(){
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
