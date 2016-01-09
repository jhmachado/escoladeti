package br.unicesumar.escoladeti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.unicesumar.escoladeti.entity.CategoriaConta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaContaRepository extends JpaRepository<CategoriaConta, Long> {

    public CategoriaConta findById(Long id);

    public Page<CategoriaConta> findByTituloContainingOrderByTituloAsc(String titulo, Pageable pageable);

}
