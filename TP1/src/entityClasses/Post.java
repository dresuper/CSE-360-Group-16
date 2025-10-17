package entityClasses;

public class Post {
	
	
	
	private String id;
	private String text;
	private String title;
	private String name;
	private String tags;
	private long create;
	private long update;
	
	
	public Post() { 
		
	} 
	
	 /*****
     * <p> Method: User(String userName, String Text, boolean r1, boolean r2,
     * 		boolean r3) </p>
     * 
     * <p> Description: This constructor is used to establish user entity objects. </p>
     * 
     * @param userName specifies the account userName for this user
     * 
     * @param password specifies the account password for this user
     * 
     * @param r1 specifies the the Admin attribute (TRUE or FALSE) for this user
     * 
     * @param r2 specifies the the Student attribute (TRUE or FALSE) for this user
     * 
     * @param r3 specifies the the Reviewer attribute (TRUE or FALSE) for this user
     * 
     */
    // Constructor to initialize a new User object with userName, password, and role.
	public Post(String id, String name, String title, String text, String tags, long create, long update) {
		
		this.id = id;
		this.name = name;
		this.title = title;
		this.text = text;
		this.tags = tags;
		this.create = create;
		this.update = update;
	}
	
	
	public String getId() { return id; 
	
	}
	
	
	public String getName() { return name; 
	
	}
	
	
	public String getTitle() { return title; 
	
	}
	
	
	public String getText() { return text; 
	
	}
	
	
	public String getTags() { return tags; 
	
	}
	
	public long getCreate() { return create; 
	
	}

	public long getUpdate() { return update; 
	
	}
	    public void setTitle(String title) { this.title = title; }
	    public void setText(String text) { this.text = text; }
	    public void setTags(String tags) { this.tags = tags; }
	    public void epochupt(long update) { this.update = update; }
	
	
	    public String toString() {
	    	return "Post{" +
	    		    "id=' " + id + '\'' +
	    		    ", name='" + name + '\'' +
	    		    ", text='" + text + '\'' +
	    		    ", tags='" + tags + '\'' +
	    		    ", create='" + create + '\'' +
	    		    ", update='" + update + '\'' +
	    		    '}';
	    }
	
	
	
	
}
