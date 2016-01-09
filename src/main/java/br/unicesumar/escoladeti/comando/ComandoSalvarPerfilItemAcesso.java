package br.unicesumar.escoladeti.comando;

import br.unicesumar.escoladeti.entity.ItemAcesso;
import br.unicesumar.escoladeti.entity.PerfilAcesso;
import java.util.List;
import javax.validation.constraints.NotNull;

public class ComandoSalvarPerfilItemAcesso {

    @NotNull(message = "Perfil de acesso é obrigatório")
    private PerfilAcesso perfilAcesso;

    @NotNull(message = "Itens de acesso são obrigatorios")
    private List<Long> itemAcesso;

    public ComandoSalvarPerfilItemAcesso() {
    }
    
    public PerfilAcesso getPerfilAcesso() {
        return perfilAcesso;
    }

    public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
        this.perfilAcesso = perfilAcesso;
    }

    public List<Long> getItemAcesso() {
        return itemAcesso;
    }

    public void setItemAcesso(List<Long> itemAcesso) {
        this.itemAcesso = itemAcesso;
    }
}
