package com.pppb.if_apps;

public class prasyaratsemester {
    private String matkul;
    private String status;

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public prasyaratsemester(String matkul, String status) {
        this.matkul = matkul;
        this.status = status;
    }
}
