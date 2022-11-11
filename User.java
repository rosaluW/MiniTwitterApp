package miniTwitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User extends TypeEntry implements Observer {
	AdminPanel adminPanel = AdminPanel.getAdminInstance();
	private String userName;
	private List<User> followers;
	private List<User> followings;
	private List<TwitterPost> newsFeed;
	
	public User(String givenName) {
		super();
		userName = givenName;
		followers = new ArrayList<User>();
		followings = new ArrayList<User>();
		newsFeed = new ArrayList<TwitterPost>();
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public List<User> getFollowers() {
		return this.followers;
	}
	
	public void displayFollowers() {
		for (int i = 0; i < this.followers.size(); i++) {
			System.out.println(this.followers.get(i).getUserName());
		}
	}
	
	public List<User> getFollowings() {
		return this.followings;
	}
	
	public void displayFollowings() {
		for (int i = 0; i < this.followings.size(); i++) {
			System.out.println(this.followings.get(i).getUserName());
		}
	}
	
	public List<TwitterPost> getNewsFeed() {
		return this.newsFeed;
	}
	
	public void displayNewsFeed() {
		for (int i = 0; i < this.newsFeed.size(); i++) {
			System.out.println((i + 1) + " " + this.newsFeed.get(i).getPost());
		}
	}
	
	public void setUserName(String givenName) {
		this.userName = givenName;
	}
	
	public void startFollowing(String userIdToFollow) {
		User user = AdminPanel.getAdminInstance().getUser(userIdToFollow);
		this.followings.add(user);
	}
	
	public TwitterPost createPost(String msg) {
		TwitterPost newPost = new TwitterPost(msg);
		newsFeed.add(newPost);
		adminPanel.getTwitterPosts().add(newPost);
		notifyFollowers(newPost);

		return newPost;
	}
	
	public void notifyFollowers(TwitterPost newPost) {
		for (User follower : this.followers) {
			follower.update(newPost);
		}
	}
	
	public void update(TwitterPost newPost) {
		this.getNewsFeed().add(newPost);
		System.out.println("Added to " + this.getUserName() + "'s news feed");
	}
	
	
}
