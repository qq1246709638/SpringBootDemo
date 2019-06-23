package com.web.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginCotroller {
    @PostMapping("/user/login")
    String Login (@RequestParam("username") String username,
                  @RequestParam("password") String password,
                  Map <String,Object> map, HttpSession session ){
        if (!StringUtils.isEmpty(username)&&"123123".equals(password)){
            //防止表单重复提交，重定向来到首页
            session.setAttribute("userLogin","Mr段");
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名或密码错误");
            return "index";
        }

    }
}
