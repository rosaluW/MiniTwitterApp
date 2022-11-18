package miniTwitter;

public interface Visitor {
    public void visit(User user);
    public void visit(UserGroup userGroup);
    public void visit(TwitterPost twitterPost);
}
