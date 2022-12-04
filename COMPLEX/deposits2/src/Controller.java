import java.io.IOException;

import javaFX.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userclass.User;
import utils.JsonWorker;

public class Controller {
    @FXML
    private TextField loginTxtField;
    @FXML
    private TextField passwordTxtField;
    @FXML
    private TextField firstNameTxtField;
    @FXML
    private TextField surnameTxtField;
    @FXML
    private TextField periodTxtField;
    @FXML
    private TextField balanceTxtField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button signUpBtn;
    @FXML
    private Label infoLabel;
    // @FXML
    // private ListView<String> profitListView;

    // private int CurUserID;
    private Stage stage;
    private Scene scene;
    private Parent root;

    String[] ListItems;
    String loginValue, passValue, name1Value, name2Value, currListItem;

    public void startLogin(ActionEvent event) throws IOException {
        loginValue = loginTxtField.getText();
        passValue = passwordTxtField.getText();

        User user = JsonWorker.loginUser(loginValue, passValue);
        if (user.getUserID() == 0) {
            System.out.println("Failed///");
            infoLabel.setText("Wrong input data. Check spelling");
        } else {
            System.out.println("Passed!!!");
            infoLabel.setText("");

            root = FXMLLoader.load(App.class.getResource("./ListScene.fxml"));
            stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            scene = new Scene(root);
            stage.setUserData(user);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void startSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(App.class.getResource("./signUpScene.fxml"));
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void finishSignUp(ActionEvent event) throws IOException {
        name1Value = firstNameTxtField.getText();
        name2Value = surnameTxtField.getText();
        loginValue = loginTxtField.getText();
        passValue = passwordTxtField.getText();

        if (name1Value.isEmpty() || name2Value.isEmpty() || loginValue.isEmpty() || passValue.isEmpty()) {
            infoLabel.setText("No empty fields allowed!");
        } else {
            JsonWorker.registerUser(name1Value, name2Value, loginValue, passValue);

            User user = JsonWorker.loginUser(loginValue, passValue);
            root = FXMLLoader.load(App.class.getResource("./ListScene.fxml"));
            stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            scene = new Scene(root);
            stage.setUserData(user);
            // DELETE
            System.out.println(user.getUserID());

            stage.setScene(scene);
            stage.show();
        }
    }
}
