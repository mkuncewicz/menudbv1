package com.example.dania.controlerGUI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String home(){

        return "adminmain";
    }
}
