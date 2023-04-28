package com.example.personaldetailer.model;

import java.io.Serializable;

public class Student implements Serializable {

    String name;
    String email;
    String nrc;
    String gender;
    String phNum;
    String profileImgUrl;

    public Student(String name, String email, String nrc, String gender, String phNum, String profileImgUrl) {
        this.name = name;
        this.email = email;
        this.nrc = nrc;
        this.gender = gender;
        this.phNum = phNum;
        this.profileImgUrl = profileImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}
