package br.unicesumar.escoladeti.comando;

import br.unicesumar.escoladeti.entity.Menu;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class ComandoSalvarSubMenu {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotNull(message = "Menu é obrigatório")
    private Menu menu;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}