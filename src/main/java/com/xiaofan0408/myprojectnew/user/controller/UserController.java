package com.xiaofan0408.myprojectnew.user.controller;

import com.xiaofan0408.myprojectnew.user.bean.entity.User;
import com.xiaofan0408.myprojectnew.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xuzefan  2019/9/23 14:11
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/select_all")
    private Flux<User> selectAll(){
        return userService.selectAll();
    }

    @PostMapping("/add")
    private Mono<User> add(@RequestBody User user){
        return userService.insert(user);
    }

    @GetMapping("/update")
    private Mono<User> update(@RequestBody User user){
        return userService.update(user);
    }

    @GetMapping("/delete_one")
    private Mono<Boolean> deleteOne(@RequestParam("id") Long id){
        return userService.delete(id);
    }

}
