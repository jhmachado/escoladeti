package br.unicesumar.escoladeti.entity;

import javax.persistence.Entity;
import static org.parboiled.common.Preconditions.checkNotNull;

@Entity
public class PerfilAcesso extends Entidade {

    private String nome;

    public PerfilAcesso() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static PerfilAcessoBuilder builder() {
        return new PerfilAcessoBuilder();
    }

    public static class PerfilAcessoBuilder {

        private Long id;
        private String nome;

        public PerfilAcessoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public PerfilAcessoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PerfilAcesso build() {
            checkNotNull(this.nome, "Nome é obrigatório");

            PerfilAcesso perfilAcesso = new PerfilAcesso();
            perfilAcesso.setNome(this.nome);

            if (this.id != null) {
                perfilAcesso.setId(this.id);
            }

            return perfilAcesso;
        }
    }

}
