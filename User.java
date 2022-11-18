package miniTwitter;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class User extends TypeEntry implements Observer, Visitable {
	AdminPanel adminPanel = AdminPanel.getAdminInstance();
	private String userName;
	private List<User> followers;
	private List<User> followings;
	private List<TwitterPost> newsFeed;
    Scene userViewScene;
	Stage userStage;
	
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

	public void setUserName(String givenName) {
		this.userName = givenName;
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
	
	public void startFollowing(String userIdToFollow) {
		User user = AdminPanel.getAdminInstance().getUser(userIdToFollow);
		this.followings.add(user);
		user.getFollowers().add(this);
	}
	
	public TwitterPost createPost(String msg) {
		TwitterPost newPost = new TwitterPost(this.getUserName() + " posted: " + msg);
		newsFeed.add(newPost);
		adminPanel.getTwitterPosts().add(newPost);
		this.notifyFollowers(newPost);

		return newPost;
	}
	
	public void notifyFollowers(TwitterPost newPost) {
		for (User follower : this.followers) {
			follower.update(newPost);
		}
	}
	
	public void update(TwitterPost newPost) {
		this.getNewsFeed().add(newPost);
	}

	@Override
    public String toString() {
        return this.userName;
    }

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public Stage getStage() {
		return this.userStage;
	}

	public void setStage(Stage givenStage) {
		this.userStage = givenStage;
	}
	
	public Scene getScene() {
		return this.userViewScene;
	}

	public void setScene(Parent root) {
		this.userViewScene = new Scene(root, 700, 450);
	}
	
	
}
