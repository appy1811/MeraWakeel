package com.meravakeel.app.pojo;

public class User {
    String uname,password,emailid,fullname,phno;

    public User(String uname, String password, String emailid, String fullname, String phno) {
        this.uname = uname;
        this.password = password;
        this.emailid = emailid;
        this.fullname = fullname;
        this.phno = phno;
    }

    public User(String uname, String password, String emailid, String fullname) {
        this.uname = uname;
        this.password = password;
        this.emailid = emailid;
        this.fullname = fullname;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public User() {
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
