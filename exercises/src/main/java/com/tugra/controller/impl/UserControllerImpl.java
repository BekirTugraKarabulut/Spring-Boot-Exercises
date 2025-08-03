package com.tugra.controller.impl;

import com.tugra.controller.UserController;
import com.tugra.dto.DtoUser;
import com.tugra.dto.DtoUserUI;
import com.tugra.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api")
@Tag(name = "User Controller" , description = "User API işlemleri")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;


    @Override
    @GetMapping(path = "/all-list")
    public List<DtoUser> getUsers() {
        return userService.getUsers();
    }

    @Override
    @GetMapping(path = "/user/{ad}")
    public DtoUser getUserByAd(@PathVariable(name = "ad" , required = true) String ad) {
        return userService.getUserByAd(ad);
    }

    @Override
    @PostMapping(path = "/user/ekle")
    public DtoUser ekleUser(@RequestBody DtoUserUI dtoUserUI) {
        return userService.ekleUser(dtoUserUI);
    }

}
