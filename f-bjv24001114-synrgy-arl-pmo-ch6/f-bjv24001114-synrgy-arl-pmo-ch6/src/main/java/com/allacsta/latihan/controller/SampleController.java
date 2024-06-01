package com.allacsta.latihan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/testlatihan")
public class SampleController {

    @GetMapping(value = "/")
    public String hello(){
        return "database connect";
    }

    @GetMapping(value = "/{yourname}")
    public String yourname(@PathVariable String yourname){
        return yourname;
    }



}
