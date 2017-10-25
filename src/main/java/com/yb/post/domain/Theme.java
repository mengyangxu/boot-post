package com.yb.post.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 杨波 on 2017/5/27.
 */
@Entity
@Table(name = "theme")
public class Theme {
    @Id
    @GeneratedValue
    private Long theme_id;

    private String title;
    private String content;
    private String post;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    private Date lasttime;
    private Date time;
    private Integer reply_num;

    /*@OneToMany(cascade = {CascadeType.ALL})
    private List<Reply> replies;*/

    public Theme() {
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Theme(String title, String content, String post, User user, Date lasttime, Date time, Integer reply_num) {
        this.title = title;
        this.content = content;
        this.post = post;
        this.user = user;
        this.lasttime = lasttime;
        this.time = time;
        this.reply_num = reply_num;
    }

    public Long getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Long theme_id) {
        this.theme_id = theme_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getReply_num() {
        return reply_num;
    }

    public void setReply_num(Integer reply_num) {
        this.reply_num = reply_num;
    }
}
