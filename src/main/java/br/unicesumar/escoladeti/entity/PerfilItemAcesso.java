package br.unicesumar.escoladeti.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import static org.parboiled.common.Preconditions.checkNotNull;

@Entity
public class PerfilItemAcesso extends Entidade {

    @ManyToOne
    @JoinColumn(name = "id_perfilacesso", referencedColumnName = "id")
    private PerfilAcesso perfilAcesso;
    
    @ManyToOne
    @JoinColumn(name = "id_itemacesso", referencedColumnName = "id")
    private ItemAcesso itemAcesso;

    public PerfilItemAcesso() {
    }

    public PerfilAcesso getPerfilAcesso() {
        return perfilAcesso;
    }

    public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
        this.perfilAcesso = perfilAcesso;
    }

    public ItemAcesso getItemAcesso() {
        return itemAcesso;
    }

    public void setItemAcesso(ItemAcesso itemAcesso) {
        this.itemAcesso = itemAcesso;
    }
    
    public static PerfilItemAcessoBuilder builder(){
        return new PerfilItemAcessoBuilder();
    }
    
    public static class PerfilItemAcessoBuilder{
        private Long id;
        private PerfilAcesso perfilAcesso;
        private ItemAcesso itemAcesso;
        
        public PerfilItemAcessoBuilder(){
            
        }
        
        public PerfilItemAcessoBuilder id(Long id){
            this.id = id;
            return this;
        }
        
        public PerfilItemAcessoBuilder perfilAcesso (PerfilAcesso perfilAcesso){
            this.perfilAcesso = perfilAcesso;
            return this;
        }
        
        public PerfilItemAcessoBuilder itemAcesso(ItemAcesso itemAcesso){
            this.itemAcesso = itemAcesso;
            return this;
        } 
        
        public PerfilItemAcesso build() {
            checkNotNull(this.perfilAcesso, "Nome é obrigatório");
            checkNotNull(this.itemAcesso, "Login é obrigatório");

            PerfilItemAcesso perfilItemAcesso = new PerfilItemAcesso();
            perfilItemAcesso.setItemAcesso(this.itemAcesso);
            perfilItemAcesso.setPerfilAcesso(this.perfilAcesso);
            
            if (id != null) {
                perfilItemAcesso.setId(id);
            }

            return perfilItemAcesso;
        }
    }

}
