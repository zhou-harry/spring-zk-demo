package com.harry.springzkdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("server")
public class HomeController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${server.port}")
    private String serverPort;
    @Value("${harry.name}")
    private String harryName;
    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/getServer")
    public String getServer() {

        List<ServiceInstance> list = discoveryClient.getInstances("spring-zk-server");
        if (!ObjectUtils.isEmpty(list)) {
            list.forEach(obj -> {
                logger.info("server端服务:{}", obj.getUri().toString());
            });
        }
        return "服务prot:" + serverPort + ", harry's name :" + harryName;
    }
}
