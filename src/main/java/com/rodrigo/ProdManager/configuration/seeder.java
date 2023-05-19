package com.rodrigo.ProdManager.configuration;

import com.rodrigo.ProdManager.domain.Categoria;
import com.rodrigo.ProdManager.domain.Produto;
import com.rodrigo.ProdManager.repository.CategoriaRepository;
import com.rodrigo.ProdManager.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class seeder implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    @Override
    public void run(String... args) throws Exception {


        var c1 = new Categoria(null, "Eletrônicos");
        var c2 = new Categoria(null, "Games");
        var c3 = new Categoria(null, "Moveis");

        categoriaRepository.saveAll(Arrays.asList(c1, c2, c3));

        var p1 = new Produto("Resident Evil 4 Remake", 230.00 );
        var p2 = new Produto( "Playstation 5", 230.00);
        var p3 = new Produto("Mesa Para Notebook", 230.00);


        p1.getCategorias().addAll(Arrays.asList(c1, c2));
        p2.getCategorias().addAll(Arrays.asList(c1));
        p3.getCategorias().addAll(Arrays.asList(c3));

        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));


    }
}
