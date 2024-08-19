package com.example.demo.controller.admin;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.UploadService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
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
    public String create(@ModelAttribute @Valid Product product,
                         BindingResult bindingResult
                         ,@RequestParam("hoidanitFile") MultipartFile file
    ){


        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for(FieldError fieldError:fieldErrors){
            System.out.println(fieldError.getField()+"-" + fieldError.getDefaultMessage());
        }
        if(bindingResult.hasErrors()){
            return "admin/product/create";
        }

        String avatar=this.uploadService.handleSaveUpload(file,"product");
        product.setImage(avatar);
        this.productService.save(product);
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/{id}")
    public String getProductPage(Model model,@PathVariable Long id)
    {
        Product product=this.productService.getProductByid(id);
        model.addAttribute("id",id);
        model.addAttribute("product",product);
    return "admin/product/detail";
    }
    @GetMapping("/admin/product/update/{id}")
    public String update(@PathVariable("id") Long id,Model model){
        model.addAttribute("product",this.productService.getProductByid(id));
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update/{id}")
    public String update(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult,
                         @RequestParam("hoidanitFile") MultipartFile file
                         ,@PathVariable Long id
    ) {

        if (bindingResult.hasErrors()) {
            return "admin/product/update";
        }

        Product product1 = this.productService.getProductByid(id);
        if (product1 != null) {
            //upload new img
            if (!file.isEmpty()) {
                String img = this.uploadService.handleSaveUpload(file, "product");
                product1.setImage(img);
            }


            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setDetailDesc(product.getDetailDesc());
            product1.setShortDesc(product.getShortDesc());
            product1.setQuantity(product.getQuantity());
            product1.setFatory(product.getFatory());
            product1.setTarget(product.getTarget());

            this.productService.save(product1);
        }

        return "redirect:/admin/product";
    }


    @GetMapping("/admin/product/delete/{id}")
    public String delete(Model model,@PathVariable Long id){
        Product product=this.productService.getProductByid(id);
        model.addAttribute("product",product);
        model.addAttribute("id",id);
        return "admin/product/delete";
    }
    @PostMapping("/admin/product/delete")
    public String delete(@ModelAttribute("product") Product product)
    {
        this.productService.deleteProductByid(product.getId());
        return "redirect:/admin/product";
    }
}
