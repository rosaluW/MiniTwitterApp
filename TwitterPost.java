package miniTwitter;

public class TwitterPost {
	private String post;
	private long tweetCreationTime;
	
	public TwitterPost(String userPost) {
		post = userPost;
		setTweetCreationTime(System.currentTimeMillis());
	}
	
	public String getPost() {
		return post;
	}
	
	public void setTwitterPost(String userPost) {
		post = userPost;
	}

	public void setTweetCreationTime(long givenTime) {
		this.tweetCreationTime = givenTime;
	}

	public long getTweetCreationTime() {
		return this.tweetCreationTime;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
