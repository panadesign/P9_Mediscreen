package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.AssessmentBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The interface Micro service assessment proxy.
 */
@FeignClient(name = "microservice-assessment", url = "${feign.ms-assessment.url}")
public interface MicroServiceAssessmentProxy {

    /**
     * Get assessment bean.
     *
     * @param id the id
     * @return the assessment bean
     */
    @GetMapping("/patients/{id}/assessment")
    AssessmentBean get(@PathVariable Integer id);
}
