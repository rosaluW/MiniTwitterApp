package miniTwitter;
import java.util.Random;

public abstract class TypeEntry {
	private String entryId;
	private long creationTime;
	
	public TypeEntry() {
		setEntryId(generateRandomId());
		setCreationTime(System.currentTimeMillis());
	}
	
	public void setEntryId(String givenId) {
		entryId = givenId;
	}
	
	public String getEntryId() {
		return entryId;
	}

	public void setCreationTime(long givenTime) {
		creationTime = givenTime;
	}

	public long getCreationTime() {
		return this.creationTime;
	}
	
	public String generateRandomId() {
		char[] base62Chars =
				"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		Random rand = new Random();
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < 6; i++) {
			str.append(base62Chars[rand.nextInt(62)]);
		}
		
		return str.toString();
	}
}
