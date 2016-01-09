package br.unicesumar.escoladeti.controller;

import br.unicesumar.escoladeti.comando.ComandoSalvarPerfilItemAcesso;
import br.unicesumar.escoladeti.entity.ItemAcesso;
import br.unicesumar.escoladeti.entity.PerfilAcesso;
import br.unicesumar.escoladeti.entity.PerfilItemAcesso;
import br.unicesumar.escoladeti.service.PerfilItemAcessoService;
import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/perfilItemAcessoSource")
public class PerfilItemAcessoController implements Serializable {

    @Autowired
    private PerfilItemAcessoService perfilItemAcessoService;
    
    @RequestMapping(value = "/perfilItemAcesso/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PerfilItemAcesso getById(@PathVariable Long id) {
        return this.perfilItemAcessoService.getById(id);
    }

    @RequestMapping(value = "/perfilItemAcesso", method = RequestMethod.GET)
    @ResponseBody
    public List<PerfilItemAcesso> getTodos() {
        return this.perfilItemAcessoService.getTodos();
    }

    @RequestMapping(value = "/perfilItemAcesso/novo", method = RequestMethod.POST)
    @ResponseBody
    public PerfilAcesso salvarPerfilItemAcesso(@Valid @RequestBody ComandoSalvarPerfilItemAcesso comando) {
        this.perfilItemAcessoService.salvar(comando);
        return comando.getPerfilAcesso();
    }
    
    @RequestMapping(value = "/perfilItemAcesso/atualizar", method = RequestMethod.POST)
    @ResponseBody
    public PerfilAcesso atualizarPerfilItemAcesso(@Valid @RequestBody ComandoSalvarPerfilItemAcesso comando) {
        this.perfilItemAcessoService.atualizar(comando);
        return comando.getPerfilAcesso();
    }

    @RequestMapping(value = "/perfilItemAcesso", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletar(@RequestBody PerfilAcesso perfilAcesso) {
        this.perfilItemAcessoService.deletar(perfilAcesso);
        return "deleted";
    }
    
    @RequestMapping(value = "/perfilAcesso/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ItemAcesso> getItensDoPerfil(@PathVariable Long id) {
        return this.perfilItemAcessoService.getItensDoPerfil(id);
    }
}
