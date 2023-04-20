package com.example.parcialnumero3samuel.persistences.models;

public class Contacts {

    private Integer Identifier;
    private String names;
    private String last;
    private String phone;
    private String email;

    public Integer getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(Integer identifier) {
        Identifier = identifier;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
