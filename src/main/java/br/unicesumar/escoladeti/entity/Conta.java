package br.unicesumar.escoladeti.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Validated
public class Conta  extends Entidade {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column
    @NotEmpty
    private String titulo;

    @NotNull
    @Column
    private double valor;

    @NotNull
    @Column
    private TipoConta tipoConta;

    @NotNull
    @Column
    private CategoriaConta categoriaConta;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public CategoriaConta getCategoriaConta() {
        return categoriaConta;
    }

    public void setCategoriaConta(CategoriaConta categoriaConta) {
        this.categoriaConta = categoriaConta;
    }
}
