package com.yb.post.service;

import com.yb.post.dao.ReplyRepository;
import com.yb.post.dao.StoreyRepository;
import com.yb.post.dao.ThemeRepository;
import com.yb.post.dao.UserRepository;
import com.yb.post.domain.Reply;
import com.yb.post.domain.Storey;
import com.yb.post.domain.Theme;
import com.yb.post.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 杨波 on 2017/5/27.
 */
@Service
@Transactional
public class PostService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ThemeRepository themeRepository;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    StoreyRepository storeyRepository;

    public void writePost(){
        Theme theme = new Theme();
        theme.setTitle("请我而后期望和哦亲往后二号");
        theme.setContent("我撒旦就快啦四大石窟良好的拉伸到拉萨惊呆了空间看撒旦就" +
                "拉屎建档立卡就上课了到家啊老实交代辣椒水撒娇的撒娇了");
        theme.setLasttime(new Date());
        theme.setTime(new Date());
        theme.setReply_num(312);
        User user = userRepository.findOne(1L);
        theme.setUser(user);
        themeRepository.save(theme);
    }

    public void writePost(Theme theme, Long user_id){
        theme.setTime(new Date());
        theme.setLasttime(new Date());
        theme.setContent(theme.getPost().substring(0,80));
        theme.setReply_num(0);
        theme.setUser(userRepository.findOne(user_id));
        themeRepository.save(theme);
    }

    public void writeReply(){
        Reply reply = new Reply();
        reply.setTime(new Date());
        reply.setContent("楼主傻逼");
        User user = userRepository.findOne(2L);
        Theme theme = themeRepository.findOne(1L);
        reply.setTheme(theme);
        reply.setUser(user);

        replyRepository.save(reply);
    }
    public void writeReply(Reply reply, Long user_id, Long theme_id){
        reply.setUser(userRepository.findOne(user_id));
        reply.setTheme(themeRepository.findOne(theme_id));
        reply.setTime(new Date());
        replyRepository.save(reply);
        themeRepository.updateReplyNum(theme_id);
        themeRepository.updateLastTime(new Date(), theme_id);
    }
    public void writeStorey(){
        Storey storey = new Storey();
        storey.setContent("你才是傻逼");
        User user = userRepository.findOne(1L);
        Reply reply = replyRepository.findOne(1L);
        storey.setUser(user);
        storey.setReply(reply);
        storey.setTime(new Date());


        storeyRepository.save(storey);
    }
    public void writeStorey(Storey storey, Long user_id, Long reply_id){
        storey.setUser(userRepository.findOne(user_id));
        Reply reply = replyRepository.findOne(reply_id);
        storey.setReply(reply);
        storey.setTime(new Date());

        storeyRepository.save(storey);
        themeRepository.updateReplyNum(reply.getTheme().getTheme_id());
        themeRepository.updateLastTime(new Date(),reply.getTheme().getTheme_id());

    }
    public Page<Theme> findThemeAll(Integer page){
        return themeRepository.findAll(new PageRequest(page,10,new Sort(Sort.Direction.DESC,"lasttime")));
    }

    public Page<Reply> findReplyByThemeId(Long theme_id, Integer page){
        Reply reply = new Reply();
        Theme theme = new Theme();
        theme.setTheme_id(theme_id);
        reply.setTheme(theme);
        Example<Reply> example = Example.of(reply);
        return replyRepository.findAll(example, new PageRequest(page, 10, new Sort(Sort.Direction.ASC, "time")));
    }
    public List<Storey> findStoreyByReplyId(Long reply_id){
        Storey storey = new Storey();
        Reply reply = new Reply();
        reply.setReply_id(reply_id);
        storey.setReply(reply);
        Example<Storey> example = Example.of(storey);
        return storeyRepository.findAll(example,  new Sort(Sort.Direction.ASC, "time"));
    }

    public User findUser(User user) {
        Example<User> example = Example.of(user);
        return userRepository.findOne(example);
    }

    public Theme themeOne(Long theme_id){
        return themeRepository.findOne(theme_id);
    }

    public Reply findReplyByReplyId(Long reply_id){
        return replyRepository.findOne(reply_id);
    }
}