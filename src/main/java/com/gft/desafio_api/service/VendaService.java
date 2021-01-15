package com.gft.desafio_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafio_api.model.Venda;
import com.gft.desafio_api.model.VendaView;
import com.gft.desafio_api.repository.ProdutoRepository;
import com.gft.desafio_api.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Venda atualizar(Long id, Venda venda) {
		Venda vendaAtualizado = buscarVendaPeloId(id);
		
		BeanUtils.copyProperties(venda, vendaAtualizado, "id");
		return vendaRepository.save(vendaAtualizado);
	}
	
	public Venda buscarVendaPeloId(Long id) {
		Venda vendaAtualizado = vendaRepository.getOne(id);
		if (vendaAtualizado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return vendaAtualizado;
	}
	
	public List<VendaView> getVenda(){
		List<VendaView>views = new ArrayList<>();
		
		List<Venda>vendas = vendaRepository.findAll();
		for(Venda forn : vendas){
			VendaView vendaView = new VendaView();
			vendaView.setId(forn.getId());
			vendaView.setCliente(forn.getCliente());
			vendaView.setDataCompra(forn.getDataCompra());
			vendaView.setFornecedor(forn.getFornecedor());
			vendaView.setProdutos(produtoRepository.findByVendas(forn));
			vendaView.setTotalCompra(forn.getTotalCompra());
			views.add(vendaView);
			
	}
		return views;

	}

	}
	
	
	


