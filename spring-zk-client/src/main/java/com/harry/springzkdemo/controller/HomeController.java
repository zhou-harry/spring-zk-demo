package com.harry.springzkdemo.controller;

import com.harry.springzkdemo.feign.FeignClientApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class HomeController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FeignClientApi feignClientApi;

    /**
     * restTemplate方式访问依赖服务
     *
     * @return
     */
    @GetMapping("/getServer")
    public String getServer() {

        List<ServiceInstance> list = discoveryClient.getInstances("spring-zk-client");
        if (!ObjectUtils.isEmpty(list)) {
            list.forEach(obj -> {
                logger.info("client端服务:{}", obj.getUri().toString());
            });
        }
        String url = "http://spring-zk-server/server/getServer";
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * Feign方式调用依赖服务
     *
     * @return
     */
    @GetMapping("/getFeign")
    public String getFeign() {
        return feignClientApi.getServer();
    }

}
