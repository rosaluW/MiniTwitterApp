package miniTwitter;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        newsFeedListView.getItems().add(userInstance.getUserName() + " posted: " + newPost.getPost());
    }



    
}
