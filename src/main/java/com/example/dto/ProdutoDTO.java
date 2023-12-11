package com.example.dto;

import com.example.domain.Produto;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class ProdutoDTO {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String nome;
    private String descricao;
    private String categoria;
    private Double preco;
    
    public ProdutoDTO() {
     
    }
    
    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
    }
}
