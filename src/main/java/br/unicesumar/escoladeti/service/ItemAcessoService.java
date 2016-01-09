package br.unicesumar.escoladeti.service;

import br.unicesumar.escoladeti.comando.ComandoSalvarItemAcesso;
import br.unicesumar.escoladeti.controller.DataPage;
import static br.unicesumar.escoladeti.controller.DataPage.pageRequestForAsc;
import br.unicesumar.escoladeti.entity.ItemAcesso;
import br.unicesumar.escoladeti.entity.ItemAcesso.ItemAcessoBuilder;
import br.unicesumar.escoladeti.repository.ItemAcessoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemAcessoService {

    @Autowired
    private ItemAcessoRepository itemAcessoRespository;

    public ItemAcesso salvar(ComandoSalvarItemAcesso comando) {
        ItemAcessoBuilder itemAcessoBuilder = ItemAcesso
                .builder()
                .nome(comando.getNome())
                .link(comando.getLink())
                .subMenu(comando.getSubMenu());
        ItemAcesso itemAcesso = itemAcessoBuilder.build();
        System.out.println("Salvando " + itemAcesso.getId() + itemAcesso.getNome() );
        return this.itemAcessoRespository.save(itemAcesso);
    }

    public DataPage<ItemAcesso> paginar(Integer pagina) {
        return new DataPage<>(itemAcessoRespository.findAll(pageRequestForAsc(pagina, "id")));
    }

    public List<ItemAcesso> getTodos() {
        return this.itemAcessoRespository.findAll();
    }

    public ItemAcesso getById(Long id) {
        return this.itemAcessoRespository.findById(id);
    }

    public void deletar(ItemAcesso itemAcesso) {
        this.itemAcessoRespository.delete(itemAcesso);
    }

    public ItemAcesso atualizar(Long id, ComandoSalvarItemAcesso comando) {
        ItemAcessoBuilder itemAcessoBuilder = ItemAcesso
                .builder()
                .id(id)
                .nome(comando.getNome())
                .link(comando.getLink())
                .subMenu(comando.getSubMenu());
        ItemAcesso itemAcesso = itemAcessoBuilder.build();
        return this.itemAcessoRespository.save(itemAcesso);
    }

    public DataPage<ItemAcesso> buscar(String nome) {
        return new DataPage<>(itemAcessoRespository.findByNomeContainingOrderByNomeAsc(nome, pageRequestForAsc(1, "nome")));
    }
}
