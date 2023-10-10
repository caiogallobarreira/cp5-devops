package br.com.fiap.cde.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.cde.exceptions.RestNotFoundException;
import br.com.fiap.cde.models.Produto;
import br.com.fiap.cde.repository.EstoqueRepository;
import br.com.fiap.cde.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController @Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Produto", description = "API de produtos")
@RequestMapping("/api/v1/produto")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;
    
    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler; 

    // Get ALL
    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String search, @PageableDefault(size = 5) Pageable pageable){
        var estoques = (search == null) ? produtoRepository.findAll(pageable) : produtoRepository.findByNomeContaining(search, pageable);
        return assembler.toModel(estoques.map(Produto::toEntityModel));
    }
    
    // Get by Id
    @GetMapping("{id}")
    public ResponseEntity<Produto> show(@PathVariable Long id){
        var produtoOptional = getProduto(id);
        log.info("Listando produto: " + id);
        return ResponseEntity.ok(produtoOptional);
    }

    // Post
    @PostMapping
    public ResponseEntity<EntityModel<Produto>> create(@RequestBody @Valid Produto produto, BindingResult result){
        log.info("Produto criado com sucesso! " + produto);
        produtoRepository.save(produto);
        produto.setEstoque(estoqueRepository.findById(produto.getEstoque().getId()).get());
        return ResponseEntity.created(produto.toEntityModel().getRequiredLink("self").toUri()).body(produto.toEntityModel());
    }

    // Delete
    @DeleteMapping("{id}")
    public ResponseEntity<Produto> delete(@PathVariable Long id){
        var produtoOptional = getProduto(id);
        log.info("Produto deletado com sucesso! " + id);
        produtoRepository.delete(produtoOptional);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Put
    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody @Valid Produto produto, BindingResult result){
        getProduto(id);
        log.info("Produto atualizado com sucesso! " + produto);
        produto.setId(id);
        produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }

    private Produto getProduto(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Produto não encontrado!"));
    }
}