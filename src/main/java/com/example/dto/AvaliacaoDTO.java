package com.example.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;



@Data
@NoArgsConstructor
public class AvaliacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private AutorDTO autor;
    private int nota;
    private String comentario;
    
    public AvaliacaoDTO ( AutorDTO autor, int nota, String comentario) {
        this.id = UUID.randomUUID().toString();
        this.autor = autor;
        this.nota = nota;
        this.comentario = comentario;
    }
}
