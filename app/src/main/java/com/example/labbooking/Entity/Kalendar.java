package com.example.labbooking.Entity;

public class Kalendar {
    public Kalendar(){

    }
    public Kalendar(int id, int no, String description) {
        this.id = id;
        this.no = no;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private int id;
    private int no;
    private String description;
}
