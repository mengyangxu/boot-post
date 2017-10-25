package com.yb.post.Controller;

import com.yb.post.domain.Reply;
import com.yb.post.domain.Storey;
import com.yb.post.domain.Theme;
import com.yb.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 杨波 on 2017/5/27.
 */
@RestController
public class PostController {
    @Autowired
    PostService postService;
    @RequestMapping("/writePost")
    public void writePost(){
        postService.writePost();
    }

    @RequestMapping("/writeReply")
    public void writeReply(){
        postService.writeReply();
    }

    @RequestMapping("/writeStorey")
    public void writeStorey(){
        postService.writeStorey();
    }

    @RequestMapping(value = "/wp", method = RequestMethod.POST)
    public void writePost(Theme theme, Long user_id){
        postService.writePost(theme, user_id);
    }
    @RequestMapping(value = "/wr", method = RequestMethod.POST)
    public void writeReply(Reply reply, Long user_id, Long theme_id){
        postService.writeReply(reply, user_id, theme_id);
    }
    @RequestMapping(value = "/ws", method = RequestMethod.POST)
    public void writeStorey(Storey storey, Long user_id, Long reply_id){
        postService.writeStorey(storey, user_id, reply_id);
    }

    @RequestMapping("/findAll")
    public Page<Theme> findThemeAll(Integer page){
        return postService.findThemeAll(page);
    }
    @RequestMapping("/findReply1")
    public Page<Reply> finReplyByThemeId(Long theme_id, Integer page){
        return postService.findReplyByThemeId(theme_id, page);
    }

    @RequestMapping("/findStorey")
    public List<Storey> findStoreyByReplyId(Long reply_id){
        return postService.findStoreyByReplyId(reply_id);
    }

}
