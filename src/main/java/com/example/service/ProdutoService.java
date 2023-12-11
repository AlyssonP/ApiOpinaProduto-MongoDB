package com.example.service;

import com.example.repository.ProdutoRepository;
import com.example.domain.Produto;
import com.example.service.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository repo;
    
    public List<Produto> findAll(){
        return repo.findAll();
    }
    
    public Produto findById(String id){
        Optional<Produto> produto = repo.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    } 
}
