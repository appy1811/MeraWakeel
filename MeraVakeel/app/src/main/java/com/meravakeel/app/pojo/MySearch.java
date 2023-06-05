package com.meravakeel.app.pojo;

public class MySearch {
    String id,uname,date,searchdata,fname;

    public MySearch() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSearchdata() {
        return searchdata;
    }

    public void setSearchdata(String searchdata) {
        this.searchdata = searchdata;
    }

    public MySearch(String id, String uname, String date, String searchdata,String fname) {
        this.id = id;
        this.uname = uname;
        this.date = date;
        this.fname=fname;
        this.searchdata = searchdata;
    }
}
