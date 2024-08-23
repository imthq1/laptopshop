package com.example.demo.controller.client;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IteamController {
    private ProductService productService;
    public IteamController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable int id, Model model) {
        Product product=this.productService.getProductByid(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "client/product/detail";
    }
}
