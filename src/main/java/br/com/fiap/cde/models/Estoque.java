package br.com.fiap.cde.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.cde.controllers.EstoqueController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Estoque {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @NotNull @Size(min = 3, max = 255, message = "A descrição deve ter entre 3 e 50 caracteres")
    private String descricao;

    public EntityModel<Estoque> toEntityModel(){
      return EntityModel.of(
        this,
        linkTo(methodOn(EstoqueController.class).show(this.getId())).withSelfRel(),
        linkTo(methodOn(EstoqueController.class).delete(this.getId())).withRel("delete"),
        linkTo(methodOn(EstoqueController.class).index(null, Pageable.unpaged())).withRel("all")      
      );
    };
}