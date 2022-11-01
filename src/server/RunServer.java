package server;

import server.model.ChatHandlerImpl;
import server.model.LoginHandler;
import server.networking.RMIServerImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    RMIServerImpl ss= new RMIServerImpl(new ChatHandlerImpl(),new LoginHandler());
    ss.startServer();
  }
}
