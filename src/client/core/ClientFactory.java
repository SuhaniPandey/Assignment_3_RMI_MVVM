package client.core;

import client.networking.Client;
import client.networking.ClientSocket;

public class ClientFactory
{
  private Client client;

  public Client getClient()
  {
    if (client== null){
      client= new ClientSocket();
    }
    return client;
  }
}
