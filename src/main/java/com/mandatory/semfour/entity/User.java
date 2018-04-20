package com.mandatory.semfour.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;




@Entity
public class User {

    private static List<User> userList = new ArrayList<>();

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int Id;
    private String username;
    private String password;
    private String email;

    public User(int id, String username, String password, String email) {
        this.Id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public static List<User> getStudentList() {
        return userList;
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static void setUserList(List<User> userList) {
        User.userList = userList;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


//    public static User getUserById(long id){
//        List<User> list = User.getUserList();
//
//        User theOne = new User(-1, "rootUser", "rootPassword","root@rootymail.com");
//        for (int i = 0; i < list.size(); i++) {
//            User u = list.get(i);
//            if (u.getId() == id) {
//                System.out.println(u);
//                theOne = u;
//                break;
//            }
//        }
//        return theOne;
//    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + Id +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
