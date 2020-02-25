
public class Users {
	protected int id;
	protected String username;
	protected String password;
	protected String first_name;
	protected String last_name;
	protected String age;
	
	Users(){
		
	}
	
	Users(int id){
		this.id = id;
	}
	
	Users(String username, String password, String first_name, String last_name, String age){
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
	}
	
	Users(int userID, String username, String password, String first_name, String last_name, String age){
		this.id = userID;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
	}
	
	// Getters
	int getID() {
		return this.id;
		
	}
	
	String getUser() {
		return this.username;
		
	}
	
	String getPass() {
		return this.password;
	}
	
	String getFirst() {
		return this.first_name;
	}
	
	String getLast() {
		return this.last_name;
	}
	
	String getAge() {
		return this.age;
	}
	//
	
	// Setters
	void setID(int id) {
		this.id = id;
	}
	
	void setUser(String user) {
		this.username = user;
	}
	
	void setPass(String pass) {
		this.password = pass;
	}
	
	void setFirst(String first) {
		this.first_name = first;
	}
	
	void setLast(String last) {
		this.last_name = last;
	}
	
	void setAge(String age) {
		this.age = age;
	}
	//
}
