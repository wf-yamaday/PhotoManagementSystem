package beans;

import java.sql.Timestamp;

public class Photo_manage_info {
	private int album_id = 0;
	private Timestamp date = null;
	private int photo_id =0;
	
	public Photo_manage_info(int album_id,Timestamp date,int photo_id){
		this.album_id=album_id;
		this.date=date;
		this.photo_id=photo_id;
	}

	public int getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
}
