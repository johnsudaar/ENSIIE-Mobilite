package fr.johnsudaar.moviie.models;


import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private String username;
    private String apiKey;
    private ArrayList<String> friends;

    public User(String u, String a, String[]f){
        this.username = u;
        this.apiKey = a;
        this.friends = new ArrayList<String>();
        this.friends.addAll(Arrays.asList(f));
    }

    public String getUsername() {
        return username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String[] getFriends() {
        String[] result = new String[this.friends.size()];
        return friends.toArray(result);
    }

    public ArrayList<String> getFriendsAsList(){
        return this.friends;
    }

    public void addFriend(String f){
        this.friends.add(f);
    }
}
