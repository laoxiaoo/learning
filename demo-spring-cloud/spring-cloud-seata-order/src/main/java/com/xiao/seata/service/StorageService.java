package com.xiao.seata.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="seata-storage-service")
@Component
public interface StorageService {
    @PostMapping(value = "/storage/decrease")
    String decrease(@RequestParam("id") Long id, @RequestParam("money") Integer money);
}
