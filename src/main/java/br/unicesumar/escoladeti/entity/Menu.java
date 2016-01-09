package br.unicesumar.escoladeti.entity;

import javax.persistence.Entity;
import static org.parboiled.common.Preconditions.checkNotNull;

@Entity
public class Menu extends Entidade {

    private String nome;

    public Menu() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static MenuBuilder builder() {
        return new MenuBuilder();
    }

    public static class MenuBuilder {

        private Long id;
        private String nome;

        public MenuBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Menu build() {
            checkNotNull(this.nome, "Nome é obrigatório");

            Menu menu = new Menu();
            menu.setNome(nome);

            if (this.id != null) {
                menu.setId(this.id);
            }

            return menu;
        }
    }
}
