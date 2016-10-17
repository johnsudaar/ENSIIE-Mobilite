package com.ensiie.tp1.model;

/**
 * Created by Adrian on 11/09/16.
 */
public class Exercise {
    private String title;
    private int number;

    public Exercise(String title, int number) {
        this.title = title;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
