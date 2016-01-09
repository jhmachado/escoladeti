package br.unicesumar.escoladeti.service;

import br.unicesumar.escoladeti.comando.ComandoSalvarMenu;
import br.unicesumar.escoladeti.entity.Menu;
import br.unicesumar.escoladeti.entity.Menu.MenuBuilder;
import br.unicesumar.escoladeti.repository.MenuRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    
    @Autowired
    private MenuRepository menuRepository;
    
     public Menu salvar(ComandoSalvarMenu comando) {
        MenuBuilder menuBuilder = Menu
                .builder()
                .nome(comando.getNome());
        
        Menu menu = menuBuilder.build();
        return menuRepository.save(menu);
    }

    public List<Menu> getTodos() {
        return menuRepository.findAll();
    }

    public void deletar(Menu menu) {
        menuRepository.delete(menu);
    }

    public Menu getById(Long id) {
        return menuRepository.findById(id);
    }
}
