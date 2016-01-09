package br.unicesumar.escoladeti.controller;

import br.unicesumar.escoladeti.comando.ComandoSalvarPerfilAcesso;
import br.unicesumar.escoladeti.entity.PerfilAcesso;
import br.unicesumar.escoladeti.service.PerfilAcessoService;
import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/perfilAcessoSource")
public class PerfilAcessoController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PerfilAcessoService perfilService;

    @RequestMapping(value = "/perfilAcesso", method = RequestMethod.POST)
    @ResponseBody
    public PerfilAcesso salvar(@Valid @RequestBody ComandoSalvarPerfilAcesso comando) {
        return this.perfilService.salvar(comando);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public PerfilAcesso atualizar(@Valid @RequestBody ComandoSalvarPerfilAcesso comando,
            @PathVariable("id") Long id) {
        return perfilService.atualizar(id, comando);
    }

    @RequestMapping(value = "/perfilAcesso/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PerfilAcesso getById(@PathVariable Long id) {
        return this.perfilService.getById(id);
    }

    @RequestMapping(value = "/perfilAcesso", method = RequestMethod.GET)
    @ResponseBody
    public List<PerfilAcesso> getTodos() {
        return this.perfilService.getTodos();
    }

    @RequestMapping(value = {"/paginar/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<PerfilAcesso> paginar(@PathVariable Integer pagina) {
        return perfilService.paginar(pagina);
    }

    @RequestMapping(value = "/perfilAcesso", params = {"q"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<PerfilAcesso> getPorNome(@RequestParam String q) {
        return perfilService.buscar(q);
    }

    @RequestMapping(value = "/perfilAcesso", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletar(@RequestBody PerfilAcesso perfilAcesso) {
        this.perfilService.deletar(perfilAcesso);
        return "deleted";
    }
}