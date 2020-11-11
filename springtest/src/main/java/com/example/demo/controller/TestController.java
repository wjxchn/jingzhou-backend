package com.example.demo.controller;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @CrossOrigin
    @RequestMapping(value = "/test", method= RequestMethod.POST)
    public String test(@RequestBody JSONObject jsonObject){
        return jsonObject.getAsString("testfirst")+jsonObject.getAsString("testsecond");
    }
}
