package com.example.service;

import com.example.domain.Produto;
import com.example.dto.AutorDTO;
import com.example.dto.AvaliacaoDTO;
import com.example.repository.ProdutoRepository;
import com.example.service.exception.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AvaliacaoService {
       
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    // Buscar produto que contém a avaliação
    public Produto produtoAvaliacao(String id) {
        Produto produto = produtoRepository.produtoAvaliacao(id);
        // Se não encontrar a avaliação lança um "Not Found"
        if(produto == null) {
            throw new ObjectNotFoundException("Avaliação não encontrado");
        }
        return produto;
    }

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
    
    
    public void updateAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Produto produto = produtoAvaliacao(avaliacaoDTO.getId());
        
        for(AvaliacaoDTO avaliacao: produto.getAvaliacoes()) {
            if(avaliacao.getId().equals(avaliacaoDTO.getId())) {
                avaliacao.setNota(avaliacaoDTO.getNota());
                avaliacao.setComentario(avaliacaoDTO.getComentario());
                break;
            }
        }
        
        produtoRepository.save(produto);
    }
    
    
    public void deleteAvaliacao(String idAvaliacao) {
        Produto produto = produtoAvaliacao(idAvaliacao);
        
        for(AvaliacaoDTO avaliacao: produto.getAvaliacoes()) {
            if(avaliacao.getId().equals(idAvaliacao)) {
                produto.getAvaliacoes().remove(avaliacao);
                break;
            }
        }
        
        produtoRepository.save(produto);
    }
}
