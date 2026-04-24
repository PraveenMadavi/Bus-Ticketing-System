package com.client.busticket.auth_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
//@CrossOrigin("*")
public class TestController {

    @GetMapping("/status")
//    @PreAuthorize("hasAuthority('APP_READ')")
    public String getStatus(){
        return "New status is okay.";
    }
}
