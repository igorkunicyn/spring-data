package com.igorkunicyn.springdata.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@SuppressWarnings("ALL")
@Controller
public class MainController {

    @RequestMapping("/")
    public String showHomePage(){
        return "index";
    }
}
