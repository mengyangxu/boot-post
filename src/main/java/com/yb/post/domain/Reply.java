package com.yb.post.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by 杨波 on 2017/5/27.
 */
@Entity
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue
    private Long reply_id;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    private String content;
    private Date time;

    /*@OneToMany(cascade = {CascadeType.ALL})
    private List<Storey> storeys;*/

    public Reply() {
    }
    public Reply(Theme theme, User user, String content, Date time) {
        this.theme = theme;
        this.user = user;
        this.content = content;
        this.time = time;
    }

    public Long getReply_id() {
        return reply_id;
    }

    public void setReply_id(Long reply_id) {
        this.reply_id = reply_id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "reply_id=" + reply_id +
                ", theme=" + theme +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
