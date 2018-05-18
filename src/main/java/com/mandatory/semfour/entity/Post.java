package com.mandatory.semfour.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    private static List<Post> postList = new ArrayList<>();


    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String title;
    @Column
    private int karma;
    @Column
    private String content;
    @Column
    private String user;
    @Column
    private String thumbnail;
    @Column
    private String timestamp;


    public Post(int id, String title,String content, int karma , String user, String thumbnail, String timestamp) {
        this.id = id;
        this.title = title;
        this.karma = karma;
        this.content = content;
        this.user = user;
        this.thumbnail = thumbnail;
        this.timestamp = timestamp;
    }

    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", karma=" + karma +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", thumbnail='" + thumbnail + '\'' +
                ", timestamp=" + timestamp +
                ", id=" + id +
                '}';
    }
}
