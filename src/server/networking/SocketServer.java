package server.networking;

import server.model.ChatHandler;
import server.model.Login;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
  private Login login;
  private ChatHandler chatHandler;

  public SocketServer(Login login,ChatHandler chatHandler){
    this.login=login;
    this.chatHandler=chatHandler;
  }

  public void startServer(){
    try
    {
      ServerSocket socket= new ServerSocket(2001);
      System.out.println("Server starting...");
      ConnectionPool pool= new ConnectionPool();
      while (true){
        Socket socket1= socket.accept();
        ServerHandler serverHandler= new ServerHandler(socket1,login,pool,chatHandler);
        Thread th= new Thread(serverHandler);
        th.start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
