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
import com.gft.desafio_api.model.Cliente;
import com.gft.desafio_api.repository.ClienteRepository;
import com.gft.desafio_api.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/clientes")
@Api(tags="Clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ClienteService clienteService;
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
	allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping
	@ApiOperation("Lista todos os clientes")
	public List<Cliente> listar(){
		return clienteRepository.findAll();
		
		}
	
	@GetMapping("/asc")
	@ApiOperation("Listar todos clientes em ordem alfabética crescente por nome")
	@OrderBy
	public List<Cliente> listarOrdemCrescente(){
		return clienteRepository.findAll(Sort.by(JpaSort.Direction.ASC, "nome"));
	}
	
	@GetMapping("/desc")
	@ApiOperation("Listar as casas em ordem alfabética decrescente por nome")
	@OrderBy
	public List<Cliente> listarOrdemDecrescente(){
		return clienteRepository.findAll(Sort.by(JpaSort.Direction.DESC, "nome"));
	}
	
	@GetMapping("/nome/{nome}")
	@ApiOperation("Listar por nome")
	public List<Cliente>listarPorNome(@PathVariable("nome")String nome){	
    return clienteRepository.findByNome(nome);
	}
	
	@PostMapping
	@ApiOperation("Inserir clientes")
	/*MOSTRAR 201 CREATED E O LOCATION COM ALGUMAS INFORMAÇÕES 
	 * NO CAMPO HEADERS APÓS CADASTRAR UM CLIENTE*/
	//ANOTAÇÃO @Valid É PARA VALIDAR O @NotNull DO NOME DA CLASSE CLIENTE
	public ResponseEntity<Cliente> criar(
				@ApiParam(name = "corpo", value = "Representação de um novo(a) cliente")
				@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSalva = clienteRepository.save(cliente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalva);		
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Buscar por ID")
	public Cliente buscarPeloId(
			@ApiParam(value ="ID de um cliente", example = "1")
			@PathVariable Long id) {
		if (clienteRepository.existsById(id)) {
		}else {
			throw new EmptyResultDataAccessException(1);
		}
		
		return this.clienteRepository.findById(id).orElse(null);
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Atualizar clientes")
	public ResponseEntity<Cliente> atualizar(
			@ApiParam(value = "ID de um cliente(a)")
			@PathVariable Long id, 
			@ApiParam(name = "corpo", value="Representação de um novo(a) cliente com os novos dados")
			@Valid @RequestBody Cliente cliente) {
		if (clienteRepository.existsById(id)) {
		}else {
			throw new EmptyResultDataAccessException(1);
		}
		
		Cliente clienteAtualizado = clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(clienteAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Remover clientes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(
			@ApiParam(value = "ID de um cliente", example ="1")
			@PathVariable Long id) {
	 Cliente cliente = new Cliente();
	 cliente.setId(id);
	 this.clienteRepository.delete(cliente);
	
	}
}
