
public class Users {
	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	protected int id = 0;
	protected String username = "NA";
	protected String password = "NA";
	protected String first_name = "NA";
	protected String last_name ="NA";
	protected String gender = "Male";
	protected String age = "NA";
	protected int favorite;
	
	public Users(){
		
	}
	
	public Users(int id){
		this.id = id;
	}
	
	public Users(String username, String first_name, String last_name, String age) {
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
	}
	
	public Users(String username, String age, String first_name, String last_name, String pass, String gender) {
		this.username = username;
		this.age = age;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = pass;
		this.gender = gender;
		
	}
	
	public Users(String username, String age, String first_name, String last_name, String pass, String gender, int favorite) {
		this.username = username;
		this.age = age;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = pass;
		this.gender = gender;
		this.favorite = favorite;
		
	}
	
	public Users(int userID, String username, String first_name, String last_name, String age){
		this.id = userID;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
	}
	
	public Users(String username, String password, String first_name, String last_name, String age){
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
	}
	
	public Users(int userID, String username, String password, String first_name, String last_name, String age){
		this.id = userID;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
	}
	
	// Getters
	public String getGender() {
		return this.gender;
		
	}
	
	public int getFavorites() {
		return this.favorite;
		
	}
	
	public int getID() {
		return this.id;
		
	}
	
	public String getUser() {
		return this.username;
		
	}
	
	public String getPass() {
		return this.password;
	}
	
	public String getFirst() {
		return this.first_name;
	}
	
	public String getLast() {
		return this.last_name;
	}
	
	public String getAge() {
		return this.age;
	}
	//
	
	// Setters
	public void setID(int id) {
		this.id = id;
	}
	
	public void setUser(String user) {
		this.username = user;
	}
	
	public void setPass(String pass) {
		this.password = pass;
	}
	
	public void setFirst(String first) {
		this.first_name = first;
	}
	
	public void setLast(String last) {
		this.last_name = last;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	//
}
