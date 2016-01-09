package br.unicesumar.escoladeti.comando;

import br.unicesumar.escoladeti.entity.SubMenu;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class ComandoSalvarItemAcesso {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "Link é obrigatório")
    private String link;

    @NotNull(message = "Submenu é obrigatório")
    private SubMenu subMenu;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }
}