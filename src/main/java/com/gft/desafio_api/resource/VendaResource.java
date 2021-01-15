package com.gft.desafio_api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
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
import com.gft.desafio_api.model.Venda;
import com.gft.desafio_api.model.VendaView;
import com.gft.desafio_api.repository.VendaRepository;
import com.gft.desafio_api.service.VendaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/vendas")
@Api(tags="Vendas")
public class VendaResource {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private VendaService vendaService;
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
	allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping
	@ApiOperation("Listar todas vendas")
	public List<VendaView> listar(){
		return vendaService.getVenda();
		}

	@PostMapping
	@ApiOperation("Inserir venda")
	public ResponseEntity<Venda> criar(@Valid @RequestBody Venda venda, HttpServletResponse response) {
		Venda vendaSalva = vendaRepository.save(venda);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, vendaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);	
	}

	@GetMapping("/{id}")
	@ApiOperation("Buscar por ID")
	public Venda buscarPeloId(@PathVariable Long id) {
	if (vendaRepository.existsById(id)) {
	}else {
		throw new EmptyResultDataAccessException(1);
	}
	
	return this.vendaRepository.findById(id).orElse(null);
}
	
	@PutMapping("/{id}")
	@ApiOperation("Atualizar venda")
	public ResponseEntity<Venda> atualizar(@PathVariable Long id, @Valid @RequestBody Venda venda) {
		Venda vendaAtualizado = vendaService.atualizar(id, venda);
		return ResponseEntity.ok(vendaAtualizado);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Excluir vendas")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
	 Venda venda = new Venda();
	 venda.setId(id);
	 this.vendaRepository.delete(venda);

}
}




