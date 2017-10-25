package com.yb.post.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 杨波 on 2017/5/27.
 */
@Entity
@Table(name = "storey")
public class Storey {
    @Id
    @GeneratedValue
    private Long storey_id;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "reply_id")
    private Reply reply;

    private String content;
    private Date time;

    public Storey() {
    }

    @Override
    public String toString() {
        return "Storey{" +
                "storey_id=" + storey_id +
                ", user=" + user +
                ", reply=" + reply +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }

    public Storey(User user, Reply reply, String content, Date time) {
        this.user = user;
        this.reply = reply;
        this.content = content;
        this.time = time;
    }

    public Long getStorey_id() {
        return storey_id;
    }

    public void setStorey_id(Long storey_id) {
        this.storey_id = storey_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
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
}
