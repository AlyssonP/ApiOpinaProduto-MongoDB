package com.example.dto;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;



@Data
public class AvaliacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private AutorDTO autor;
    private int nota;
    private String comentario;
    
    public AvaliacaoDTO(){
        
    }
    
    public AvaliacaoDTO(String id, AutorDTO autor, int nota, String comentario){
        this.id = id;
        this.autor = autor;
        this.nota = nota;
        this.comentario = comentario;
    }
    
}
