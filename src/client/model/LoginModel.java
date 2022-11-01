package client.model;

import shared.User;
import shared.util.Subject;

import java.util.List;

public interface LoginModel extends Subject
{
  boolean addUser(String username, String password);

  User getUser();

  boolean login(User user);
  List<String> getAllUsers();
}
