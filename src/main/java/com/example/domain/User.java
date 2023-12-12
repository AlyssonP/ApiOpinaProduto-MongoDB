package com.example.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection="users")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String nome;
    private String email;
    
    @DBRef(lazy=true)
    private List<Produto> produtos = new ArrayList<>();
    
    public User(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
       
}
