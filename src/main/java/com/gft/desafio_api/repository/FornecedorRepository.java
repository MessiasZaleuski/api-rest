package com.gft.desafio_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gft.desafio_api.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
	 List<Fornecedor>findByNome(String nome);
	
   
}
