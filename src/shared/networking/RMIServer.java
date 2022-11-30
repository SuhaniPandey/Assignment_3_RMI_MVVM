package shared.networking;

import shared.Message;
import shared.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIServer extends Remote
{
  List<String> getUserList() throws RemoteException;
  boolean addUser(User user1) throws RemoteException;

  boolean login(User user) throws RemoteException;
  void sendMessage(Message message) throws RemoteException;

  List<Message>  getPreviousMessages() throws RemoteException;

  void addNewUser(ClientCallBack client) throws RemoteException;
  void addNewMessage(ClientCallBack client) throws RemoteException;
}
