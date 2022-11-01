package shared.networking;

import shared.Message;
import shared.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBack extends Remote
{
  void updateNewMessage(Message message) throws RemoteException;
  void updateNewUser(User user) throws RemoteException;
}
