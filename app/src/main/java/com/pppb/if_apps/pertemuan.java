package com.pppb.if_apps;

public class pertemuan {
    private String judul;
    private String tanggal;
    private String deskripsi;

    public pertemuan(String judul, String tanggal, String deskripsi) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String id) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String id) {
        this.deskripsi = deskripsi;
    }


}


