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

public class CalcController implements Initializable {
    @FXML
    private ListView<String> profitListView;
    @FXML
    private TextField periodTxtField;
    @FXML
    private TextField balanceTxtField;
    @FXML
    private Button createDepBtn;

    private User user;
    private Stage stage;
    private Scene scene;
    private Parent root;

    String[] ListItems, currListItem = { "" };
    String loginValue, passValue, name1Value, name2Value;

    public void loadList(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        user = (User) stage.getUserData();
        System.out.println(user.getUserID());
        node.toBack();
        myInit(user);
    }

    public void myInit(User user) {
        // Clear old data
        profitListView.getItems().clear();

        // Init input data
        String balance = balanceTxtField.getText();
        String period = periodTxtField.getText();

        // show deals {balance} {period_in_da}
        // Init arr for List
        String[] input = { "show", "deals", balance, period };
        String[] tmp = user.ShowDealsToArr(input);
        for (String string : tmp) {
            if (string != null) {
                profitListView.getItems().add(string);
            }
        }
    }

    public void startCalculation(ActionEvent event) throws IOException {
        myInit(user);
    }

    public void createDep(ActionEvent event) throws IOException {
        // create dep {BankID} {DepID} {balance} {period_in_days}
        System.out.println("Start creating dep");
        System.out.println(currListItem[1]);
        String[] input = { "create", "dep", currListItem[2], currListItem[1], "100", "10" };
        System.out.println(user.CreateDepCase(input));
        System.out.println("Finish");
    }

    public void exitFromCalc(ActionEvent event) throws IOException {
        root = FXMLLoader.load(CalcController.class.getResource("javaFX/ListScene.fxml"));
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setUserData(user);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        createDepBtn.setDisable(true);
        profitListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                currListItem = profitListView.getSelectionModel().getSelectedItem()
                        .split("[a-zA-Z\\[\\n\\t:\\] =]+");
                // for (int i = 0; i < currListItem.length; i++) {
                // System.out.printf("%d -> %s\n", i, currListItem[i]);
                // }
                System.out.println(currListItem[1]);
                System.out.println(currListItem[2]);
                createDepBtn.setDisable(false);
                // бАГ в читання через реджекс, там золота2 робить збій
            }
        });
    }
}
