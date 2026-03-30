package com.example.RateLimiter.controller;

import com.example.RateLimiter.Service.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomePageController {
    @Autowired
    @Qualifier("fixedWindow")
    RateLimiter rateLimiter;

    @RequestMapping(value = "/access", method = RequestMethod.GET)
    public boolean getRequestAccess(@RequestParam(name = "userId") String userId) {
        return rateLimiter.isAccessGranted(userId);
    }
}
