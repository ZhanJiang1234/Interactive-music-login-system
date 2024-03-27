package com.project.music.entity;

import lombok.Getter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author test
 * @since 2023-11-19
 */
@Getter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String nick;

    private String account;

    private String password;

    private String userType;

    public void setId(String id) {
        this.id = id;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", nick=" + nick +
            ", account=" + account +
            ", password=" + password +
            ", userType=" + userType +
        "}";
    }
}
