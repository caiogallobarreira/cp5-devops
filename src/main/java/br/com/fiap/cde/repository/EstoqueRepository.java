package br.com.fiap.cde.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.cde.models.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

    Page<Estoque> findByNomeContaining(String search, Pageable pageable);}