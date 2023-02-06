package com.mediscreen.ms_assessment.proxies;

import com.mediscreen.ms_assessment.beans.HistoryBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Micro service history proxy.
 */
@FeignClient(name = "microservice-history", url = "${feign.ms-history.url}")
public interface MicroServiceHistoryProxy {

    /**
     * Get history bean.
     *
     * @param id the id
     * @return the history bean
     */
    @GetMapping("/patients/{id}/history")
    public HistoryBean get(@PathVariable Integer id);

    /**
     * Add history bean.
     *
     * @param patId the pat id
     * @param note  the note
     * @return the history bean
     */
    @PostMapping("/patients/{id}/history/add")
    public HistoryBean add(@PathVariable("id") Integer patId, @RequestParam("e") String note);
}
