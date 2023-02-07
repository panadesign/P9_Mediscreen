package com.mediscreen.ms_clientui.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Home controller.
 */
@Controller
@Log4j2
public class HomeController {

    /**
     * Get Home page
     *
     * @return the home page
     */
    @GetMapping("/")
    public String home() {
        log.debug("Access to homepage");
        return "home";
    }

}
