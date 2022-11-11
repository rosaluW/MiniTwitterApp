package miniTwitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminPanel {
	private static AdminPanel adminInstance;
	private HashMap<String, User> twitterUsers = new HashMap<String, User>();
	private HashMap<String, UserGroup> twitterUserGroups = new HashMap<String, UserGroup>();
	private List<TwitterPost> twitterPosts = new ArrayList<TwitterPost>();
	
	private AdminPanel() {
	}
	
	public static AdminPanel getAdminInstance() {
		if (adminInstance == null) {
			adminInstance = new AdminPanel();
		}
		return adminInstance;
	}

	public User getUser(String givenUserId) {
		return twitterUsers.get(givenUserId);
	}

	public UserGroup getUserGroup(String givenGroupId) {
		return twitterUserGroups.get(givenGroupId);
	}

	public List<TwitterPost> getTwitterPosts() {
		return twitterPosts;
	}
	
	public void addGroup(UserGroup originalGroup, String givenGroupId) {
		originalGroup.add(twitterUserGroups.get(givenGroupId));
	}
	
	public void addUser(UserGroup originalGroup, String givenUserId) {
		originalGroup.add(twitterUsers.get(givenUserId));
	}
	
	public User createNewUser(String givenUserName) {
		User newUser = new User(givenUserName);
		twitterUsers.put(newUser.getEntryId(), newUser);

		return newUser;
	}
	
	public UserGroup createNewGroup(String givenGroupName) {
		UserGroup newGroup = new UserGroup(givenGroupName);
		twitterUserGroups.put(newGroup.getEntryId(), newGroup);

		return newGroup;
	}
	
	public int getTotalUsers() {
		return this.twitterUsers.size();
	}
	
	public int getTotalGroups() {
		return this.twitterUserGroups.size();
	}
}
