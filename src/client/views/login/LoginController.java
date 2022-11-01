package client.views.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController implements ViewController
{
  @FXML private TextField passwordtextField;
  @FXML private TextField usernameTextField;
  @FXML private Label label;

  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler= viewHandler;
    this.viewModelFactory=viewModelFactory;
    usernameTextField.textProperty().bindBidirectional(viewModelFactory.getLoginViewModel().usernameProperty());
    passwordtextField.textProperty().bindBidirectional(viewModelFactory.getLoginViewModel().passwordProperty());
    label.textProperty().bind(viewModelFactory.getLoginViewModel().labelProperty());

  }

  public void onLoginButton() throws IOException
  {
    if (viewModelFactory.getLoginViewModel().login()){
      viewHandler.openGlobalChatPage();
    }
  }

  public void onRegisterButton() throws IOException
  {
    viewHandler.openRegisterpage();
  }


}
