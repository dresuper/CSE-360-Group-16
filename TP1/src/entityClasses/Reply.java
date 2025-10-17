package entityClasses;

public class Reply {
	
	
	
	private String id;
	private String postId;
	private String name;
	private String text;
	private long create;
	private long update;
	
	
	public Reply() { 
		
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
	public Reply(String id, String postId, String name, String text, long create, long update) {
		
		this.id = id;
		this.postId = postId;
		this.name = name;
		this.text = text;
		this.create = create;
		this.update = update;
		
	}
	
	
	public String getId() { return id; 
	
	}
	
	
	public String getPostId() { return postId; 
	
	}
	
	
	public String getName() { return name; 
	
	}
	
	
	public String getText() { return text; 
	
	}
	
	public long getCreate() { return create; 
	
	}

	public long getUpdate() { return update; 
	
	}
	    public void setId(String s) { id = s;     }
	    public void setPostId(String s) { id = s;     }
	    public void setName(String s) { name = s; }
	    public void setText(String s) { text = s; }
	    public void setUpdate(long t) { update = t; }
	
}
