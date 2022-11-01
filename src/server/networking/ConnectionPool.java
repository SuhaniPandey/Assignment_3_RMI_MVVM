package server.networking;

import shared.Message;
import shared.User;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool
{

  private List<ServerHandler> connections= new ArrayList<>();


  public void addConnection(ServerHandler csh){
    connections.add(csh);
  }

  public void broadcastMessage(Message msg){
    for (ServerHandler conn : connections)
    {
        conn.sendMessageToClient(msg);
    }
  }

  public void broadcastUserName(User users){
    for (ServerHandler connection : connections)
    {
      connection.sendUsers(users);
    }
  }

  public void removeConnection(ServerHandler serverSocketHandler)
  {
    connections.remove(serverSocketHandler);
  }
}
