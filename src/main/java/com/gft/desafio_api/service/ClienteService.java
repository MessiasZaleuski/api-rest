package com.gft.desafio_api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafio_api.model.Cliente;
import com.gft.desafio_api.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente clienteAtualizado = buscarClientePeloId(id);
		
		BeanUtils.copyProperties(cliente, clienteAtualizado, "id");
		return clienteRepository.save(clienteAtualizado);
	}
	
	public Cliente buscarClientePeloId(Long id) {
		Cliente clienteAtualizado = clienteRepository.getOne(id);
		if (clienteAtualizado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return clienteAtualizado;
	}
	

	}
	



