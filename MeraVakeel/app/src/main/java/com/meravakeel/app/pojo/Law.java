package com.meravakeel.app.pojo;

import java.io.Serializable;

public class Law implements Serializable {
    String id,mainT,subT,desc,wcbd,lawref;

    public Law(String id, String mainTitle, String subTitle, String desc, String whatCanDone, String lawRef) {
        this.id = id;
        this.mainT = mainTitle;
        this.subT = subTitle;
        this.desc = desc;
        this.wcbd = whatCanDone;
        this.lawref = lawRef;
    }

    public Law() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainT() {
        return mainT;
    }

    public void setMainT(String mainT) {
        this.mainT = mainT;
    }

    public String getSubT() {
        return subT;
    }

    public void setSubT(String subT) {
        this.subT = subT;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWcbd() {
        return wcbd;
    }

    public void setWcbd(String wcbd) {
        this.wcbd = wcbd;
    }



    public String getLawref() {
        return lawref;
    }

    public void setLawref(String lawref) {
        this.lawref = lawref;
    }

}
