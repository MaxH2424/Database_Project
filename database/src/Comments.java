public class Comments {
	protected String rating;
	protected String comment;
	protected String username;
	protected String url;
	
	Comments(){
		
	}
	
	Comments(String rating, String comment, String username, String url){
		this.rating = rating;
		this.comment = comment;
		this.username = username;
		this.url = url;
	}
	
	// Getters
	public String getRating() {
		return this.rating;
		
	}
	
	public String getComment() {
		return this.comment;
		
	}
	
	public String getUsername() {
		return this.username;
		
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
	
	public void setUserEmail(String username) {
		this.username = username;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
