package com.ensiie.tp4;

public class UserSingleton {
    private static final String TAG = "com.ensiie.tp4.UserSingleton";

    private static UserSingleton INSTANCE = new UserSingleton();
    private User user;

    private UserSingleton() {

    }

    public static UserSingleton getInstance() {
        return INSTANCE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void clean() {
        user = null;
    }
}
