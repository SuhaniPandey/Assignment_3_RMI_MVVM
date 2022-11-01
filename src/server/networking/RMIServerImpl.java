package server.networking;

import server.model.ChatHandler;
import server.model.Login;
import shared.Message;
import shared.User;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.util.Request;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIServerImpl implements RMIServer
{

  private ChatHandler chatHandler;
  private Login login;

  public RMIServerImpl(ChatHandler chatHandler, Login login)
      throws RemoteException
  {
    UnicastRemoteObject.exportObject(this,0);
    this.chatHandler = chatHandler;
    this.login = login;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry= LocateRegistry.createRegistry(1099);
    registry.bind("Server",this);
  }

  @Override public List<String> getUserList() throws RemoteException
  {
    return login.getAllUsers();
  }

  @Override public boolean addUser(User user1) throws RemoteException
  {
    return login.addUser(user1);
  }

  @Override public boolean login(User user) throws RemoteException
  {
    if (login.addUser(user)){
      return true;
    }
    return false;
  }

  @Override public void sendMessage(Message message) throws RemoteException
  {
    chatHandler.addMessages(message);
  }

  @Override public List<Message> getPreviousMessages() throws RemoteException
  {
    return chatHandler.getPreviousMessage();
  }

  @Override public void addNewUser(ClientCallBack client) throws RemoteException
  {
    PropertyChangeListener listener=null;
    listener=evt -> {
      try
      {
        client.updateNewUser((User) evt.getNewValue());
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    };
    login.addListener(Request.TYPE.ONLOGGEDINADDUSER.toString(),
        listener);
  }

  @Override public void addNewMessage(ClientCallBack client)
      throws RemoteException
  {
    PropertyChangeListener listener=null;
    listener= evt -> {
      try
      {
        client.updateNewMessage((Message) evt.getNewValue());
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    };
    chatHandler.addListener("addNewMessage",listener);
  }
}
