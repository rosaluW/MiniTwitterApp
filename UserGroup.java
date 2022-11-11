package miniTwitter;

import java.util.ArrayList;
import java.util.List;

public class UserGroup extends TypeEntry {
	private String userGroupName;
	private List<TypeEntry> entryList;
	
	public UserGroup(String givenGroupName) {
		super();
		this.setUserGroupName(givenGroupName);
		entryList = new ArrayList<TypeEntry>();
	}
	
	public String getUserGroupName() {
		return this.userGroupName;
	}
	
	public void setUserGroupName(String givenGroupName) {
		this.userGroupName = givenGroupName;
	}
	
	public List<TypeEntry> getEntryList() {
		return this.entryList;
	}
	
	public void displayUserGroup() {
		for(int i = 0; i < this.entryList.size(); i++) {
			if (this.entryList.get(i) instanceof User)
				System.out.println("- " + ((User)this.entryList.get(i)).getUserName());
			else {
				System.out.println("   " + ((UserGroup)this.entryList.get(i)).getUserGroupName());
				((UserGroup)this.entryList.get(i)).displayUserGroup();
			}
		}
	}
	
	public void add(TypeEntry newEntry) {
		entryList.add(newEntry);
	}
}
