package com.example.car.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SwaggerController {

    @GetMapping("/swagger")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/swagger/");
    }

    @RequestMapping("/swagger/")
    public String welcomeAPIAlt() {
        return "./index.html";
    }
}
