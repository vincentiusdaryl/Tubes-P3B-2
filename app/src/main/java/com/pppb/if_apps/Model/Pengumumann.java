package com.pppb.if_apps.Model;

public class Pengumumann {
    private String id;
    private String title;
    private String updated_at;
    private String created_at;
    private String author_id;
    private String author_name;
    private String[] tag_name;
    private String[] tag_id;


    public Pengumumann(String id, String title, String updated_at, String created_at, String author_id, String author_name,
                       String[] tag_name, String[] tag_id) {
        this.id = id;
        this.title = title;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.author_id = author_id;
        this.author_name = author_name;
        this.tag_name = tag_name;
        this.tag_id = tag_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String[] getTag_name() {
        return tag_name;
    }

    public void setTag_name(String[] tag_name) {
        this.tag_name = tag_name;
    }

    public String[] getTag_id() {
        return tag_id;
    }

    public void setTag_id(String[] tag_id) {
        this.tag_id = tag_id;
    }

}
