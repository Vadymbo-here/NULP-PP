import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userclass.User;
import utils.JsonWorker;

public class CreateDepController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField percField;
    @FXML
    private TextField descrpField;
    @FXML
    private ChoiceBox<String> MyChoiceBox;
    @FXML
    private Label infoLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    String[] ListItems;
    String loginValue, passValue, name1Value, name2Value, currListItem;

    public void startCreation(ActionEvent event) throws IOException {
        infoLabel.setText("");
        if (checkFields()) {
            infoLabel.setText("No empty fields allowed!");
            return;
        }
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        User user = (User) stage.getUserData();
        int type = 0;
        float perc = 0.0f;
        String description = descrpField.getText(),
                name = nameField.getText(),
                percentage = percField.getText();

        String tmpChoice = MyChoiceBox.getValue();
        if (tmpChoice.equals("With Capatalization")) {
            type = 1;
        }
        try {
            perc = Float.parseFloat(percentage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int depid = JsonWorker.getFreeSlot("depcases", "depID");
        JsonWorker.writeDepCase(user.getBankID(), depid, name, description, type, perc);
        infoLabel.setText("Deposit was created!");
        nameField.setText("");
        percField.setText("");
        descrpField.setText("");
    }

    private boolean checkFields() {
        return nameField.getText().isBlank() || percField.getText().isBlank() || descrpField.getText().isBlank();
    }

    public void cancel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(ListController.class.getResource("javaFX/ListScene.fxml"));
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        User tmp = (User) stage.getUserData();
        stage.setUserData(tmp);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String[] choiceVar = { "With Capatalization", "Without Capatalization" };

        MyChoiceBox.getItems().setAll(choiceVar);

    }
}
