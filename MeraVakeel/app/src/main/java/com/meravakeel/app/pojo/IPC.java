package com.meravakeel.app.pojo;

public class IPC {
    String  section, dtls;

    public IPC(String section, String dtls) {
        this.section = section;
        this.dtls = dtls;
    }

    public IPC() {
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDtls() {
        return dtls;
    }

    public void setDtls(String dtls) {
        this.dtls = dtls;
    }
}
