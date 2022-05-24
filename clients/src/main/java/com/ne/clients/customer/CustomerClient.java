package com.ne.clients.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "customers")
public interface CustomerClient {
    @GetMapping("api/customers/get")
    void test();
}
