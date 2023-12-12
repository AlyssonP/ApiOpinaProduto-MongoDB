package com.example.service;

import com.example.domain.Produto;
import com.example.dto.AutorDTO;
import com.example.dto.AvaliacaoDTO;
import com.example.repository.ProdutoRepository;
import com.example.repository.UserRepository;
import com.example.service.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;


@Service
public class AvaliacaoService {
       
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<AvaliacaoDTO> findAllIdProd(String id){
        Produto produto = produtoService.findById(id);
        List<AvaliacaoDTO> listAvaliacao = produto.getAvaliacoes();
        return listAvaliacao;
    }
    
    public void insertAvaliacao(AutorDTO autor, int nota, String comentario, String idProd){
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(autor, nota, comentario);
        Produto produto = produtoService.findById(idProd);
        produto.getAvaliacoes().add(avaliacao);
        produtoRepository.save(produto);
    }
}
