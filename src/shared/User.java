package shared;

import java.io.Serializable;

public class User implements Serializable
{

  private String userName;
  private String password;

  public User(String userName, String password)
  {
    this.userName = userName;
    this.password = password;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getPassword()
  {
    return password;
  }

  public boolean equals(Object obj){
    if (!(obj instanceof User user)){
      return false;
    }
    return user.getUserName().equals(userName) && user.getPassword().equals(password);
  }
}
