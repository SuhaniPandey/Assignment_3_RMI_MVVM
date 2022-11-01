package client.networking;

import shared.Message;
import shared.User;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.util.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIClient implements Client, ClientCallBack
{

  private RMIServer server;
  private PropertyChangeSupport support;

  public RMIClient()
  {
    support= new PropertyChangeSupport(this);
  }

  public void startClient(){
    try
    {
      UnicastRemoteObject.exportObject(this,0);
      Registry registry=LocateRegistry.getRegistry("localHost",1099);
      server= (RMIServer) registry.lookup("Server");
      server.addNewMessage(this);
      server.addNewUser(this);
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<String> getUserList()
  {
    try
    {
      return server.getUserList();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public boolean addUser(User user1)
  {
    try
    {
      return server.addUser(user1);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return true;
  }

  @Override public boolean login(User user)
  {
    try
    {
      return server.login(user);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return true;
  }

  @Override public void sendMessage(Message message)
  {
    try
    {
      server.sendMessage(message);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<Message> getPreviousMessages()
  {
    try
    {
      return server.getPreviousMessages();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }



  @Override public void updateNewMessage(Message message) throws RemoteException
  {
    support.firePropertyChange("addNewMessage",null,message);
  }

  @Override public void updateNewUser(User user) throws RemoteException
  {
    support.firePropertyChange(Request.TYPE.ONLOGGEDINADDUSER.toString(),null,user);
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
