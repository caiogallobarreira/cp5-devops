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
import br.com.fiap.cde.models.Estoque;
import br.com.fiap.cde.repository.EstoqueRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController @Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Estoque", description = "API de estoque")
@RequestMapping("/api/v1/estoque")
public class EstoqueController {

    @Autowired
    EstoqueRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler; 

    // Get All
    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String search, @PageableDefault(size = 5) Pageable pageable){
        var estoques = (search == null) ? repository.findAll(pageable) : repository.findByNomeContaining(search, pageable);
        return assembler.toModel(estoques.map(Estoque::toEntityModel));
    }

    // Get by Id
    @GetMapping("{id}")
    public ResponseEntity<Estoque> show(@PathVariable Long id){
        var estoqueOptional = getEstoque(id);
        log.info("Listando estoque: " + id);
        return ResponseEntity.ok(estoqueOptional);
    }

    // Post
    @PostMapping
    public ResponseEntity<EntityModel<Estoque>> create(@RequestBody @Valid Estoque estoque, BindingResult result){
        log.info("Estoque criado com sucesso! " + estoque);
        repository.save(estoque);
        return ResponseEntity.created(estoque.toEntityModel().getRequiredLink("self").toUri()).body(estoque.toEntityModel());
    }

    // Delete
    @DeleteMapping("{id}")
    public ResponseEntity<Estoque> delete(@PathVariable Long id){
        var estoqueOptional = getEstoque(id);
        log.info("Estoque deletado com sucesso! " + id);
        repository.delete(estoqueOptional);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Put
    @PutMapping("{id}")
    public ResponseEntity<Estoque> update(@PathVariable Long id, @RequestBody @Valid Estoque estoque, BindingResult result){
        getEstoque(id);
        log.info("Estoque atualizado com sucesso! " + estoque);
        estoque.setId(id);
        repository.save(estoque);
        return ResponseEntity.ok(estoque);
    }

    private Estoque getEstoque(Long id) {
        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Estoque não encontrado!"));
    }
}