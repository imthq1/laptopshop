package com.example.demo.controller.client;


import com.example.demo.domain.DTO.RegisterDTO;
import com.example.demo.domain.Product;

import com.example.demo.domain.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomePageController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final ProductService productService;
    public HomePageController(ProductService productService, UserService userService,PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/client/homepage/show";
    }
    @GetMapping("/client/product")
    public String showProductPage(Model model) {
        return "/client/product/detail";
    }
    @GetMapping("/register")
    public String showRegisterPage(Model model ) {
        model.addAttribute("register", new RegisterDTO());
        return "/client/auth/register";
    }
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("register") RegisterDTO registerDTO) {
        User user = this.userService.registerDTOtoUser(registerDTO);

        String hashPassword = passwordEncoder.encode(registerDTO.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String getLogin(Model model ) {
        model.addAttribute("register", new RegisterDTO());
        return "/client/auth/login";
    }
}
