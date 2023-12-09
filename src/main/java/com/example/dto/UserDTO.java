package com.example.dto;


import com.example.domain.User;
import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String nome;
    private String email;
    
    public UserDTO() {
        
    }
    
    public UserDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
    }
}
