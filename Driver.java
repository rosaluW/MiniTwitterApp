package miniTwitter;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.URL;
import java.util.ResourceBundle;

import miniTwitter.AdminPanel;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Driver implements Initializable {
    AdminPanel adminPanel = AdminPanel.getAdminInstance();
    UserGroup Root = new UserGroup("root");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        TreeItem<String> rootItem = new TreeItem<String>("Root");
        treeId.setRoot(rootItem);
    }

    @FXML private TreeView<String> treeId;
    @FXML private TextField userIdTextField;
    @FXML private Button addUserButton;
    @FXML private TextField groupIdTextField;
    @FXML private Button addGroupButton;

    @FXML private TextField userIdToView;
    @FXML private Button userViewButton;

    @FXML private TextField userNameTextField;
    @FXML private Button createUserButton;
    @FXML private Label userIdLabel;

    @FXML private TextField groupNameTextField;
    @FXML private Button createGroupButton;
    @FXML private Label groupIdLabel;

    public void createNewUser(ActionEvent event) {
        User newUser = adminPanel.createNewUser(userNameTextField.getText());
        userIdLabel.setText("User ID: " + newUser.getEntryId());
    }

    public void createNewGroup(ActionEvent event) {
        UserGroup newGroup = adminPanel.createNewGroup(groupNameTextField.getText());
        groupIdLabel.setText("Group ID: " + newGroup.getEntryId());
    }

    public void addUserToTree(ActionEvent event) {
        User user = adminPanel.getUser(userIdTextField.getText());
        TreeItem<String> newUserItem = new TreeItem<String>(user.getUserName());
        treeId.getRoot().getChildren().add(newUserItem);
    }

    public void addUserGroupToTree(ActionEvent event) {
        UserGroup userGroup = adminPanel.getUserGroup(groupIdTextField.getText());
        TreeItem<String> newUserGroupItem = new TreeItem<String>(userGroup.getUserGroupName());
        treeId.getRoot().getChildren().add(newUserGroupItem);
    }

    public void showTotalUsers(ActionEvent event) {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("This is a pop up window");
      
        Label label1= new Label("Total Users: " + adminPanel.getTotalUsers());
          
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 200, 200);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    } 

    public void showTotalGroups(ActionEvent event) {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("This is a pop up window");
      
        Label label1= new Label("Total Groups: " + adminPanel.getTotalGroups());
          
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 200, 200);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    } 

    public void showTotalMessages(ActionEvent event) {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("This is a pop up window");
      
        Label label1= new Label("Total Messages: " + adminPanel.getTwitterPosts().size());
          
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 200, 200);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    } 

    public void goToUserView(ActionEvent event) throws IOException {
        User user = adminPanel.getUser(userIdToView.getText());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("userview.fxml"));
        Parent popupRoot = loader.load();
        UserViewDriver userDriver = loader.getController();
        userDriver.getUserInstance(user);

        Stage popupwindow=new Stage();
        popupwindow.setTitle("User View");
        
        popupwindow.setScene(new Scene(popupRoot, 700, 450));
        popupwindow.showAndWait();
    } 

}
