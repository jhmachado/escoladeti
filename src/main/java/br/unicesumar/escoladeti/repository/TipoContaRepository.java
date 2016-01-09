package br.unicesumar.escoladeti.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.unicesumar.escoladeti.entity.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoContaRepository  extends JpaRepository<TipoConta, Long> {

    public TipoConta findById(Long id);

    public Page<TipoConta> findByTituloContainingOrderByTituloAsc(String titulo, Pageable pageable);

}
