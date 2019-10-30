package com.offcn.userweb01.service.impl;

import com.offcn.userweb01.bean.User;
import com.offcn.userweb01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    //远程服务调用客户端
    @Autowired
    RestTemplate restTemplate;
    //Eureka客户端
    @Autowired
    DiscoveryClient discoveryClient;

    String url="http://USERPROVIDER"+"/user";
    
    @Override
    public Map getUserMap() {
        Map map = restTemplate.getForObject(url+"/getAll", Map.class);
        return map;
    }

    @Override
    public void createUser(User user) {

        restTemplate.postForObject(url+"/save", user,String.class);

    }

    @Override
    public User getUser(Long id) {

        return restTemplate.getForObject(url+"/get/"+id, User.class);
    }

    @Override
    public void updateUser(Long id, User user) {
        restTemplate.put(url+"/update/"+id, user);

    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.delete(url+"/delete/"+id);
    }
}
