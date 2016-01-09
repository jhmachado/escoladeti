package br.unicesumar.escoladeti.repository;

import br.unicesumar.escoladeti.entity.PerfilAcesso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, Long>{
    
    public PerfilAcesso findById(Long id);
    public Page<PerfilAcesso> findByNomeContainingOrderByNomeAsc(String nome, Pageable pageable);
}
