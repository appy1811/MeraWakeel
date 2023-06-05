package com.meravakeel.app.pojo;

public class Lawyer {
    String did, dname, address, mobno, emailid, specialization, totalexp, qualification;

    public Lawyer(){
    }

    public Lawyer(String did, String dname, String address, String mobno, String emailid, String specialization, String totalexp, String qualification) {
        this.did = did;
        this.dname = dname;
        this.address = address;
        this.mobno = mobno;
        this.emailid = emailid;
        this.specialization = specialization;
        this.totalexp = totalexp;
        this.qualification = qualification;

    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getTotalexp() {
        return totalexp;
    }

    public void setTotalexp(String totalexp) {
        this.totalexp = totalexp;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

}
