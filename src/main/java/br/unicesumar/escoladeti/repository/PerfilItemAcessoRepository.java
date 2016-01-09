package br.unicesumar.escoladeti.repository;

import br.unicesumar.escoladeti.entity.PerfilItemAcesso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilItemAcessoRepository  extends JpaRepository<PerfilItemAcesso, Long>{
    public PerfilItemAcesso findById(Long id);
    public List<PerfilItemAcesso> findByPerfilAcessoId(Long id);
//    public PerfilItemAcesso deletePerfilAcessoId(Long id);
}
