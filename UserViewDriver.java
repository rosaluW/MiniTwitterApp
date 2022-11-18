package miniTwitter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class UserViewDriver implements Initializable {
    @FXML private TitledPane userViewTable;
    @FXML private TextField userIdText;
    @FXML private Button followButton;
    @FXML private ListView<String> followingsListView;
    @FXML private TextArea twitterPostTextArea;
    @FXML private Button postTweetButton;
    @FXML private ListView<String> newsFeedListView;

    User userInstance;
    AdminPanel adminPanel = AdminPanel.getAdminInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        followingsListView.getItems().add("Followings: ");    
        newsFeedListView.getItems().add("News Feed: ");    
    }

    public void getUserInstance(User givenUser) {
        userInstance = givenUser;

        for (int i = 0; i < userInstance.getFollowings().size(); i++) {
            followingsListView.getItems().add(userInstance.getFollowings().get(i).getUserName());
        }

        for (int i = 0; i < userInstance.getNewsFeed().size(); i++) {
            newsFeedListView.getItems().add(userInstance.getNewsFeed().get(i).getPost());
        }
    }

    public void followUser(ActionEvent event) {
        userInstance.startFollowing(userIdText.getText());
        followingsListView.getItems().add(adminPanel.getUser(userIdText.getText()).getUserName());
    }

    public void postTweet() {
        TwitterPost newPost = userInstance.createPost(twitterPostTextArea.getText());
        newsFeedListView.getItems().add(newPost.getPost());
        adminPanel.visit(newPost);
    }


    
}
