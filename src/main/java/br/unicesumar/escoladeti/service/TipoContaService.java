package br.unicesumar.escoladeti.service;

import br.unicesumar.escoladeti.controller.DataPage;
import static br.unicesumar.escoladeti.controller.DataPage.pageRequestForAsc;
import br.unicesumar.escoladeti.entity.TipoConta;
import br.unicesumar.escoladeti.repository.TipoContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoContaService {

    @Autowired
    private TipoContaRepository tipoContaRepository;

    public TipoConta salvar(TipoConta tipoConta) {
        return tipoContaRepository.save(tipoConta);
    }

    public List<TipoConta> listarTodosTipoContas() {
        return tipoContaRepository.findAll();
    }

    public void deletar(TipoConta tipoConta) {
        tipoContaRepository.delete(tipoConta);
    }

    public TipoConta getById(Long id) {
        return tipoContaRepository.findById(id);
    }

    public DataPage<TipoConta> getTodos(Integer pagina) {
        return new DataPage<>(tipoContaRepository.findAll(pageRequestForAsc(pagina, "titulo")));
    }


    /*   public DataPage<TipoConta> getByName(String titulo) {
           return new DataPage<TipoConta>(tipoContaRepository.findByTituloContainingOrderByTituloAsc(titulo, pageRequestForAsc(1, "titulo")));
       }*/

}
