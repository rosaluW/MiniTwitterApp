package miniTwitter;

public class TwitterPost {
	private String post;
	
	public TwitterPost(String userPost) {
		post = userPost;
	}
	
	public String getPost() {
		return post;
	}
	
	public void setTwitterPost(String userPost) {
		post = userPost;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
