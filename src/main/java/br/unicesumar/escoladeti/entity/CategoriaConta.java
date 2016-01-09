package br.unicesumar.escoladeti.entity;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Validated
public class CategoriaConta extends Entidade{


    private static final long serialVersionUID = 1L;

    @NotNull
    @Column
    @NotEmpty
    private String titulo;


    @NotNull
    @Column
    @NotEmpty
    private int importancia;

    @NotNull
    @Column
    @NotEmpty
    private int fixa;

    public CategoriaConta(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }

    public int getFixa() {
        return fixa;
    }

    public void setFixa(int fixa) {
        this.fixa = fixa;
    }
}
