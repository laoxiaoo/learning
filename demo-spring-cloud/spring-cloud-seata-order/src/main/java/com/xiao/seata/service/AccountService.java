package com.xiao.seata.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="seata-account-service")
@Component
public interface AccountService {
    @PostMapping(value = "/account/decrease")
    String decrease(@RequestParam("id") Long id, @RequestParam("money") Integer money);
}
