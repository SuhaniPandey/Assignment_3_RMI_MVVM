package server.networking;

import server.model.ChatHandlerImpl;
import server.model.LoginHandler;

public class RunServer
{
  public static void main(String[] args)
  {
     SocketServer ss= new SocketServer(new LoginHandler(),new ChatHandlerImpl());
     ss.startServer();
  }
}
