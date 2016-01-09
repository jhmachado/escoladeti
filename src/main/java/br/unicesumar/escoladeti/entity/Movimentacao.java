package br.unicesumar.escoladeti.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao extends Entidade {

    private String tipo;

    private Long quantidade;

    @Temporal(TemporalType.DATE)
    private Date dataMovimentacao;

    @ManyToOne
    @JoinColumn(name = "id_origem")
    private Pessoa pessoaOrigem;

    @ManyToOne
    @JoinColumn(name = "id_destino")
    private Pessoa pessoaDestino;

    @OneToOne
    @JoinColumn(name = "produtoId", referencedColumnName = "id")
    private Produto produto;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Pessoa getPessoaOrigem() {
        return pessoaOrigem;
    }

    public void setPessoaOrigem(PessoaFisica pessoaOrigem) {
        this.pessoaOrigem = pessoaOrigem;
    }

    public Pessoa getPessoaDestino() {
        return pessoaDestino;
    }

    public void setPessoaDestino(PessoaFisica pessoaDestino) {
        this.pessoaDestino = pessoaDestino;
    }
}
