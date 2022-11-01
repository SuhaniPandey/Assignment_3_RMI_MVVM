package client.networking;

import shared.Message;
import shared.User;
import shared.util.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientSocket implements Client
{
  private PropertyChangeSupport support;
  private User user;

  public ClientSocket()
  {
    support = new PropertyChangeSupport(this);
  }

 /* @Override public void listenToServer(User user)
  {
    try
    {
      Socket socket = new Socket("localhost", 2001);
      ObjectOutputStream outputStream = new ObjectOutputStream(
          socket.getOutputStream());
      ObjectInputStream inputStream = new ObjectInputStream(
          socket.getInputStream());

      Thread th = new Thread(
          () -> listenToServer(inputStream, outputStream, user));
      th.start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }*/

  @Override public List<String> getUserList()
  {
    Request response = request(null, Request.TYPE.USERLIST.toString());
    return (List<String>) response.getArg();
  }

  @Override public boolean addUser(User user1)
  {
    Request response = request(user1, Request.TYPE.ADDUSER.toString());
    assert response != null;
    return (boolean) response.getArg();
  }

  @Override public boolean login(User user)
  {
    try
    {
      Request response = request(user, Request.TYPE.LOGINPOSSIBLE.toString());
      assert response != null;
      boolean isPossible = (boolean) response.getArg();
      if (isPossible)
      {
        //listenToServer(user);
        //not sure............................
        support.firePropertyChange("userAdded",null,user);
      }
      return isPossible;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }

  /*@Override public List<Message> getMessages()
  {
    Request response= request(null,"getMessages");
    return (List<Message>) response.getArg();
  }
*/
  @Override public void sendMessage(Message message)
  {
    try
    {
      //----------------------------
      Request response= request(message,"sendMessage");
      //System.out.println(response.getArg() + " : send message in client socket");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<Message> getPreviousMessages()
  {
    Request response = request(null,"getPreviousMessages");
    return (List<Message>) response.getArg();
  }

  @Override public void startClient()
  {
    this.user=user;
    try
    {
      Socket socket = new Socket("localhost", 2001);
      ObjectOutputStream outputStream = new ObjectOutputStream(
          socket.getOutputStream());
      ObjectInputStream inputStream = new ObjectInputStream(
          socket.getInputStream());

      Thread th = new Thread(
          () -> listenToServer(inputStream, outputStream));
      th.start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  private Request request(Object arg, String type)
  {
    try
    {
      Socket socket = new Socket("localhost", 2001);
      ObjectOutputStream outputStream = new ObjectOutputStream(
          socket.getOutputStream());
      ObjectInputStream inputStream = new ObjectInputStream(
          socket.getInputStream());
      outputStream.writeObject(new Request(type, arg));
      return (Request) inputStream.readObject();
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  private void listenToServer(ObjectInputStream inputStream,
      ObjectOutputStream outputStream)
  {
    try
    {
      outputStream.writeObject(new Request("Listener", user));
      while (true)
      {
        Request response = (Request) inputStream.readObject();
        support.firePropertyChange(response.getType(),null,response.getArg());
        /*if(response.getType().equals("addMessage")){
          support.firePropertyChange("addMessage",null,response.getArg());
        }
        else if (response.getType().equals(Request.TYPE.ONLOGGEDINADDUSER.toString())){
          support.firePropertyChange(Request.TYPE.ONLOGGEDINADDUSER.toString(),null,response.getArg());
        }*/
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
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
