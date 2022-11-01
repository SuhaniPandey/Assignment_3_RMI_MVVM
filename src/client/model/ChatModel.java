package client.model;

import shared.Message;
import shared.util.Subject;

import java.util.List;

public interface ChatModel extends Subject
{
  List<String> getUsersname();

  List<Message> getMessages();
  void sendMessage(Message message);


}
