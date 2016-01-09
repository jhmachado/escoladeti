package br.unicesumar.escoladeti.controller;

import br.unicesumar.escoladeti.entity.TipoConta;
import br.unicesumar.escoladeti.service.TipoContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@Controller
@RequestMapping("/rest/tipoContaSource")
public class TipoContaController implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    private TipoContaService tipoContaService;

    @RequestMapping(value = "/TipoConta", method = RequestMethod.POST)
    @ResponseBody
    public TipoConta salvar(@RequestBody TipoConta tipoConta) throws Exception {
        return this.tipoContaService.salvar(tipoConta);
    }

    @RequestMapping(value = "/TipoConta/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TipoConta getById(@PathVariable Long id) {
        return tipoContaService.getById(id);
    }

    @RequestMapping(value = "/TipoConta", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<TipoConta> getTodos() {
        return tipoContaService.getTodos(1);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<TipoConta> listarTipoConta(@PathVariable Integer pagina) {
        return tipoContaService.getTodos(pagina);
    }

    @RequestMapping(value = "/TipoConta", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletar(@RequestBody TipoConta tipoConta) {
        tipoContaService.deletar(tipoConta);
        return "deleted";
    }

    @RequestMapping(value = "/TipoConta", method = RequestMethod.PUT)
    @ResponseBody
    public TipoConta editar(@RequestBody TipoConta tipoConta) {
        TipoConta tipoContaEditado = this.tipoContaService.getById(tipoConta.getId());
        return tipoContaService.salvar(tipoContaEditado);
    }
}
