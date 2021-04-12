package com.example.labbooking.Entity;

public class Lab {

    public Lab(int id, String name, int no) {
        super();
        this.id = id;
        this.name = name;
        this.no = no;
    }
    public Lab(){
        super();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Lab{" +
                "id=" + id +
                ", no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    private int id;
    private String name;
    private int no;

}
