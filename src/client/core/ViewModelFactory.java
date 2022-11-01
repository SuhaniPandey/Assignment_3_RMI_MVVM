package client.core;

import client.views.chat.ChatViewModel;
import client.views.login.LoginViewModel;
import client.views.register.RegisterViewModel;

public class ViewModelFactory
{

  private ModelFactory modelFactory;
  private LoginViewModel loginViewModel;
  private RegisterViewModel registerViewModel;
  private ChatViewModel chatViewModel;

  public ViewModelFactory(ModelFactory modelFactory){
    this.modelFactory=modelFactory;
  }

  public LoginViewModel getLoginViewModel(){
    if (loginViewModel== null){
      loginViewModel= new LoginViewModel(modelFactory.getLoginModel());
    }
    return loginViewModel;
}

  public RegisterViewModel getRegisterViewModel(){
    if (registerViewModel== null){
      registerViewModel= new RegisterViewModel(modelFactory.getLoginModel());
    }
    return registerViewModel;
  }

  public ChatViewModel getChatViewModel(){
    if (chatViewModel== null){
      chatViewModel= new ChatViewModel(modelFactory.getChatModel(),
          modelFactory.getLoginModel());
    }
    return chatViewModel;
  }
}
