package rgr1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginConroller {

    @FXML
    private TextField loginTxtField;
    @FXML
    private TextField signUpTxtField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button signUpBtn;

    String loginValue, signValue;

    public void startLogin(ActionEvent event) {
        loginValue = loginTxtField.getText();
        signValue = signUpTxtField.getText();

        System.out.printf("\n\tLogin: %s\n\tPassword: %s", loginValue, signValue);
    }

    public void startSignUp(ActionEvent event) {
        loginValue = loginTxtField.getText();
        signValue = signUpTxtField.getText();

        System.out.printf("\n\tLogin: %s\n\tPassword: %s", loginValue, signValue);
    }
}
