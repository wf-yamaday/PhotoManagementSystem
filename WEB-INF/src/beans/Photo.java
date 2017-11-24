package beans;


public class Photo {
	private int photo_id=0;
	private String photo_name = null;
	public int getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}

	private String photo_path = null;
	private int activation = 0;
	private String comment = null;
	private int album_id;
	
	public Photo(int photo_id,String photo_name,String photo_path,int activation,String comment,int album_id){
		this.photo_id=photo_id;
		this.photo_name=photo_name;
		this.photo_path=photo_path;
		this.activation=activation;
		this.comment=comment;
		this.album_id=album_id;
	}

	public Photo() {
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}

	public String getPhoto_name() {
		return photo_name;
	}

	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public int getActivation() {
		return activation;
	}

	public void setActivation(int activation) {
		this.activation = activation;
	}
}
