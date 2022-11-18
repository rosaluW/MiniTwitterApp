package miniTwitter;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    UserGroup Root = new UserGroup("Root");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        TreeItem<TypeEntry> rootItem = new TreeItem<TypeEntry>(Root);
        treeId.setRoot(rootItem);
    }

    @FXML private TreeView<TypeEntry> treeId;
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
        adminPanel.visit(newUser);
    }

    public void createNewGroup(ActionEvent event) {
        UserGroup newGroup = adminPanel.createNewGroup(groupNameTextField.getText());
        groupIdLabel.setText("Group ID: " + newGroup.getEntryId());
        adminPanel.visit(newGroup);
    }

    public void addUserToTree(ActionEvent event) {
        User user = adminPanel.getUser(userIdTextField.getText());
        TreeItem<TypeEntry> newUserItem = new TreeItem<TypeEntry>(user);
        if (selectItem() != null) {
            selectItem().getChildren().add(newUserItem);
        }
        else {
            treeId.getRoot().getChildren().add(newUserItem);
        }
    }

    public void addUserGroupToTree(ActionEvent event) {
        UserGroup userGroup = adminPanel.getUserGroup(groupIdTextField.getText());
        TreeItem<TypeEntry> newUserGroupItem = new TreeItem<TypeEntry>(userGroup);
        if (selectItem() != null) {
            selectItem().getChildren().add(newUserGroupItem);
        }
        else {
            treeId.getRoot().getChildren().add(newUserGroupItem);
        }
    }

    public void createPopUp(String givenTitle, String givenLabelTitle) {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(givenTitle);

        Label label1 = new Label(givenLabelTitle);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 200, 200);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }


    public void showTotalUsers(ActionEvent event) {
        String windowTitle, labelTitle;
        windowTitle = "User Total";
        labelTitle = "Total Users: " + adminPanel.getUserTotal();

        createPopUp(windowTitle, labelTitle);
    } 

    public void showTotalGroups(ActionEvent event) {
        String windowTitle, labelTitle;
        windowTitle = "Group Total";
        labelTitle = "Total Groups: " + adminPanel.getGroupTotal();

        createPopUp(windowTitle, labelTitle);
    } 

    public void showTotalMessages(ActionEvent event) {
        String windowTitle, labelTitle;
        windowTitle = "Total Messages";
        labelTitle = "Total Messages: " + adminPanel.getPostTotal();

        createPopUp(windowTitle, labelTitle);
    } 

    public void showPositivePercentage(ActionEvent event) {
        String windowTitle, labelTitle;
        windowTitle = "Positive Percentage";
        double positivePercent = (double)(adminPanel.getTotalPositiveWords() / adminPanel.getTotalWords()) * 100.00;
        DecimalFormat df = new DecimalFormat("#.00");
        labelTitle = "Positive Percentage: " + df.format(positivePercent) + "%";

        createPopUp(windowTitle, labelTitle);
    }

    public TreeItem<TypeEntry> selectItem() {
        TreeItem<TypeEntry> selectedItem = treeId.getSelectionModel().getSelectedItem();
        return selectedItem;
    }

    public void goToUserView(ActionEvent event) throws IOException {
        User user = (User) selectItem().getValue();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userview.fxml"));
        Parent popupRoot = loader.load();
        UserViewDriver userDriverController = loader.getController();
        userDriverController.getUserInstance(user);

        Stage popupWindow = new Stage();
        popupWindow.setTitle(user.getUserName() + "'s User View");
        user.setScene(popupRoot);
        popupWindow.setScene(user.getScene());
        user.setStage(popupWindow);
        user.getStage().showAndWait();
    } 

}
