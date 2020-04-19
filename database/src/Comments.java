public class Comments {
	protected String rating;
	protected String comment;
	protected int comedianID;
	protected String url;
	
	Comments(String rating, String comment, int comedianID, String url){
		this.rating = rating;
		this.comment = comment;
		this.comedianID = comedianID;
		this.url = url;
	}
	
	// Getters
	public String getRating() {
		return this.rating;
		
	}
	
	public String getComment() {
		return this.comment;
		
	}
	
	public int getComedianID() {
		return this.comedianID;
		
	}
	
	public String getUrl() {
		return this.url;
		
	}
	
	// Setters
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setUserEmail(int id) {
		this.comedianID = id;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
