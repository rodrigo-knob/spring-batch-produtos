package br.com.produtobatch.domain.enitity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Produto {

    @Id
    private Integer id;
    private String descricao;
    private Integer quantidadeEstoque;
    private Double valor;

    public Produto() {
    }

    public Produto(String descricao, Integer quantidadeEstoque, Double valor) {
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
