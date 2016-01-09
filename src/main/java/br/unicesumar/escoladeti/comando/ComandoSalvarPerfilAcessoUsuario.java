package br.unicesumar.escoladeti.comando;

import br.unicesumar.escoladeti.entity.PerfilAcesso;
import br.unicesumar.escoladeti.entity.Usuario;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class ComandoSalvarPerfilAcessoUsuario {
    
    @NotNull(message = "Inicio vigência é obrigatório")
    private Date inicioVigencia;
    
    @NotNull(message = "Fim vigência é obrigatório")
    private Date fimVigencia;
    
    @NotNull(message = "Usuário é obrigatório")
    private PerfilAcesso perfilAcesso;
    
    @NotNull(message = "Usuário é obrigatório")
    private Usuario usuario;

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public PerfilAcesso getPerfilAcesso() {
        return perfilAcesso;
    }

    public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
        this.perfilAcesso = perfilAcesso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}