package org.example.UserDetails;



import java.util.List;

public interface UserDetailsDao {
    void createUserDetails(UserDetails userDetails);
    List<UserDetails> getAllUserDetails();
    UserDetails getUserDetailsById(Long id);
    void updateUserDetails(UserDetails userDetails);
    void deleteUserDetails(UserDetails userDetails);
}
