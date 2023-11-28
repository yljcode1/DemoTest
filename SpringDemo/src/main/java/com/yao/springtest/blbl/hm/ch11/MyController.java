package com.yao.springtest.blbl.hm.ch11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @date: 2023-11-28
 * @author: yao
 */
@RestController
public class MyController {
    @Lazy
    @Autowired
    private BeanForRequest request;
    @Lazy
    @Autowired
    private BeanForSession session;
    @Lazy
    @Autowired
    private BeanForApplication application;

    @GetMapping(value = "/test", produces = "text/html")
    public String test(HttpServletRequest request, HttpSession session) {
        ServletContext servletContext = request.getServletContext();
        String sb = "<ul>" +
                "<li>" + "request scope" + request + "</li>" +
                "<li>" + "session scope" + session + "</li>" +
                "<li>" + "application scope" + application + "</li>" +
                "</ul>";
        return sb;
    }
}
