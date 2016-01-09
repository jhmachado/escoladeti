package br.unicesumar.escoladeti.service;

import br.unicesumar.escoladeti.comando.ComandoSalvarPerfilItemAcesso;
import br.unicesumar.escoladeti.entity.ItemAcesso;
import br.unicesumar.escoladeti.entity.PerfilAcesso;
import br.unicesumar.escoladeti.entity.PerfilItemAcesso;
import br.unicesumar.escoladeti.entity.PerfilItemAcesso.PerfilItemAcessoBuilder;
import br.unicesumar.escoladeti.repository.ItemAcessoRepository;
import br.unicesumar.escoladeti.repository.PerfilAcessoRepository;
import br.unicesumar.escoladeti.repository.PerfilItemAcessoRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilItemAcessoService implements Serializable {

    @Autowired
    private PerfilItemAcessoRepository perfilItemAcessoRepository;

    @Autowired
    private ItemAcessoRepository itemAcessoRepository;

    @Autowired
    private PerfilAcessoRepository perfilAcessoRepository;

    public PerfilAcesso salvar(ComandoSalvarPerfilItemAcesso comando) {
        PerfilAcesso novoPerfil = this.salvarPerfilAcesso(comando.getPerfilAcesso());
        for (Long id : comando.getItemAcesso()) {
            ItemAcesso itemAcesso = itemAcessoRepository.findById(id);
            PerfilItemAcessoBuilder perfilItemAcessoBuilder = PerfilItemAcesso
                    .builder()
                    .itemAcesso(itemAcesso)
                    .perfilAcesso(novoPerfil);
            PerfilItemAcesso perfilItemAcesso = perfilItemAcessoBuilder.build();
            this.perfilItemAcessoRepository.save(perfilItemAcesso);
        }
        return novoPerfil;
    }
    
    public PerfilAcesso atualizar(ComandoSalvarPerfilItemAcesso comando) {
//        this.perfilItemAcessoRepository.deletePerfilAcessoId(comando.getPerfilAcesso().getId());
        PerfilAcesso novoPerfil = this.salvarPerfilAcesso(comando.getPerfilAcesso());
        for (Long id : comando.getItemAcesso()) {
            ItemAcesso itemAcesso = itemAcessoRepository.findById(id);
            PerfilItemAcessoBuilder perfilItemAcessoBuilder = PerfilItemAcesso
                    .builder()
                    .itemAcesso(itemAcesso)
                    .perfilAcesso(novoPerfil);
            PerfilItemAcesso perfilItemAcesso = perfilItemAcessoBuilder.build();
            this.perfilItemAcessoRepository.save(perfilItemAcesso);
        }
        return novoPerfil;
    }
    
    public PerfilAcesso salvarPerfilAcesso(PerfilAcesso perfilAcesso) {
        return this.perfilAcessoRepository.save(perfilAcesso);
    }

    public void deletar(PerfilAcesso perfilAcesso) {
        List<PerfilItemAcesso> cadastros = this.perfilItemAcessoRepository.findByPerfilAcessoId(perfilAcesso.getId());
        for (PerfilItemAcesso perfilItemAcesso : cadastros) {
            this.perfilItemAcessoRepository.delete(perfilItemAcesso);
        }
        this.perfilAcessoRepository.delete(perfilAcesso);
    }

    public PerfilItemAcesso getById(Long id) {
        return this.perfilItemAcessoRepository.findById(id);
    }

    public List<PerfilItemAcesso> getTodos() {
        return this.perfilItemAcessoRepository.findAll();
    }

    public List<ItemAcesso> getItensDoPerfil(Long id) {
        List<PerfilItemAcesso> itens =  this.perfilItemAcessoRepository.findByPerfilAcessoId(id);
        List<ItemAcesso> itensDoPerfil = new ArrayList<ItemAcesso>();
        for (PerfilItemAcesso perfilItemAcesso : itens) {
            itensDoPerfil.add(this.itemAcessoRepository.findById(perfilItemAcesso.getItemAcesso().getId()));
        }
        return itensDoPerfil;
    }
}
