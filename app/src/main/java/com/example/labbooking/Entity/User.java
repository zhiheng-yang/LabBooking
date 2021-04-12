package com.example.labbooking.Entity;

public class User {
    public User(int id, String username, String password, String name, String IDcard, String contact, int isAdmin, int is_admin) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.IDcard = IDcard;
        this.contact = contact;
        this.isAdmin = isAdmin;
        this.is_admin = is_admin;
    }
    public User(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    private int id;
    private String username;
    private String password;
    private String name;
    private String IDcard;
    private String contact;
    private int isAdmin;
    private int is_admin;
}
