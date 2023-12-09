package com.example.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="users")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String nome;
    private String email;
       
}
