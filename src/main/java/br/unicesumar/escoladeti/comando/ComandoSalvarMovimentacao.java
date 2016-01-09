package br.unicesumar.escoladeti.comando;

import br.unicesumar.escoladeti.entity.Pessoa;
import br.unicesumar.escoladeti.entity.PessoaFisica;
import br.unicesumar.escoladeti.entity.Produto;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class ComandoSalvarMovimentacao {
    
    @NotBlank
    private String tipo;
    
    @NotNull
    private Long quantidade;
    
    @NotNull
    private Date dataMovimentacao;
    
    @NotNull
    private Pessoa pessoaOrigem;
    
    @NotNull
    private Pessoa pessoaDestino;
    
    @NotNull
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
