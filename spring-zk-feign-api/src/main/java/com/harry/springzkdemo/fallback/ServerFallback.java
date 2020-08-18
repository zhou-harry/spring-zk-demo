package com.harry.springzkdemo.fallback;

import com.harry.springzkdemo.feign.FeignClientApi;

public class ServerFallback implements FeignClientApi {
    @Override
    public String getServer() {
        return "server request error";
    }
}
