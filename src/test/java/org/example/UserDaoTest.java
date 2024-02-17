package org.example;


import org.example.User.UserDaoImpl;
import org.example.User.User;
import org.junit.jupiter.api.*;

import java.util.List;

public class UserDaoTest {
    private UserDaoImpl userDao;
    private User testUser;

    @BeforeEach
    public void setUp(){
        userDao = new UserDaoImpl();
        testUser = new User();
        testUser.setName("Test");
        testUser.setSurname("User");
        testUser.setAge(10);
        testUser.setEmail("test@example.com");
        userDao.createUser(testUser);
    }
    @AfterEach
    public void clearTests(){
        userDao.deleteUser(testUser);
    }
    @Test
    public void testCreateUser(){
        User newUser = new User();
        newUser.setName("Test Create");
        newUser.setSurname("User");
        newUser.setAge(10);
        newUser.setEmail("test@example.com");
        userDao.createUser(newUser);

        User retrievedUser = userDao.getUserById(newUser.getId());
        Assertions.assertEquals(newUser.getName(), retrievedUser.getName());
        Assertions.assertEquals(newUser.getSurname(), retrievedUser.getSurname());
        Assertions.assertEquals(newUser.getAge(), retrievedUser.getAge());
        Assertions.assertEquals(newUser.getEmail(), retrievedUser.getEmail());

        userDao.deleteUser(newUser);
    }

    @Test
    public void testGetUserById(){
        User retrievedUser = userDao.getUserById(testUser.getId());
        Assertions.assertEquals(testUser.getId(), retrievedUser.getId());
    }

    @Test
    public void testGetAllUsers(){
        List<User> users = userDao.getAllUsers();
        Assertions.assertFalse(users.isEmpty());

        User retrievedUser = userDao.getUserById(testUser.getId());
        Assertions.assertNotNull(retrievedUser);
    }

    @Test
    public void testUpdateUser(){
        testUser.setName("Updated");
        testUser.setEmail("updated@test.com");
        userDao.updateUser(testUser);
        User retrievedUser = userDao.getUserById(testUser.getId());
        Assertions.assertEquals("Updated", retrievedUser.getName());
        Assertions.assertEquals("updated@test.com", retrievedUser.getEmail());
    }
    @Disabled("clearTests")
    @Test
    public void testDeleteUser() {
        User retrievedUser = userDao.getUserById(testUser.getId());
        Assertions.assertEquals(testUser.getId(), retrievedUser.getId());

        userDao.deleteUser(testUser);

        retrievedUser = userDao.getUserById(testUser.getId());
        Assertions.assertNull(retrievedUser);
    }




}
