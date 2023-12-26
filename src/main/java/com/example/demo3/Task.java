package com.example.demo3;

import java.io.*;

public class Task {
    private String subject;
    private String date;
    private String time;
    private String description;
    private String location;

    public Task(String subject, String date, String time, String description, String location){
        this.date = date;
        this.time = time;
        this.description = description;
        this.location = location;
        this.subject = subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString(){
        String s = "Subject: " + this.subject + "\n" +
                "Date: " + this.date + "\n" +
                   "Time: " + this.time + "\n" +
                "Description: "+ this.description + "\n" +
                "Location: " + this.location;
        return s;
    }

    public String getSubject() {
        return subject;
    }
}
