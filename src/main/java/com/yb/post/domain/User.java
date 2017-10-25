package com.yb.post.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 杨波 on 2017/5/27.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long user_id;

    private String username;
    private String password;

    /*@OneToMany(cascade = {CascadeType.ALL})
    private List<Theme> themes;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Reply> replies;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Storey> storeys;*/

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
}
