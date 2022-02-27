package com.example.controller;


import com.example.common.lang.Result;
import com.example.entity.User;
import com.example.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzx
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        UserService userService;


        @GetMapping("/{id}")
        public Object test(@PathVariable("id") Long id) {
            return userService.getById(id);
        }

        //@RequiresAuthentication使用该注解标注的类，实例，方法在访问或调用时，当前Subject必须在当前session中已经过认证
        @RequiresAuthentication
        @GetMapping("/index")
        public Result index(){
                User user = userService.getById((1L));
                return Result.succ(user);
        }

        @GetMapping("/save")
        public Object save(@Validated @RequestBody User user) {
                return Result.succ(user);
        }


}
