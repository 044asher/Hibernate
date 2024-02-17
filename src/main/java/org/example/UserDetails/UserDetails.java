package org.example.UserDetails;

import jakarta.persistence.*;
import org.example.User.User;

@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "details_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    public UserDetails(){
    }
    public UserDetails(User user, String address, String phoneNumber){
        this.user = user;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public Long getUserId(){return user.getId();}

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "userDetails{" +
                "details_id = " + id +
                ", user_id ='" + user.getId() + '\'' +
                ", address ='" + address + '\'' +
                ", phone_number ='" + phoneNumber +
                '}';
    }
}
