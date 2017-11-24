package beans;

public class User {
	private String user_id = null;
	private String user_name = null;
	private String password = null;
	private int role = 0;
	
	public User(){
	}
	public User(String user_id,String user_name,String password,int role){
		this.user_id=user_id;
		this.user_name=user_name;
		this.password=password;
		this.role = role;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
}
