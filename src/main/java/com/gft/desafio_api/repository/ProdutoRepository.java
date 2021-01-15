package com.gft.desafio_api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gft.desafio_api.model.Fornecedor;
import com.gft.desafio_api.model.Produto;
import com.gft.desafio_api.model.Venda;

public interface ProdutoRepository  extends JpaRepository<Produto, Long>{
	List<Produto>findByFornecedor(Fornecedor fornecedor);
	List<Produto> findByVendas(Venda vendas);
    List<Produto >findByNome(String nome);

}
