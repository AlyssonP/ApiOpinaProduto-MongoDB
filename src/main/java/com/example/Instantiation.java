/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import com.example.domain.Produto;
import java.util.Arrays;
import com.example.domain.User;
import com.example.dto.AutorDTO;
import com.example.dto.AvaliacaoDTO;
import com.example.repository.ProdutoRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private ProdutoRepository produtoRepo;
    
    @Override
    public void run(String... args) throws Exception {
        userRepo.deleteAll();
        produtoRepo.deleteAll();
        
        User fulano = new User(null, "Fulano", "fulano@email.com");
        User ciclano = new User(null, "Ciclano", "ciclano@email.com");
        
        userRepo.saveAll(Arrays.asList(fulano, ciclano));
        
        Produto teclado = new Produto(null, "Teclado", "Teclado Slim", "Eletronicos", 49.99, new AutorDTO(fulano));
        
        Produto mouse = new Produto(null, "Mouse", "Mouse Gamer 3200DPI", "Eletronicos", 59.99, new AutorDTO(fulano));
        
        Produto monitor = new Produto(null, "Monitor", "Monitor Gamer 144hz Curvo", "Eletronicos", 599.99, new AutorDTO(ciclano));
        
        AvaliacaoDTO avaliacao1 = new AvaliacaoDTO(new AutorDTO(fulano),5,"Bom");
        AvaliacaoDTO avaliacao2 = new AvaliacaoDTO(new AutorDTO(ciclano),0,"Ruim");
        
        teclado.getAvaliacoes().add(avaliacao2);
        monitor.getAvaliacoes().add(avaliacao1);
        
        produtoRepo.saveAll(Arrays.asList(teclado, mouse, monitor));
        fulano.getProdutos().addAll(Arrays.asList(teclado, mouse));
        ciclano.getProdutos().addAll(Arrays.asList(monitor));
        userRepo.save(fulano);
        userRepo.save(ciclano);
        
    }
    
}
