package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.HistoryBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservice-assessment", url = "${feign.ms-assessment.url}")
public interface MicroServiceAssessmentProxy {

    @GetMapping("/patients/{id}/assessment")
    public HistoryBean get(@PathVariable Integer id);
}
