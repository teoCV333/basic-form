package com.example.basic_form;

import java.io.Serializable;

public class FormModel implements Serializable {
    private String name;
    private String cc;
    private String birth;
    private String vote;

    public FormModel(String name, String cc, String birth, String vote) {
        this.name = name;
        this.cc = cc;
        this.birth = birth;
        this.vote = vote;
    }

    public String getName() {
        return name;
    }

    public String getCc() {
        return cc;
    }

    public String getBirth() {
        return birth;
    }

    public String getVote() {
        return vote;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
