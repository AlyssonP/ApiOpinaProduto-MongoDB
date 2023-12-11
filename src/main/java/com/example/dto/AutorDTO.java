package com.example.dto;

import java.io.Serializable;
import lombok.Data;
import com.example.domain.User;

@Data
public class AutorDTO implements Serializable{
    private static final long serialVersionUID = 1L;
	
    private String id;
    private String nome;
	
    public AutorDTO() {
    }
	
    public AutorDTO(User user) {
	this.id = user.getId();
	this.nome = user.getNome();
    }
    
}
