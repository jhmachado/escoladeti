package br.unicesumar.escoladeti.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Validated
public class TipoConta extends Entidade {


    private static final long serialVersionUID = 1L;

    @NotNull
    @Column
    @NotEmpty
    private String titulo;

    @NotNull
    @Column
    @NotEmpty
    private int multiplicador;


    public TipoConta(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }
}
