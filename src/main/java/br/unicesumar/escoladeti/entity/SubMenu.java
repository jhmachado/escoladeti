package br.unicesumar.escoladeti.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import static org.parboiled.common.Preconditions.checkNotNull;

@Entity
public class SubMenu extends Entidade {

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_menu", referencedColumnName = "id")
    private Menu menu;

    public SubMenu() {
    }

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

    public static SubMenuBuilder builder() {
        return new SubMenuBuilder();
    }

    public static class SubMenuBuilder {

        private Long id;
        private String nome;
        private Menu menu;

        public SubMenuBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public SubMenuBuilder menu(Menu menu) {
            this.menu = menu;
            return this;
        }

        public SubMenu build() {
            checkNotNull(this.nome, "Nome é obrigatório");
            checkNotNull(this.menu, "Nome é obrigatório");

            SubMenu subMenu = new SubMenu();
            subMenu.setNome(this.nome);
            subMenu.setMenu(this.menu);

            if (this.id != null) {
                subMenu.setId(this.id);
            }

            return subMenu;
        }
    }

}
