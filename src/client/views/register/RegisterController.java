package client.views.register;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController implements ViewController
{
  
  @FXML private TextField usernameRegister;
  @FXML private TextField passswordRegister;
  @FXML private TextField reenterRegister;
  @FXML private Label label;

  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler= viewHandler;
    this.viewModelFactory=viewModelFactory;
    usernameRegister.textProperty().bindBidirectional(viewModelFactory.getRegisterViewModel().usernameRegisterProperty());
    passswordRegister.textProperty().bindBidirectional(viewModelFactory.getRegisterViewModel().passswordRegisterProperty());
    reenterRegister.textProperty().bindBidirectional(viewModelFactory.getRegisterViewModel().reenterRegisterProperty());
    label.textProperty().bind(viewModelFactory.getRegisterViewModel().labelProperty());

  }


  public void onSignUpButton(ActionEvent actionEvent)
  {
    viewModelFactory.getRegisterViewModel().onRegister(usernameRegister.getText(),passswordRegister.getText(),reenterRegister.getText());
    usernameRegister.clear();
    passswordRegister.clear();
    reenterRegister.clear();
  }

  public void onAlreadyHaveAnAccountButton()
  {
    viewHandler.openLoginPage();
  }


}
