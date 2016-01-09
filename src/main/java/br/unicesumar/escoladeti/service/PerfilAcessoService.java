package br.unicesumar.escoladeti.service;

import br.unicesumar.escoladeti.comando.ComandoSalvarPerfilAcesso;
import br.unicesumar.escoladeti.controller.DataPage;
import static br.unicesumar.escoladeti.controller.DataPage.pageRequestForAsc;
import br.unicesumar.escoladeti.entity.PerfilAcesso;
import br.unicesumar.escoladeti.repository.PerfilAcessoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilAcessoService {

    @Autowired
    private PerfilAcessoRepository perfilAcessoRepository;

    public PerfilAcesso salvar(ComandoSalvarPerfilAcesso comando) {
        PerfilAcesso.PerfilAcessoBuilder perfilAcessoBuilder = PerfilAcesso
                .builder()
                .nome(comando.getNome());
        PerfilAcesso perfilAcesso = perfilAcessoBuilder.build();

        return this.perfilAcessoRepository.save(perfilAcesso);
    }

    public PerfilAcesso atualizar(Long id, ComandoSalvarPerfilAcesso comando) {
        PerfilAcesso.PerfilAcessoBuilder perfilAcessoBuilder = PerfilAcesso
                .builder()
                .id(id)
                .nome(comando.getNome());
        PerfilAcesso perfilAcesso = perfilAcessoBuilder.build();
        return this.perfilAcessoRepository.save(perfilAcesso);
    }

    public void deletar(PerfilAcesso perfilAcesso) {
        this.perfilAcessoRepository.delete(perfilAcesso);
    }

    public PerfilAcesso getById(Long id) {
        return this.perfilAcessoRepository.findById(id);
    }

    public List<PerfilAcesso> getTodos() {
        return this.perfilAcessoRepository.findAll();
    }

    public DataPage<PerfilAcesso> paginar(Integer pagina) {
        return new DataPage<>(this.perfilAcessoRepository.findAll(pageRequestForAsc(pagina, "id")));
    }

    public DataPage<PerfilAcesso> buscar(String nome) {
        return new DataPage<>(this.perfilAcessoRepository.findByNomeContainingOrderByNomeAsc(nome, pageRequestForAsc(1, "nome")));
    }
}