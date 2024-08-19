package com.example.demo.controller.admin;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private final UploadService uploadService;
    private final ProductService productService;
    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getDashboard(Model model){
        List<Product> products=this.productService.findAll();
        model.addAttribute("products", products);
        return "admin/product/show";
    }
    @GetMapping("/admin/product/create")
    public String create(Model model){
        model.addAttribute("product", new Product());
        return "admin/product/create";
    }
    @PostMapping("/admin/product/create")
    public String create(@ModelAttribute Product product
                         ,@RequestParam("hoidanitFile") MultipartFile file
    ){
        String avatar=this.uploadService.handleSaveUpload(file,"product");
        product.setImage(avatar);
        this.productService.save(product);
        return "redirect:/admin/product";
    }
}
