
public class Comedians {
	protected int id = 0;
	protected String fName = "";
	protected String lName = "";
	
	Comedians(){};
	Comedians(String fName, String lName, int id){
		this.fName = fName;
		this.lName = lName;
		this.id = id;
	}
	
	Comedians(String fName){
		this.fName = fName;
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getid() {
		return id;
	}
	public void setid(int comid) {
		this.id=comid;
	}
}
