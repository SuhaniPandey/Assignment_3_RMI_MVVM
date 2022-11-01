package client.views.login;

import client.model.LoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.User;

public class LoginViewModel
{
  private StringProperty username,password,label;
  private LoginModel loginModel;

  public LoginViewModel(LoginModel loginModel)
  {
    this.loginModel=loginModel;
    username= new SimpleStringProperty();
    password= new SimpleStringProperty();
    label= new SimpleStringProperty();
  }

  public StringProperty usernameProperty()
  {
    return username;
  }

  public StringProperty passwordProperty()
  {
    return password;
  }

  public StringProperty labelProperty()
  {
    return label;
  }

  public boolean login(){
    User user= new User(username.get(),password.get());//.get

   if (!loginModel.login(user)){
      label.set("Username or password is incorrect");
    return false;
    }
    return true;
  }
}
