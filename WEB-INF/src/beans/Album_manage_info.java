package beans;

import java.sql.Timestamp;

public class Album_manage_info {
	private String user_id = null;
	private Timestamp date = null;
	private int album_id = 0;
	
	public Album_manage_info(String user_id,Timestamp date,int album_id){
		this.user_id=user_id;
		this.date=date;
		this.album_id=album_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	
}
