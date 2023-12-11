package com.example.service;

import com.example.repository.ProdutoRepository;
import com.example.domain.Produto;
import com.example.dto.AutorDTO;
import com.example.dto.ProdutoDTO;
import com.example.service.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    private UserService userService;
    
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }
    
    public Produto findById(String id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
    
    public void insertProduto(Produto produto) {
        produtoRepository.insert(produto);
    }
    
    public Produto fromDTO(ProdutoDTO produtoDTO, AutorDTO autorDTO) {
        return new Produto(
                produtoDTO.getId(), 
                produtoDTO.getNome(), 
                produtoDTO.getDescricao(), 
                produtoDTO.getCategoria(), 
                produtoDTO.getPreco(),
                autorDTO
        );
    }
}
