package br.unicesumar.escoladeti.service;

import br.unicesumar.escoladeti.controller.DataPage;
import static br.unicesumar.escoladeti.controller.DataPage.pageRequestForAsc;
import br.unicesumar.escoladeti.entity.CategoriaConta;
import br.unicesumar.escoladeti.repository.CategoriaContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.unicesumar.escoladeti.controller.DataPage.pageRequestForAsc;

@Service
public class CategoriaContaService {

    @Autowired
    private CategoriaContaRepository categoriaContaRepository;

    public CategoriaConta salvar(CategoriaConta categoriaConta) {
        return categoriaContaRepository.save(categoriaConta);
    }

    public List<CategoriaConta> listarTodosCategoriaContas() {
        return categoriaContaRepository.findAll();
    }

    public void deletar(CategoriaConta categoriaConta) {
        categoriaContaRepository.delete(categoriaConta);
    }

    public CategoriaConta getById(Long id) {
        return categoriaContaRepository.findById(id);
    }

    public DataPage<CategoriaConta> getTodos(Integer pagina) {
        return new DataPage<>(categoriaContaRepository.findAll(pageRequestForAsc(pagina, "titulo")));
    }


    /*   public DataPage<TipoConta> getByName(String titulo) {
           return new DataPage<TipoConta>(tipoContaRepository.findByTituloContainingOrderByTituloAsc(titulo, pageRequestForAsc(1, "titulo")));
       }*/

}
