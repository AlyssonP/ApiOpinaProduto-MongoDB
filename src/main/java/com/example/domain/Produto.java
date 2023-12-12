package com.example.domain;

import com.example.dto.AvaliacaoDTO;
import com.example.dto.AutorDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection="produtos")
public class Produto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String nome;
    private String descricao;
    private String categoria;
    private Double preco;
    private AutorDTO autor;
    private List<AvaliacaoDTO> avaliacoes = new ArrayList<>();
    
    public Produto(String id, String nome, String descricao, String categoria, Double preco, AutorDTO autor){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.autor = autor;
    }
    
}
