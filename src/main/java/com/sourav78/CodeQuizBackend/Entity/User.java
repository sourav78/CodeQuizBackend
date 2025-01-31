package com.sourav78.CodeQuizBackend.Entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String password;

    private boolean isAdmin = false;

    private boolean isVerified = false;

    private String verificationCode;

    // User Info Mapping
    @OneToOne(mappedBy = "user")
    private UserInfo userInfo;

    public User() {
    }

    public User(String userName, String email, String password, boolean isAdmin, boolean isVerified, String verificationCode) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isVerified = isVerified;
        this.verificationCode = verificationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getRoles() {
        return isAdmin;
    }

    public void setRoles(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", isAdmin=" + isAdmin + '\'' +
                ", isVerified=" + isVerified +
                ", verificationCode=" + verificationCode +
                '}';
    }
}
