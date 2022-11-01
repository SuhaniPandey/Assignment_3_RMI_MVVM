package client.model;

import client.networking.Client;
import shared.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class LoginImp implements LoginModel
{
  private Client client;
  private User user;
  private PropertyChangeSupport support;
  public LoginImp(Client client)
  {
    this.client=client;
    support= new PropertyChangeSupport(this);
    client.startClient();
   // client.addListener("userAdded",this::userAdded);
  }

 /* private void userAdded(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }
*/
  @Override public boolean addUser(String username, String password)
  {
    User user1= new User(username,password);
    return client.addUser(user1);
  }

  @Override public User getUser()
  {
    return user;
  }

  @Override public boolean login(User user)
  {
    boolean isloginPossible= client.login(user);
    if (isloginPossible)this.user=user;
    return isloginPossible;
  }

  @Override public List<String> getAllUsers()
  {
    return client.getUserList();
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
