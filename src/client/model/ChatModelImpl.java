package client.model;

import client.networking.Client;
import shared.Message;
import shared.util.Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ChatModelImpl implements ChatModel
{
  private Client client;
  private PropertyChangeSupport support;

  public ChatModelImpl(Client client){
    this.client=client;
    support= new PropertyChangeSupport(this);
    client.addListener("addNewMessage",this::addMessage);
    client.addListener(Request.TYPE.ONLOGGEDINADDUSER.toString(),this::useradded);

  }

  private void useradded(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  private void addMessage(PropertyChangeEvent event)
  {
    Message message= (Message) event.getNewValue();
    support.firePropertyChange("addMessage",null,message);
    //System.out.println(message + ": chat model impl");
    // support.firePropertyChange(event);
  }

  @Override public List<String> getUsersname()
  {
    return client.getUserList();

  }

  @Override public List<Message> getMessages()
  {
    return client.getPreviousMessages();
  }

  @Override public void sendMessage(Message message)
  {
    client.sendMessage(message);
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}
