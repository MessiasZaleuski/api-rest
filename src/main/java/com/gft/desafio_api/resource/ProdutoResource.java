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
import com.gft.desafio_api.model.Produto;
import com.gft.desafio_api.repository.ProdutoRepository;
import com.gft.desafio_api.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/produtos")
@Api(tags="Produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ProdutoService produtoService;
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
	allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping
	@ApiOperation("Lista todos os produtos")
	public List<Produto> listar(){
		return produtoRepository.findAll();
		}
	
	@GetMapping("/asc")
	@ApiOperation("Listar todos os produtos em ordem alfabética crescente por nome")
	@OrderBy
	public List<Produto> listarOrdemCrescente(){
		return produtoRepository.findAll(Sort.by(JpaSort.Direction.ASC, "nome"));
	}
	
	@GetMapping("/desc")
	@ApiOperation("Listar as casas em ordem alfabética decrescente por nome")
	@OrderBy
	public List<Produto> listarOrdemDecrescente(){
		return produtoRepository.findAll(Sort.by(JpaSort.Direction.DESC, "nome"));
	}
	
	@GetMapping("/nome/{nome}")
	@ApiOperation("Listar por nome")
	public List<Produto >listarPorNome(@PathVariable("nome")String nome){
		
    return produtoRepository.findByNome(nome);
	}
	
	
	@PostMapping
	@ApiOperation("Inserir produto")
	public ResponseEntity<Produto> criar(
			@ApiParam(name = "corpo", value = "Representação de um novo produto")
			@Valid @RequestBody Produto produto, HttpServletResponse response) {
		Produto produtoSalva = produtoRepository.save(produto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalva);	
	}

	@GetMapping("/{id}")
	@ApiOperation("Buscar por ID")
	public Produto buscarPeloId(
			@ApiParam(value = "ID de um produto", example ="1")
			@PathVariable Long id) {
	if (produtoRepository.existsById(id)) {
	}else {
		throw new EmptyResultDataAccessException(1);
	}
	
	return this.produtoRepository.findById(id).orElse(null);
}
	
	@PutMapping("/{id}")
	@ApiOperation("Atualizar produtos")
	public ResponseEntity<Produto> atualizar(
			@ApiParam(value = "ID de um produto")
			@PathVariable Long id, 
			@ApiParam(name = "corpo", value="Representação de um novo produto com os novos dados")
			@Valid @RequestBody Produto produto)
	{
		Produto produtoAtualizado = produtoService.atualizar(id, produto);
		return ResponseEntity.ok(produtoAtualizado);
	}
	
	

	@DeleteMapping("/{id}")
	@ApiOperation("Excluir produtos")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(
			@ApiParam(value ="ID de um produto", example = "1")
			@PathVariable Long id) {
	 Produto produto = new Produto();
	 produto.setId(id);
	 this.produtoRepository.delete(produto);

}
}

