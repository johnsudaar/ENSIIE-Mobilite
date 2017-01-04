package com.ensiie.tp3.bo;

/**
 * Created by Adrian on 29/11/2016.
 */
public class TextDescription {
    private String text;
    private String description;

    public TextDescription(String text, String description) {
        this.text = text;
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TextDescription{" +
                "text='" + text + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
