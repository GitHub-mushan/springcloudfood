package com.mushan.controller;

import com.mushan.entity.Admin;
import com.mushan.entity.User;
import com.mushan.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountFeign accountFeign;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession httpSession){
        Object object = accountFeign.login(username, password, type);
        LinkedHashMap<String,Object> hashMap = (LinkedHashMap) object;
        String result = null;
        String idstr = null;
        long id = 0;
        if(object == null){
            result = "login";
        }else {
            switch (type){
                case "user":
                    User user = new User();
                    idstr =hashMap.get("id")+"";
                    id = Long.parseLong(idstr);
                    String nickname = (String)hashMap.get("nickname");
                    user.setId(id);
                    user.setNickname(nickname);
                    httpSession.setAttribute("user",user);
                    result = "index";
                    break;
                case "admin":
                    Admin admin = new Admin();
                    idstr =hashMap.get("id")+"";
                    id = Long.parseLong(idstr);
                    String username2 = (String) hashMap.get("username");
                    httpSession.setAttribute("admin",admin);
                    result = "main";
                    break;
            }
        }
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }


}
