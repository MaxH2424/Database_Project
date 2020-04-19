
public class Comedians {
	protected int comid = 0;
	protected String fName = "";
	protected String lName = "";
	
	Comedians(){};
	Comedians(String fName, String lName, int comid){
		this.fName = fName;
		this.lName = lName;
		this.comid = comid;
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
	public int getcomid() {
		return comid;
	}
	public void setcomid(int comid) {
		this.comid=comid;
	}
}
