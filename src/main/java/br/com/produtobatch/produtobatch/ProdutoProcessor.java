package br.com.produtobatch.produtobatch;

import org.springframework.batch.item.ItemProcessor;

public class ProdutoProcessor implements ItemProcessor<Produto, Produto> {
    @Override
    public Produto process(Produto item) throws Exception {
        return item;
    }
}
