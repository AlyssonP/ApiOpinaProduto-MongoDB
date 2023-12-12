package com.example.resources;

import com.example.service.ProdutoService;
import com.example.domain.Produto;
import com.example.dto.AutorDTO;
import com.example.dto.AvaliacaoDTO;
import com.example.dto.ProdutoDTO;
import com.example.service.AvaliacaoService;
import com.example.service.UserService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/produtos") 
public class ProdutoResource {
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private UserService userService;
    
    
    @Autowired
    private AvaliacaoService avaliacaoService;
    
    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        List<Produto> list = produtoService.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable String id){
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok().body(produto);
    }
    
    @PostMapping(value = "/{idAutor}")
    public ResponseEntity<Void> insertProduto(@PathVariable String idAutor, @RequestBody ProdutoDTO produtoDTO) {
        AutorDTO autorDTO = new AutorDTO(userService.findById(idAutor));
        
        Produto produto = produtoService.fromDTO(produtoDTO, autorDTO);
        produtoService.insertProduto(produto);
        userService.addRefProduto(produto);
        
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        URI uri = ServletUriComponentsBuilder.fromPath(baseUrl+"/produtos/{id}")
                .buildAndExpand(produto.getId())
                .toUri();
        
        return ResponseEntity.created(uri).build();
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable String id){
        Produto produto = produtoService.findById(id);
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateProduto(@RequestBody ProdutoDTO produtoDTO, @PathVariable String id) {
        Produto produto = produtoService.findById(id);
        produtoDTO.setId(id);
        produto = produtoService.fromDTO(produtoDTO, produto.getAutor());
        produtoService.updateProduto(produto);
        return ResponseEntity.noContent().build();
    }
    
    
    // Rotas especificas para avaliações 
    @GetMapping(value="/{id}/avaliacoes")
    public ResponseEntity<List<AvaliacaoDTO>> findAllId(@PathVariable String id){
        List<AvaliacaoDTO> list = avaliacaoService.findAllIdProd(id);
        return ResponseEntity.ok().body(list);
    }
    
    @PostMapping(value = "/{id}/avaliacoes/{idAutor}")
    public ResponseEntity<Void> insertAvaliacao(@PathVariable String idAutor, @RequestBody AvaliacaoDTO avaliacao, @PathVariable String id){
        AutorDTO autor = new AutorDTO(userService.findById(idAutor));
        avaliacaoService.insertAvaliacao(autor, avaliacao.getNota(), avaliacao.getComentario(), id);
        
        return ResponseEntity.noContent().build();
    }
}
