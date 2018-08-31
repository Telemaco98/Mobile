package com.android.ohara.telemaco.entity;

public class SingletonUser {
    public static SingletonUser instance = null;
    private static User user = null;

    public static SingletonUser getInstance () {
        if (instance == null)  instance = new SingletonUser();
        return instance;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public static User getUser() {
        return user;
    }
}
