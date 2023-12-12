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
        
        User teste = new User(null, "Teste nome", "teste@email.com");
        User admin = new User(null, "Admin teste", "admin@email.com");
        
        userRepo.saveAll(Arrays.asList(teste, admin));
        
        Produto teclado = new Produto(null, "Teclado", "Teclado Slim", "Eletronicos", 49.99, new AutorDTO(teste));
        
        Produto mouse = new Produto(null, "Mouse", "Mouse Gamer 3200DPI", "Eletronicos", 59.99, new AutorDTO(teste));
        
        Produto monitor = new Produto(null, "Monitor", "Mouse Gamer 144hz Curvo", "Eletronicos", 599.99, new AutorDTO(admin));
        
        AvaliacaoDTO avaliacao1 = new AvaliacaoDTO(new AutorDTO(teste),10,"Bom");
        AvaliacaoDTO avaliacao2 = new AvaliacaoDTO(new AutorDTO(admin),0,"Ruim");
        
        teclado.getAvaliacoes().add(avaliacao1);
        monitor.getAvaliacoes().add(avaliacao2);
        
        produtoRepo.saveAll(Arrays.asList(teclado, mouse, monitor));
        teste.getProdutos().addAll(Arrays.asList(teclado, mouse));
        admin.getProdutos().addAll(Arrays.asList(monitor));
        userRepo.save(teste);
        userRepo.save(admin);
        
    }
    
}
