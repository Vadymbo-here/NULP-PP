import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userclass.User;
import utils.JsonWorker;

public class ListController implements Initializable {
    @FXML
    private ListView<String> mainListView;
    @FXML
    private TextField mainField;
    @FXML
    private Button calcButton;
    @FXML
    private Button mainEnter;

    private User user;
    private Stage stage;
    private Scene scene;
    private Parent root;

    String[] ListItems, currListItem = { "" };

    public void startCalcuation(ActionEvent event) throws IOException {
        root = FXMLLoader.load(ListController.class.getResource("javaFX/calculatorScene.fxml"));
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        User tmp = (User) stage.getUserData();
        stage.setUserData(tmp);
        // DELETE
        System.out.println(user.getUserID());

        stage.setScene(scene);
        stage.show();
    }

    public void addBalanceToDep(ActionEvent event) {
        mainField.setDisable(false);
        mainEnter.setDisable(false);
    }

    public void deleteDep(ActionEvent event) throws IOException {
        int bankID = 0, depID = 0;
        try {
            bankID = Integer.parseInt(currListItem[6]);
            depID = Integer.parseInt(currListItem[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonWorker.delDeposit(user.getUserID(), bankID, depID);
        myInit(user);
    }

    public void finishEntering() throws IOException {
        if (currListItem.length > 1) {

            String newValue = mainField.getText();
            int depID = 0, bankID = 0;
            try {
                bankID = Integer.parseInt(currListItem[6]);
                depID = Integer.parseInt(currListItem[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (newValue.matches("[0-9.]+") && currListItem.length > 1) {
                JsonWorker.updateDeposit(user.getUserID(), bankID, depID, newValue);
                for (int i = 0; i < currListItem.length; i++) {
                    System.out.printf("[%d] -> %s\n", i, currListItem[i]);
                }
                System.out.println("true");
            } else {
                System.out.println("false");
            }

            mainField.setDisable(true);
            mainEnter.setDisable(true);

            myInit(user);
        }
    }

    public void loadList(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        user = (User) stage.getUserData();
        node.toBack();
        calcButton.setDisable(false);
        myInit(user);
    }

    public void myInit(User user) {
        // Clear old data
        mainListView.getItems().clear();

        // Init arr for List
        String[] tmp = JsonWorker.printAllDepositsArray(user.getUserID());
        for (String string : tmp) {
            if (string != null) {
                mainListView.getItems().add(string);
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        mainListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (mainField.isDisabled()) {
                    currListItem = mainListView.getSelectionModel().getSelectedItem().split("[a-zA-Z\\[\\n\\t:\\] =]+");
                }
            }
        });

        calcButton.setDisable(true);
        mainField.setDisable(true);
        mainEnter.setDisable(true);
    }
}
