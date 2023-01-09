package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.HistoryBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservice-history", url = "${feign.ms-history.url}")
public interface MicroServiceHistoryProxy {

    @GetMapping("/patients/{id}/history")
    public HistoryBean get(@PathVariable Integer id);

    @PostMapping("/patients/{id}/history/add")
    public HistoryBean add(@PathVariable("id") Integer patId, @RequestParam("e") String note);
}
