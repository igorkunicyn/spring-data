package com.igorkunicyn.springdata.controllers;

import com.igorkunicyn.springdata.entities.User;
import com.igorkunicyn.springdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String viewHomePage(Model model) {
        return viewPage(1, model);
    }

    @RequestMapping(value = "/page/{pageNum}")
    public String viewPage(@PathVariable(name = "pageNum") int pageNum, Model uiModel) {
        Page<User> userPage = userService.findPaginated(pageNum);
        List<User> userList = userPage.getContent();
        uiModel.addAttribute("currentPage", pageNum);
        uiModel.addAttribute("totalPages", userPage.getTotalPages());
        uiModel.addAttribute("totalItems", userPage.getTotalElements());
        uiModel.addAttribute("listUsers", userList);
        return "user-list";
    }

}
