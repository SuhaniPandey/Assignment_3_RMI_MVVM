package server.model;

import shared.User;
import shared.util.Subject;

import java.util.List;

public interface Login extends Subject
{
  boolean addUser(User user);
  boolean login(User user);
  List<String> getAllUsers();
}
