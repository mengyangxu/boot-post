package com.yb.post.Controller;

import com.yb.post.domain.Reply;
import com.yb.post.domain.Storey;
import com.yb.post.domain.Theme;
import com.yb.post.domain.User;
import com.yb.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 杨波 on 2017/5/28.
 */
@Controller
public class PageController {
    @Autowired
    PostService postService;

    @RequestMapping("/index")
    public String index(Model model){
        Page<Theme> themeAll = postService.findThemeAll(0);
        List<Theme> themeList = themeAll.getContent();
        int number = themeAll.getNumber();
        int totalPages = themeAll.getTotalPages();
        long all = themeAll.getTotalElements();
        model.addAttribute("all", all);
        model.addAttribute("total", totalPages);
        model.addAttribute("number", number+1);
        model.addAttribute("themeList", themeList);
        return "theme";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/logined")
    public String logined(User user, HttpSession session, Model model){
    	System.out.println("用户名："+user.getUsername());
        User user1 = postService.findUser(user);
        if (user1 != null){
            session.setAttribute("sessionUser", user1);
            Page<Theme> themeAll = postService.findThemeAll(0);
            List<Theme> themeList = themeAll.getContent();
            int number = themeAll.getNumber();
            int totalPages = themeAll.getTotalPages();
            long all = themeAll.getTotalElements();
            model.addAttribute("all", all);
            model.addAttribute("total", totalPages);
            model.addAttribute("number", number+1);
            model.addAttribute("themeList", themeList);
            return "theme";
        }else {
            return "error";
        }

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model){
        session.removeAttribute("sessionUser");
        Page<Theme> themeAll = postService.findThemeAll(0);
        List<Theme> themeList = themeAll.getContent();
        int number = themeAll.getNumber();
        int totalPages = themeAll.getTotalPages();
        long all = themeAll.getTotalElements();
        model.addAttribute("all", all);
        model.addAttribute("total", totalPages);
        model.addAttribute("number", number+1);
        model.addAttribute("themeList", themeList);
        return "theme";
    }

    @RequestMapping("/post")
    public String post(){
        return "wpost";
    }

    @RequestMapping(value = "wpost", method = RequestMethod.POST)
    public String wpost(Theme theme, User user, Model model){
        postService.writePost(theme, user.getUser_id());

        Page<Theme> themeAll = postService.findThemeAll(0);
        List<Theme> themeList = themeAll.getContent();
        int number = themeAll.getNumber();
        int totalPages = themeAll.getTotalPages();
        long all = themeAll.getTotalElements();
        model.addAttribute("all", all);
        model.addAttribute("total", totalPages);
        model.addAttribute("number", number+1);
        model.addAttribute("themeList", themeList);
        return "theme";
    }

    @RequestMapping("/findReply")
    public String theme(Long theme_id, Model model){
        Theme theme = postService.themeOne(theme_id);
        model.addAttribute("theme", theme);
        return "reply";
    }
    @RequestMapping(value = "/wreply", method = RequestMethod.POST)
    public String wreply(Reply reply, Theme theme, User user, Model model){
        postService.writeReply(reply,user.getUser_id(),theme.getTheme_id());

        Theme theme1 = postService.themeOne(theme.getTheme_id());
        model.addAttribute("theme", theme1);
        return "reply";
    }

    @RequestMapping(value = "/wstorey", method = RequestMethod.POST)
    public String wstorey(Storey storey, Reply reply, User user, Model model){
        postService.writeStorey(storey, user.getUser_id(), reply.getReply_id());
        Reply reply1 = postService.findReplyByReplyId(reply.getReply_id());
        Theme theme1 = postService.themeOne(reply1.getTheme().getTheme_id());
        model.addAttribute("theme", theme1);
        return "reply";
    }
}
