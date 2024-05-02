package br.com.produtobatch.infra.repository;

import br.com.produtobatch.domain.enitity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
