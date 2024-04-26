package sample.service;

import sample.model.User;

public class UserService {

  public User findUserByName(String name) {
    return new User(name, 30, 0);
  }

  public User saveUser(String name, int age) {
    User user = new User(name, age, 1);
    System.out.println("User saved: " + user);
    return user;
  }
}
