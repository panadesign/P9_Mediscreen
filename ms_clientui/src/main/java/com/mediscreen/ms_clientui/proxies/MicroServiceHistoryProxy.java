package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.HistoryBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
    HistoryBean get(@PathVariable Integer id);

    /**
     * Add history bean.
     *
     * @param patId the patient id
     * @param note  the note
     * @return a new history bean
     */
    @PostMapping("/patients/{id}/history/add")
    HistoryBean add(@PathVariable("id") Integer patId, @RequestParam("note") String note);

    @PostMapping("/patients/{id}/history/{noteId}")
    HistoryBean update(@PathVariable Integer id, @PathVariable Long noteId,  @RequestParam String note);

}
