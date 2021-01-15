package com.gft.desafio_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafio_api.model.Fornecedor;
import com.gft.desafio_api.model.FornecedorView;
import com.gft.desafio_api.repository.FornecedorRepository;
import com.gft.desafio_api.repository.ProdutoRepository;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Fornecedor atualizar(Long id, Fornecedor fornecedor) {
		Fornecedor fornecedorAtualizadoo= buscarFornecedorPeloId(id);
		
		BeanUtils.copyProperties(fornecedor, fornecedorAtualizadoo, "id");
		return fornecedorRepository.save(fornecedorAtualizadoo);
	}
	
	public Fornecedor buscarFornecedorPeloId(Long id) {
		Fornecedor fornecedorAtualizado = fornecedorRepository.getOne(id);
		if (fornecedorAtualizado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return fornecedorAtualizado;
	}
	
	public List<FornecedorView> getFornecedor(){
		List<FornecedorView>views = new ArrayList<>();
		
		List<Fornecedor>fornecedores = fornecedorRepository.findAll();
		for(Fornecedor forn : fornecedores){
			FornecedorView fornecedorView = new FornecedorView();
			fornecedorView.setId(forn.getId());
			fornecedorView.setCnpj(forn.getCnpj());
			fornecedorView.setNome(forn.getNome());
			fornecedorView.setProdutos(produtoRepository.findByFornecedor(forn));
			views.add(fornecedorView);
			
	}
		return views;

	}
}
	
	


