package com.offcn.userweb01.service;

import com.offcn.userweb01.bean.User;
import com.offcn.userweb01.utils.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "USERPROVIDER",configuration= FeignConfig.class)
public interface UserService {
    @GetMapping("/user/getAll")
    public Map<String, Object> getUserMap();
    @PostMapping("/user/save")
    public void createUser(User user);
    @GetMapping("/user/get/{id}")
    public User getUser(@RequestParam("id") Long id);
    @PutMapping("/user/update/{id}")
    public void updateUser(@RequestParam("id") Long id, @RequestBody User user);
    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@RequestParam("id") Long id);
}