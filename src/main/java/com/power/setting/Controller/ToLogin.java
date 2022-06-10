package com.power.setting.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ToLogin {
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "index.html";
    }
}
