package shared;

import java.util.ArrayList;

public class ListOfUsers
{

  private ArrayList<User> users;

  public ListOfUsers()
  {
    users= new ArrayList<>();
  }

  public void addUser(User user){
    users.add(user);
  }

  public void removeUser(User user){
    users.remove(user);
  }

  public ArrayList<String> userNames(){
    ArrayList<String> usernames= new ArrayList<>();
    for (int i = 0; i < users.size(); i++)
    {
      usernames.add(users.get(i).getUserName());
    }
    return usernames;
  }


  public boolean haveUsers(User user){
    for (int i = 0; i < users.size(); i++)
    {
      if (user.equals(users.get(i))){
        return true;
      }
    }
    return false;
  }
}
