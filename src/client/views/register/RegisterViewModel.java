package client.views.register;

import client.model.LoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegisterViewModel
{
  private StringProperty usernameRegister,passswordRegister,reenterRegister,label;
  private LoginModel loginModel;

  public RegisterViewModel(LoginModel loginModel){
    this.loginModel=loginModel;
    usernameRegister= new SimpleStringProperty();
    passswordRegister= new SimpleStringProperty();
    reenterRegister= new SimpleStringProperty();
    label= new SimpleStringProperty();
  }

  public StringProperty usernameRegisterProperty()
  {
    return usernameRegister;
  }

  public StringProperty passswordRegisterProperty()
  {
    return passswordRegister;
  }

  public StringProperty reenterRegisterProperty()
  {
    return reenterRegister;
  }

  public StringProperty labelProperty()
  {
    return label;
  }

  public void onRegister(String username, String password, String re_enter)
  {
    if (username == null)
    {
      label.set("Username is empty");
    }
    else if (!(password.equals(re_enter)))
    {
      label.set("Password do not match");
    }
    else
    {
      if (loginModel.addUser(username, password))
      {
        {
          label.set("Account Successfully Created");
        }
      }
    }
  }
}
