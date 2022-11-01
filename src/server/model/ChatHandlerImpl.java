package server.model;

import shared.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ChatHandlerImpl implements ChatHandler
{
  private List<Message> messages;
  private PropertyChangeSupport support;

  public ChatHandlerImpl(){
    messages= new ArrayList<>();
    support= new PropertyChangeSupport(this);
  }

  @Override public List<Message> getMessages()
  {
    return messages;
  }

  @Override public void addMessages(Message message)
  {
    messages.add(message);
    //System.out.println(support.hasListeners("addNewMessage") + " :from chat handler");
    support.firePropertyChange("addNewMessage",null,message);
  }

  @Override public List<Message> getPreviousMessage()
  {
    return messages;
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName,listener);
  }

}
