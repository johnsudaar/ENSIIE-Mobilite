package com.ensiie.tp4;


public class StorageManager {
    private static StorageManager manager = null;

    private User user;

    private StorageManager(){
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public static StorageManager getInstance(){
        if(StorageManager.manager == null){
            StorageManager.manager = new StorageManager();
        }
        return StorageManager.manager;
    }
}
