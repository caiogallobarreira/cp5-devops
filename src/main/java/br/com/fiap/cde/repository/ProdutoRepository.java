package br.com.fiap.cde.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cde.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    Page<Produto> findByNomeContaining(String search, Pageable pageable);

}