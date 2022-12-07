package miniTwitter;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML private Label userIdCreateTimeLabel;

    User userInstance;
    AdminPanel adminPanel = AdminPanel.getAdminInstance();
    Calendar calendar = Calendar.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        followingsListView.getItems().add("Followings: ");    
        newsFeedListView.getItems().add("News Feed: ");    
    }

    public void getUserInstance(User givenUser) {
        userInstance = givenUser;

        calendar.setTimeInMillis(userInstance.getCreationTime());
        String timeStamp = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

        userIdCreateTimeLabel.setText("My User ID: " + userInstance.getEntryId() + "    Creation Time: " + timeStamp);

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
        adminPanel.setLastUpdatedUser(userInstance);

        calendar.setTimeInMillis(newPost.getTweetCreationTime());
        String timeStamp = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

        newsFeedListView.getItems().add(timeStamp + " " + newPost.getPost());
        adminPanel.visit(newPost);
    }


    
}
