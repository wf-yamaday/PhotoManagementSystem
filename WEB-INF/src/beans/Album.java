package beans;

public class Album {
	private int album_id = 0;
	private String album_name = null;
	private int year = 0;
	
	public Album(int album_id,String album_name,int year){
		this.album_id=album_id;
		this.album_name=album_name;
		//yearどうしようか
		this.year=year;
	}

	public Album() {
	}

	public int getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}


}
