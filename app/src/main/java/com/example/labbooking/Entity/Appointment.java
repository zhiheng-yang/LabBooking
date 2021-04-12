package com.example.labbooking.Entity;

public class Appointment {
    /**
     *         {
     *             "id": 1,
     *             "date": "2021-01-23",
     *             "remark": "生化实验",
     *             "is_approved": 0,
     *             "user": {
     *                 "id": 1,
     *                 "username": "zach",
     *                 "name": "杨志恒",
     *                 "password": "123456",
     *                 "contact": "110",
     *                 "no": "201802104060",
     *                 "is_admin": "1",
     *                 "idcard": "110"
     *             },
     *             "lab": {
     *                 "id": 1,
     *                 "name": "生物实验室",
     *                 "no": "1"
     *             },
     *             "kalendar": null
     *         }
     */

    public Appointment(){
        super();
    }

    public Appointment(int id, User user, Lab lab, Kalendar kalendar, String date, int is_approved, String remark) {
        this.id = id;
        this.user = user;
        this.lab = lab;
        this.kalendar = kalendar;
        this.date = date;
        this.is_approved = is_approved;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public Kalendar getKalendar() {
        return kalendar;
    }

    public void setKalendar(Kalendar kalendar) {
        this.kalendar = kalendar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(int is_approved) {
        this.is_approved = is_approved;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private int id;
    private User user;
    private Lab lab;
    private Kalendar kalendar;
    private String date;
    private int is_approved;
    private String remark;
//    private int isAdmin;
//    private int is_admin;
}
