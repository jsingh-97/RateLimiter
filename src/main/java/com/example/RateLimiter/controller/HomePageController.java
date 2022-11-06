package com.example.RateLimiter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

    @RequestMapping(value = "/access",method = RequestMethod.GET)
    public boolean getRequestAccess(@RequestParam(name="userId") String userId){
        return false;
    }
}
