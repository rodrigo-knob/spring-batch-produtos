package br.com.produtobatch.produtobatch;

public class Produto {
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
