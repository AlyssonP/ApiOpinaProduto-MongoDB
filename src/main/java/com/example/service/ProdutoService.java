package com.example.service;

import com.example.repository.ProdutoRepository;
import com.example.domain.Produto;
import com.example.domain.User;
import com.example.dto.AutorDTO;
import com.example.dto.ProdutoDTO;
import com.example.repository.UserRepository;
import com.example.service.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
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
    
    public void updateProduto(Produto produto) {
        Produto newProduto = findById(produto.getId());
        updateData(newProduto, produto);
        produtoRepository.save(newProduto);
    }
    
    public void updateData(Produto newProduto, Produto produto) {
        newProduto.setNome(produto.getNome());
        newProduto.setDescricao(produto.getDescricao());
        newProduto.setCategoria(produto.getCategoria());
        newProduto.setPreco(produto.getPreco());
    }
    
    public void deleteProduto(String id){
        Produto produto = findById(id);
        User user = userService.findById(produto.getAutor().getId());
        
        List<Produto> listProduto = user.getProdutos();
        
        for(int i = 0;i<listProduto.size();i++){
            if(listProduto.get(i).getId().equals(id)){
                listProduto.remove(i);
                user.setProdutos(listProduto);
                userRepository.save(user);
                break;
            }
        }   
        
        produtoRepository.deleteById(id);
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
