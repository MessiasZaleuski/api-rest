package com.gft.desafio_api.resource;

import java.util.List;

import javax.persistence.OrderBy;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.desafio_api.event.RecursoCriadoEvent;
import com.gft.desafio_api.model.Fornecedor;
import com.gft.desafio_api.model.FornecedorView;
import com.gft.desafio_api.repository.FornecedorRepository;
import com.gft.desafio_api.service.FornecedorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/fornecedor")
@Api(tags="Fornecedor")
public class FornecedorResource {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
	allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping
	@ApiOperation("Listar os fornecedores")
	public List<FornecedorView> listar(){
		return fornecedorService.getFornecedor();
		}
	
	@GetMapping("/asc")
	@ApiOperation("Listar todos os fornecedores em ordem alfabética crescente por nome")
	@OrderBy
	public List<Fornecedor> listarOrdemCrescente(){
		return fornecedorRepository.findAll(Sort.by(JpaSort.Direction.ASC, "nome"));
	}
	
	@GetMapping("/desc")
	@ApiOperation("Listar as casas em ordem alfabética decrescente por nome")
	@OrderBy
	public List<Fornecedor> listarOrdemDecrescente(){
		return fornecedorRepository.findAll(Sort.by(JpaSort.Direction.DESC, "nome"));
	}
	
	@GetMapping("/nome/{nome}")
	@ApiOperation("Listar por nome")
	public List<Fornecedor>listarPorNome(@PathVariable("nome")String nome){	
    return fornecedorRepository.findByNome(nome);
	}
	
	@PostMapping
	@ApiOperation("Inserir fornecedor")
	public ResponseEntity<Fornecedor> criar(
			@ApiParam(name = "corpo", value = "Representação de um novo(a) fornecedor")
			@Valid @RequestBody Fornecedor fornecedor, HttpServletResponse response) {
	Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
	publisher.publishEvent(new RecursoCriadoEvent(this, response, fornecedorSalvo.getId()));
	return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);		
}
	
	@GetMapping("/{id}")
	@ApiOperation("Buscar por ID")
	public Fornecedor buscarPeloId(@PathVariable Long id) {
		if (fornecedorRepository.existsById(id)) {
		}else {
			throw new EmptyResultDataAccessException(1);
		}
		
		return this.fornecedorRepository.findById(id).orElse(null);
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Atualizar fornecedor")
	public ResponseEntity<Fornecedor> atualizar(
			@ApiParam(value = "ID de um fornecedor")
			@PathVariable Long id, 
			@ApiParam(name = "corpo", value="Representação de um novo fornecedor com os novos dados")
			@Valid @RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorAtualizado = fornecedorService.atualizar(id, fornecedor);
		return ResponseEntity.ok(fornecedorAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Excluir fornecedor")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
	 Fornecedor fornecedor = new Fornecedor();
	 fornecedor.setId(id);
	 this.fornecedorRepository.delete(fornecedor);

}
}
