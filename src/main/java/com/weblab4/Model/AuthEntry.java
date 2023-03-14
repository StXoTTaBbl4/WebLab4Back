package com.weblab4.Model;

import com.weblab4.Others.Encryptor;
import jakarta.persistence.*;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "users")
public class AuthEntry implements Serializable {

    @Id
    private String login;
    @Column
    private String password;
//    private boolean isValid;
//
//    public boolean isValid() {
//        return isValid;
//    }
//
//    public void setValid(boolean valid) {
//        isValid = valid;
//    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
