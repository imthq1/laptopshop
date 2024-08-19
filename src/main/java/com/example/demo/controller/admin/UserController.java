package com.example.demo.controller.admin;

import com.example.demo.domain.User;
import com.example.demo.service.UploadService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService,
                          UploadService uploadService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }




    @GetMapping(value = "/admin/user")
    public String showCreateForm(Model model) {
        List<User> Users=this.userService.findAll();
        model.addAttribute("users", Users);

        return "admin/user/show";
    }


    @GetMapping(value = "/admin/user/create")
    public String showCreate(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }


    @PostMapping(value = "/admin/user/create")
    public String createUser(Model model,
                             @ModelAttribute("newUser")@Valid User user,
                             BindingResult bindingResult,
                             @RequestParam("hoidanitFile") MultipartFile file
    ) throws IOException {
        //validate
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for(FieldError fieldError:fieldErrors){
            System.out.println(fieldError.getField()+"-" + fieldError.getDefaultMessage());
        }

        if (bindingResult.hasErrors()) {
            return "admin/user/create";
        }

        //
        String avatar =this.uploadService.handleSaveUpload(file,"avatar");
        String hashPassword=this.passwordEncoder.encode(user.getPassword());

        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public String getUserPage(Model model, @PathVariable long id) {
        User Users=this.userService.getUserById(id);
        System.out.println("Check "+id);
        model.addAttribute("id",id);
        model.addAttribute("user", Users);
        return "admin/user/detail";
    }
    @RequestMapping(value = "/admin/user/update/{id}", method = RequestMethod.GET)
    public String getUpdateUserPage(Model model,@PathVariable long id ) {
        model.addAttribute("user", this.userService.getUserById(id));

        return "admin/user/update";
    }
    //@ModelAttribute("newUser") User user data by view
    @PostMapping("/admin/user/update/{id}")
    public String updateUser(Model model,@ModelAttribute("newUser") User user) {
    User Users=this.userService.getUserById(user.getId());
    if (Users != null) {
        Users.setId(user.getId());
        Users.setAddress(user.getAddress());
        Users.setFullName(user.getFullName());
        Users.setPhone(user.getPhone());

        this.userService.handleSaveUser(Users);
    }

    return "redirect:/admin/user";

    }
    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(Model model,@PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", this.userService.getUserById(id));
        return "admin/user/delete";
    }
    @PostMapping("/admin/user/delete")
    public String deleteUser(Model model,@ModelAttribute("newUser") User user)
    {
        this.userService.DeleteById(user.getId());
        return "redirect:/admin/user";
    }


}
