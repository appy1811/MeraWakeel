package com.meravakeel.app;

public class MyList {
    private int image;
    String myname;



    public MyList(int image, String mn) {
        this.image = image;myname=mn;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
