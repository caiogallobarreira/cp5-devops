package br.com.fiap.cde.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.cde.models.Estoque;
import br.com.fiap.cde.models.Produto;
import br.com.fiap.cde.repository.EstoqueRepository;
import br.com.fiap.cde.repository.ProdutoRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Override
    public void run(String... args) throws Exception {
        estoqueRepository.saveAll(List.of(
            new Estoque(1L, "Estoque 1", "Descricao do estoque 1"),
            new Estoque(2L, "Estoque 2", "Descricao do estoque 2"),
            new Estoque(3L, "Estoque 3", "Descricao do estoque 3"),
            new Estoque(4L, "Estoque 4", "Descricao do estoque 4")
        ));

        produtoRepository.saveAll(List.of(
            Produto.builder().nome("Produto 1").descricao("Descricao do produto 1").estoque(estoqueRepository.findById(1L).get()).imagemUrl("https://example.com").quantidade(10).quantidadeMinima(2).build(),
            Produto.builder().nome("Produto 2").descricao("Descricao do produto 2").estoque(estoqueRepository.findById(1L).get()).imagemUrl("https://example.com").quantidade(10).quantidadeMinima(2).build(),
            Produto.builder().nome("Produto 3").descricao("Descricao do produto 3").estoque(estoqueRepository.findById(2L).get()).imagemUrl("https://example.com").quantidade(10).quantidadeMinima(2).build(),
            Produto.builder().nome("Produto 4").descricao("Descricao do produto 4").estoque(estoqueRepository.findById(2L).get()).imagemUrl("https://example.com").quantidade(10).quantidadeMinima(2).build(),
            Produto.builder().nome("Produto 5").descricao("Descricao do produto 5").estoque(estoqueRepository.findById(3L).get()).imagemUrl("https://example.com").quantidade(10).quantidadeMinima(2).build(),
            Produto.builder().nome("Produto 6").descricao("Descricao do produto 6").estoque(estoqueRepository.findById(3L).get()).imagemUrl("https://example.com").quantidade(10).quantidadeMinima(2).build(),
            Produto.builder().nome("Produto 7").descricao("Descricao do produto 7").estoque(estoqueRepository.findById(4L).get()).imagemUrl("https://example.com").quantidade(10).quantidadeMinima(2).build(),
            Produto.builder().nome("Produto 8").descricao("Descricao do produto 8").estoque(estoqueRepository.findById(4L).get()).imagemUrl("https://example.com").quantidade(10).quantidadeMinima(2).build()
        ));
         
    }
    
}
