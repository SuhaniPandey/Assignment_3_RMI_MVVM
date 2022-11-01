package client.views.chat;

import client.model.ChatModel;
import client.model.LoginModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Message;
import shared.User;
import shared.util.Request;

import java.beans.PropertyChangeEvent;

public class ChatViewModel
{
  private ChatModel chatModel;
  private LoginModel loginModel;
  private ObservableList<String> users;
  private ObservableList<Message> chatViewsIndivisualChat;
  private ObservableList<Message> chatViewsGlobalChat;
  private StringProperty messageTextField;
  private StringProperty usernameLabel;

  public ChatViewModel(ChatModel chatModel, LoginModel loginModel)
  {
    this.chatModel = chatModel;
    this.loginModel=loginModel;
    messageTextField = new SimpleStringProperty();
    users = FXCollections.observableArrayList(chatModel.getUsersname());
    //usernameLabel.setValue(loginModel.getUser().getUserName());

    chatViewsGlobalChat = FXCollections.observableArrayList(
        chatModel.getMessages());

    chatModel.addListener(Request.TYPE.ONLOGGEDINADDUSER.toString(),
        this::userAdded);
    chatModel.addListener("addMessage", this::addMessage);
  }

  public StringProperty usernameLabelProperty()
  {
    return usernameLabel;
  }

  public StringProperty messageTextFieldProperty()
  {
    return messageTextField;
  }

  private void addMessage(PropertyChangeEvent event)
  {
    System.out.println(event.getNewValue() + ": chat view model");
    Platform.runLater(
        () -> chatViewsGlobalChat.add((Message) event.getNewValue()));
  }

  private void userAdded(PropertyChangeEvent event)
  {
    Platform.runLater(
        () -> users.add(((User) event.getNewValue()).getUserName()));
  }

  public ObservableList<String> getUsers()
  {
    System.out.println(users + "");
    return users;

  }

  public void sendMessage()
  {
    chatModel.sendMessage(new Message(loginModel.getUser().getUserName(), messageTextField.getValue()));
    //System.out.println(messageTextField.getValue());
  }

  public ObservableList<Message> getMessages()
  {
    return chatViewsGlobalChat;
  }
}
