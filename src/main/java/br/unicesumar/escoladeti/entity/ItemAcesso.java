package br.unicesumar.escoladeti.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import static org.parboiled.common.Preconditions.checkNotNull;

@Entity
public class ItemAcesso extends Entidade {
   
    private String nome;
    private String link;
    
    @OneToOne
    @JoinColumn(name = "id_submenu")
    private SubMenu subMenu;

    public ItemAcesso() {
    }

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
    
    public static ItemAcessoBuilder builder(){
        return new ItemAcessoBuilder();
    }
    
    public static class ItemAcessoBuilder{
        private Long id;
        private String nome;
        private String link;
        private SubMenu subMenu;
        
        public ItemAcessoBuilder(){
            
        }
        
        public ItemAcessoBuilder nome (String nome){
            this.nome = nome;
            return this;
        }
        
        public ItemAcessoBuilder link (String link){
            this.link = link;
            return this;
        }
        
        public ItemAcessoBuilder subMenu(SubMenu subMenu){
            this.subMenu = subMenu;
            return this;
        }
        
        public ItemAcessoBuilder id(Long id){
            this.id = id;
            return this;
        }
        
        public ItemAcesso build() {
            checkNotNull(this.nome, "Nome é obrigatório");
            checkNotNull(this.link, "Link é obrigatório");
            checkNotNull(this.subMenu, "Login é obrigatório");

            ItemAcesso itemAcesso = new ItemAcesso();
            itemAcesso.setNome(this.nome);
            itemAcesso.setLink(this.link);
            itemAcesso.setSubMenu(this.subMenu);
            
            if (id != null) {
                itemAcesso.setId(id);
            }

            return itemAcesso;
        }

    }
    
}
