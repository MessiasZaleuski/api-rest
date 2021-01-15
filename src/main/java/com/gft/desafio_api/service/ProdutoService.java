package com.gft.desafio_api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafio_api.model.Produto;
import com.gft.desafio_api.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto atualizar(Long id, Produto produto) {
		Produto produtoAtualizado = buscarProdutoPeloId(id);
		
		BeanUtils.copyProperties(produto, produtoAtualizado, "id");
		return produtoRepository.save(produtoAtualizado);
	}
	
	public Produto buscarProdutoPeloId(Long id) {
		Produto produtoAtualizado = produtoRepository.getOne(id);
		if (produtoAtualizado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return produtoAtualizado;
	}

	}

	


