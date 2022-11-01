package client.core;

import client.views.chat.ChatController;
import client.views.login.LoginController;
import client.views.register.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private Scene loginScene, registerScene,globalChatScene,indivisualChatScene;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(ViewModelFactory viewModelFactory){
    this.viewModelFactory=viewModelFactory;
  }

  public void start() throws IOException
  {
    stage= new Stage();
     openRegisterpage();
  }

  public void openLoginPage(){
    FXMLLoader loader= new FXMLLoader();

    Parent root= loadFXMLFiles("/client/views/login/Login.fxml",loader);
    LoginController loginController= loader.getController();
    loginController.init(this, viewModelFactory);

    loginScene= new Scene(root);
    stage.setTitle("Login");
    stage.setResizable(false);
    stage.setScene(loginScene);
    stage.show();
  }

  public void openRegisterpage() throws IOException
  {


  FXMLLoader loader= new FXMLLoader();
    Parent root= loadFXMLFiles("/client/views/register/Register.fxml",loader);

    RegisterController registerController= loader.getController();
    registerController.init(this,viewModelFactory);

    registerScene= new Scene(root);
    stage.setTitle("Register");
    stage.setResizable(false);
    stage.setScene(registerScene);
    stage.show();
  }

  public void openGlobalChatPage(){
    FXMLLoader loader= new FXMLLoader();
    Parent root= loadFXMLFiles("/client/views/chat/Chat.fxml",loader);

    ChatController chatController = loader.getController();
    chatController.init(this,viewModelFactory);

    globalChatScene= new Scene(root);
    stage.setTitle("Global Chat");
    stage.setResizable(false);
    stage.setScene(globalChatScene);
    stage.show();
  }

  public void openIndivisualChatPage(){
    FXMLLoader loader= new FXMLLoader();
    Parent root= loadFXMLFiles("/client/views/chat/IndivisualChat.fxml",loader);

    ChatController chatController= loader.getController();
    chatController.init(this,viewModelFactory);

    indivisualChatScene= new Scene(root);
    stage.setTitle("Indivisual Chat");
    stage.setResizable(false);
    stage.setScene(indivisualChatScene);
    stage.show();
  }
  private Parent loadFXMLFiles(String path, FXMLLoader loader)
  {
    loader.setLocation(getClass().getResource(path));
    Parent root = null;
    try
    {
      // loading fxml files in parent instance, containing all GUI elements
      root = loader.load();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return root;
  }

//  public void openLoginPage()
//  {
//    if (loginScene== null){
//      try
//      {
//        Parent root= loadFXML("client/views/Login/Login.fxml");
//        loginScene = new Scene(root);
//        stage.setTitle("Login");
//        stage.setResizable(false);
//      }
//      catch (IOException e)
//      {
//        e.printStackTrace();
//      }
//      stage.setScene(loginScene);
//      stage.show();
//    }
//  }
//
//  public void openRegisterPage(){
//    if (registerScene== null){
//      try
//      {
//        Parent root= loadFXML("client/views/Register/Register.fxml");
//        registerScene = new Scene(root);
//        stage.setTitle("Register");
//        stage.setResizable(false);
//      }
//      catch (IOException e)
//      {
//        e.printStackTrace();
//      }
//      stage.setScene(registerScene);
//      stage.show();
//    }
//  }
//
//  private Parent loadFXML(String path) throws IOException {
//    FXMLLoader loader = new FXMLLoader();
//    loader.setLocation(getClass().getResource(path));
//    Parent root = loader.load();
//
//    ViewController ctrl = loader.getController();
//    ctrl.init(this, viewModelFactory);
//    return root;
//  }
}
