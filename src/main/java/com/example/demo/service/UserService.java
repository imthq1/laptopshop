package com.example.demo.service;


import com.example.demo.domain.DTO.RegisterDTO;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }
    public User handleSaveUser(User user){
        return this.userRepository.save(user);
    }

    public List<User> getAllUsersByEmail(String email){
        return this.userRepository.getAllUsersByEmail(email);
    }
    public User getUserById(long id){
        return this.userRepository.findUserById(id);
    }
    public User DeleteById(long id){
        return this.userRepository.deleteById(id);
    }
    public Role getRoleByName(String name)
    {
    return this.roleRepository.findByName(name);
    }
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName()+" "+registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }
}
