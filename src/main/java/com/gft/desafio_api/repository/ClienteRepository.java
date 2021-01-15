package com.gft.desafio_api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.desafio_api.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente>findByNome(String nome);


	

}
