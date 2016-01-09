package br.unicesumar.escoladeti.controller;

import br.unicesumar.escoladeti.comando.ComandoSalvarItemAcesso;
import br.unicesumar.escoladeti.entity.ItemAcesso;
import br.unicesumar.escoladeti.service.ItemAcessoService;
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
@RequestMapping("/rest/itemAcessoSource")
public class ItemAcessoController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ItemAcessoService itemAcessoService;

    @RequestMapping(value = "/itemAcesso", method = RequestMethod.POST)
    @ResponseBody
    public ItemAcesso salvar(@Valid @RequestBody ComandoSalvarItemAcesso comando) {
        return this.itemAcessoService.salvar(comando);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ItemAcesso atualizar(@Valid @RequestBody ComandoSalvarItemAcesso comando,
            @PathVariable("id") Long id) {
        return itemAcessoService.atualizar(id, comando);
    }

    @RequestMapping(value = "/itemAcesso", method = RequestMethod.GET)
    @ResponseBody
    public List<ItemAcesso> getTodos() {
        return this.itemAcessoService.getTodos();
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    @ResponseBody
    public List<ItemAcesso> listar() {
        return this.itemAcessoService.getTodos();
    }

    @RequestMapping(value = "/itemAcesso/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ItemAcesso getById(@PathVariable Long id) {
        return this.itemAcessoService.getById(id);
    }

    @RequestMapping(value = {"/paginar/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<ItemAcesso> paginar(@PathVariable("pagina") Integer pagina) {
        System.out.println("Pagina que chegou " + pagina);
        return itemAcessoService.paginar(pagina);
    }

    @RequestMapping(value = "/itemAcesso", params = {"q"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<ItemAcesso> getPorNome(@RequestParam String q) {
        return itemAcessoService.buscar(q);
    }

    @RequestMapping(value = "/itemAcesso", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletar(@RequestBody ItemAcesso itemAcesso) {
        this.itemAcessoService.deletar(itemAcesso);
        return "Deleted";
    }
}
