package org.zerock.partThree.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/exLayout1")
    public void exLayout1(){
        log.info("exLayout...........");
    }
}
