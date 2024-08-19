package com.example.demo.controller.client;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "/client/homepage/show";
    }
    @GetMapping("/client/product")
    public String showProductPage(Model model) {
        return "/client/product/detail";
    }
}
