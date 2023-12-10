package com.example.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.domain.User;
import com.example.dto.UserDTO;
import com.example.repository.UserRepository;
import com.example.service.exception.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> findAll(){
        return userRepository.findAll();
    }
    
    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
    
    public User insertUser(User user) {
        return userRepository.insert(user);
    }
    
    public User updateUser(User user) {
        Optional<User> newUser = userRepository.findById(user.getId());
        updateData(newUser.get(), user);
        return userRepository.save(newUser.get());
    }
    
    public void updateData(User newUser, User user) {
        newUser.setNome(user.getNome());
        newUser.setEmail(user.getEmail());
    }
    
    public void deleteUser(String id) {
        findById(id);
        userRepository.deleteById(id);
    }
    
    public User fromDTO(UserDTO userDto) {
        return new User(userDto.getId(), userDto.getNome(), userDto.getEmail());
    }
}
