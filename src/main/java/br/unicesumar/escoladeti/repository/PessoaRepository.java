package br.unicesumar.escoladeti.repository;

import br.unicesumar.escoladeti.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository  extends JpaRepository<Pessoa, Long>{
    
}
