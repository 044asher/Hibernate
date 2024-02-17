package org.example.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void updateUser(User user);
    void deleteUser(User user);
}
