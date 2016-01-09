package br.unicesumar.escoladeti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.escoladeti.entity.ItemAcesso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemAcessoRepository extends JpaRepository<ItemAcesso, Long> {

//    public List<ItemAcesso> findByNomeContainingOrderByNomeAsc(String nome);
    public Page<ItemAcesso> findByNomeContainingOrderByNomeAsc(String nome, Pageable pageable);

    public ItemAcesso findById(Long id);

}
