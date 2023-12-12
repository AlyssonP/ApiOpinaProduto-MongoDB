package com.example.repository;

import com.example.domain.Produto;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String>{
    
    @Query(value = "{\"autor._id\": ObjectId('?0')}")
    public List<Produto> produtoAutor(String id);
    
    @Query(value = "{\"avaliacoes._id\": '?0'}")
    public Produto produtoAvaliacao(String id);
    
}