package com.example.nootes;

import java.text.DateFormat;
import java.util.Calendar;

public class Note {

    String text ="";
    String date = "";

    Calendar calendar = Calendar.getInstance();

    Note(String text) {
        this.text = text;
        this.date = DateFormat.getDateInstance().format(calendar.getTime());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getDate() {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
    }

}