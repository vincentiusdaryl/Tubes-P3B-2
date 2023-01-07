package com.pppb.if_apps.Model;

public class Pengumumann {
    private String id;
    private String title;
    private String updated_at;
    private String created_at;
    private String author;
    private String[] tags;
    private String[] tag_id;


    public Pengumumann(String id, String title, String updated_at, String created_at, String author, String[] tags, String[] tag_id){
        this.id = id;
        this.title = title;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.author = author;
        this.tags = tags;
        this.tag_id = tag_id;
    }


    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getUpdated_at(){
        return updated_at;
    }

    public void setUpdated_at(String updated_at){
        this.updated_at = updated_at;
    }

    public String getCreated_at(){
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String[] getTags(){
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getTag_id(){
        return tag_id;
    }

    public void setTag_id(String[] tag_id) {
        this.tag_id = tag_id;
    }
}
