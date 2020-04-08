import java.sql.Date;

public class Videos {
	protected int id = 0;
	protected String url = "NA";
	protected String title = "NA";
	protected String description = "NA";
	protected String tags ="NA";
	protected Date postDate;

	
	public Videos(){
		
	}
	
	public Videos(int id){
		this.id = id;
	}
	
	public Videos(String url) {
		this.url = url;
	}
	
	public Videos(String d, Date pdate, String title, String tags, String url, int id) {
		this.description = d;
		this.id = id;
		this.postDate = pdate;
		this.title = title;
		this.tags = tags;
		this.url = url;
	}
	
	public Videos(String url, String title, String description, String tags) {
		this.url = url;
		this.title = title;
		this.description = description;
		this.tags = tags;
	}
	
	public Videos(int id, String url, String title, String description, String tags) {
		this.id = id;
		this.url = url;
		this.title = title;
		this.description = description;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
}
