package org.example;

import org.example.User.UserDaoImpl;
import org.example.UserDetails.UserDetailsImpl;
import org.example.UserDetails.UserDetails;
import org.junit.jupiter.api.*;

import java.util.List;

public class UserDetailsDaoTest {
    private UserDetailsImpl userDetailsDAO;
    private UserDetails testUserDetails;
    private UserDaoImpl userDao;
    @BeforeEach
    public void setUp(){
        userDetailsDAO = new UserDetailsImpl();
        userDao = new UserDaoImpl();
        testUserDetails = new UserDetails();
        testUserDetails.setUser(userDao.getUserById(5L));
        testUserDetails.setAddress("Example Street 1");
        testUserDetails.setPhoneNumber("+3806645454");
        userDetailsDAO.createUserDetails(testUserDetails);
    }
    @AfterEach
    public void clearTests(){
        userDetailsDAO.deleteUserDetails(testUserDetails);
    }

    @Test
    public void testCreateUserDetails(){
        UserDetails newUserDetails = new UserDetails();
        newUserDetails.setUser(userDao.getUserById(2L));
        newUserDetails.setAddress("Example Street 1");
        newUserDetails.setPhoneNumber("+3806645454");
        userDetailsDAO.createUserDetails(newUserDetails);

        UserDetails retrievedUserDetails = userDetailsDAO.getUserDetailsById(newUserDetails.getId());

        Assertions.assertEquals(newUserDetails.getId(), retrievedUserDetails.getId());
        Assertions.assertEquals(newUserDetails.getUserId(), retrievedUserDetails.getUserId());
        Assertions.assertEquals(newUserDetails.getAddress(), retrievedUserDetails.getAddress());
        Assertions.assertEquals(newUserDetails.getPhoneNumber(), retrievedUserDetails.getPhoneNumber());

        userDetailsDAO.deleteUserDetails(newUserDetails);
    }

    @Test
    public void testGetUserDetailsById(){
        UserDetails retrievedUserDetails = userDetailsDAO.getUserDetailsById(testUserDetails.getId());
        Assertions.assertEquals(testUserDetails.getId(), retrievedUserDetails.getId());
    }

    @Test
    public void testGetAllUserDetails(){
        List<UserDetails> userDetailsList = userDetailsDAO.getAllUserDetails();
        Assertions.assertFalse(userDetailsList.isEmpty());

        UserDetails retrievedUserDetails = userDetailsDAO.getUserDetailsById(testUserDetails.getId());
        Assertions.assertNotNull(retrievedUserDetails);
    }

    @Test
    public void testUpdateUserDetails(){
        testUserDetails.setPhoneNumber("(Changed)");
        testUserDetails.setAddress("(Changed)");
        userDetailsDAO.updateUserDetails(testUserDetails);
        Assertions.assertEquals("(Changed)", testUserDetails.getPhoneNumber());
        Assertions.assertEquals("(Changed)", testUserDetails.getAddress());
    }

    @Test
    @Disabled("clearTests")
    public void testDeleteUserDetails(){
        UserDetails retrievedUserDetails = userDetailsDAO.getUserDetailsById(testUserDetails.getId());
        Assertions.assertEquals(testUserDetails.getId(), retrievedUserDetails.getId());

        userDetailsDAO.deleteUserDetails(testUserDetails);

        retrievedUserDetails = userDetailsDAO.getUserDetailsById(testUserDetails.getId());
        Assertions.assertNull(retrievedUserDetails);
    }
}
