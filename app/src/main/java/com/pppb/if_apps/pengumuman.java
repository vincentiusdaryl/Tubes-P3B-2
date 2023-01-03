package com.pppb.if_apps;

public class pengumuman {
    private String judul;
    private String tags;
    private String deskripsi;

    public String getJudul() {
        return judul;
    }

    public void setNama(String nama) {
        this.judul = judul;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String id) {
        this.tags = tags;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String id) {
        this.deskripsi = deskripsi;
    }

    public pengumuman(String judul, String tags, String deskripsi) {
        this.judul = judul;
        this.tags = tags;
        this.deskripsi = deskripsi;
    }

}

