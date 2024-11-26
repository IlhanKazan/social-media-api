package org.example.socialmediaapi.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "/get-new")
    public String getNew() {
        return "Ali";
    }

}
