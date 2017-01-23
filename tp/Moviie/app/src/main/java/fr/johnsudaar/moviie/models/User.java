package fr.johnsudaar.moviie.models;


public class User {
    private String username;
    private String apiKey;
    private String[] friends;

    public User(String u, String a, String[]f){
        this.username = u;
        this.apiKey = a;
        this.friends = f;
    }

    public String getUsername() {
        return username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String[] getFriends() {
        return friends;
    }
}
