package com.example.resources;

import com.example.domain.Produto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.domain.User;
import com.example.dto.UserDTO;
import com.example.service.ProdutoService;
import com.example.service.UserService;
import java.net.URI;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequestMapping(value="/users") 
public class UserResource {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = userService.findAll();
        // Convertendo User para UserDTO
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }
    
    @GetMapping(value = "{id}/produto")
    public ResponseEntity<List<Produto>> produtoAutor(@PathVariable String id) {
        return ResponseEntity.ok().body(produtoService.produtoAutor(id));
    }
    
    @PostMapping
    public ResponseEntity<Void> insertUser(@RequestBody UserDTO userDto) {
        User user = userService.fromDTO(userDto);
        User obj = userService.insertUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDto, @PathVariable String id){
        User user = userService.fromDTO(userDto);
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping(value = "/{id}/produtos")
    public ResponseEntity<List<Produto>> findProdutos(@PathVariable String id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user.getProdutos());
    }
    
}
