package com.example.shiro.controller;

import com.example.shiro.shiro.MyShiroRealm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: TODO
 * Package: com.example.shiro.controller
 *
 * @author Leeves
 * @date 2018-03-18
 */
@Controller
public class loginController {

    private static Logger log = LoggerFactory.getLogger(loginController.class);

    //跳转到登录表单页面
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    /**
     * ajax登录请求
     */
    @PostMapping("/ajaxLogin")
    @ResponseBody
    public Map<String, Object> submitLogin(String username, String password, Model model) {
        log.info("username:" + username);
        log.info("password:" + password);
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            log.info("subject:" + subject.getSession());
            log.info("subject:" + subject.getSession().getId());
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }


    //跳转到主页
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> logout() {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        try {
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "userInfoAdd";
    }

    @GetMapping("/403")
    public String error() {
        return "403";
    }
}
