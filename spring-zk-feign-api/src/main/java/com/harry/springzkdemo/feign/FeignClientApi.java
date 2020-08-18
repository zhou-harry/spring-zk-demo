package com.harry.springzkdemo.feign;

import com.harry.springzkdemo.fallback.ServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "spring-zk-server", fallback = ServerFallback.class)
public interface FeignClientApi {

    @GetMapping("/server/getServer")
    String getServer();
}
