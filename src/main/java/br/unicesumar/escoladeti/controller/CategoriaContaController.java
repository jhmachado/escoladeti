package br.unicesumar.escoladeti.controller;


import br.unicesumar.escoladeti.entity.CategoriaConta;
import br.unicesumar.escoladeti.service.CategoriaContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@Controller
@RequestMapping("/rest/categoriaContaSource")
public class CategoriaContaController  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    private CategoriaContaService categoriaContaService;

    @RequestMapping(value = "/CategoriaConta", method = RequestMethod.POST)
    @ResponseBody
    public CategoriaConta salvar(@RequestBody CategoriaConta categoriaConta) throws Exception {
        return this.categoriaContaService.salvar(categoriaConta);
    }

    @RequestMapping(value = "/CategoriaConta/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CategoriaConta getById(@PathVariable Long id) {
        return categoriaContaService.getById(id);
    }

    @RequestMapping(value = "/CategoriaConta", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<CategoriaConta> getTodos() {
        return categoriaContaService.getTodos(1);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<CategoriaConta> listarCategoriaConta(@PathVariable Integer pagina) {
        return categoriaContaService.getTodos(pagina);
    }

    @RequestMapping(value = "/CategoriaConta", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletar(@RequestBody CategoriaConta categoriaConta) {
        categoriaContaService.deletar(categoriaConta);
        return "deleted";
    }

    @RequestMapping(value = "/CategoriaConta", method = RequestMethod.PUT)
    @ResponseBody
    public CategoriaConta editar(@RequestBody CategoriaConta categoriaConta) {
        CategoriaConta categoriaContaEditado = this.categoriaContaService.getById(categoriaConta.getId());
        return categoriaContaService.salvar(categoriaContaEditado);
    }

}
