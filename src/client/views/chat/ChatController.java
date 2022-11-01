package client.views.chat;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shared.Message;

public class ChatController implements ViewController
{
  @FXML private ListView<String> usersList;
  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;

  @FXML private ListView<Message> chatViewsIndivisualChat;
  @FXML private ListView<Message> chatViewsGlobalChat; //

  @FXML private TextField messageTextField;
  @FXML private Label onUsernameLabel;


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler=viewHandler;
    this.viewModelFactory=viewModelFactory;


    usersList.setItems(viewModelFactory.getChatViewModel().getUsers());

    chatViewsGlobalChat.setItems(viewModelFactory.getChatViewModel().getMessages());

    messageTextField.textProperty().bindBidirectional(viewModelFactory.getChatViewModel().messageTextFieldProperty());

  }


  // send button for individual button
  public void onSendButton(ActionEvent actionEvent)
  {
    viewModelFactory.getChatViewModel().sendMessage();
    messageTextField.clear();
  }

  //Global chat
  public void onBackToGlobalChat(ActionEvent actionEvent)
  {
    viewHandler.openGlobalChatPage();
  }

  //Indivisual chat

  public void onSelectUserButtonIndivisualChat(ActionEvent actionEvent)
  {

  }



  public void onBackToIndivisualChat(ActionEvent actionEvent)
  {
    //viewHandler.openIndivisualChatPage();
  }

  public void onSendGlobalButton(ActionEvent actionEvent)
  {
    viewModelFactory.getChatViewModel().sendMessage();
    messageTextField.clear();
  }

  public void onSelectUserButtonGlobalChat(ActionEvent actionEvent)
  {
  }
}
