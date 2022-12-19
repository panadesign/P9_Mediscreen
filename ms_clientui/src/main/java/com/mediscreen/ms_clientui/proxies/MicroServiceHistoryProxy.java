package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.HistoryBean;
import com.mediscreen.ms_clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-patients-observation", url = "localhost:8082")
public interface MicroServiceHistoryProxy {

    @GetMapping("/patHistory")
    public HistoryBean get(@RequestParam Integer id);

    @PostMapping("/patHistory/add")
    public HistoryBean add(@RequestParam("id") Integer patId, @RequestParam("e") String note);
}
