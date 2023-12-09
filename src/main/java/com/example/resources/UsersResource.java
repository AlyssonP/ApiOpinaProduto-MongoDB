package com.example.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.domain.User;
import com.example.service.UserService;
import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping(value="/users") 
public class UsersResource {
    
    @Autowired
    private UserService service;
    
    
    
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
}
