package miniTwitter;

import java.util.ArrayList;
import java.util.List;

public class UserGroup extends TypeEntry implements Visitable {
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
	
	public void add(TypeEntry newEntry) {
		entryList.add(newEntry);
	}

	@Override
    public String toString() {
        return this.userGroupName;
    }

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
