package com.example.repository;

import com.example.domain.Produto;
import com.example.dto.AvaliacaoDTO;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String>{

}