package miniTwitter;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AdminPanel implements Visitor{
	private static AdminPanel adminInstance;
	private HashMap<String, User> twitterUsers = new HashMap<String, User>();
	private HashMap<String, UserGroup> twitterUserGroups = new HashMap<String, UserGroup>();
	private List<TwitterPost> twitterPosts = new ArrayList<TwitterPost>();
	private static List<String> positiveWords = new ArrayList<String>(Arrays.asList("good", "love",
				"excellent", "awesome", "amazing", "best"));
	private double totalWords = 0.0;
	private double totalPositiveWords = 0.0;

	private int userTotal = 0;
	private int groupTotal = 0;
	private int postTotal = 0;

	private TypeEntry lastUpdatedUser;
	
	private AdminPanel() {
	}
	
	public static AdminPanel getAdminInstance() {
		if (adminInstance == null) {
			adminInstance = new AdminPanel();
		}
		return adminInstance;
	}

	public void setLastUpdatedUser(TypeEntry givenUser) {
		this.lastUpdatedUser = givenUser;
	}

	public TypeEntry getLastUpdatedUser() {
		return this.lastUpdatedUser;
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

	public void visit(User user) {
		this.userTotal++;
	}

	public void visit(UserGroup userGroup) {
		this.groupTotal++;
	}
    public void visit(TwitterPost twitterPost) {
		this.postTotal++;
		String [] post = twitterPost.getPost().split("\\s+");
		for (int i = 0; i < post.length; i++) {
			if (positiveWords.contains(post[i])) {
				totalPositiveWords += 1.0;
			}
		}
		totalWords += post.length - 2;
	}

	public int getUserTotal() {
		return this.userTotal;
	}

	public int getGroupTotal() {
		return this.groupTotal;
	}

	public int getPostTotal() {
		return this.postTotal;
	}

	public double getTotalWords() {
		return this.totalWords;
	}

	public double getTotalPositiveWords() {
		return this.totalPositiveWords;
	}
}
