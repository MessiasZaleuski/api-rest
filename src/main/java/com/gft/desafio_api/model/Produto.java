package com.gft.desafio_api.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name= "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String nome;
	
	@Column(name ="codigo_produto")
	@Size(min = 3, max = 4)
	private String codigoProduto;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private Boolean promocao;
	
	@Column(name ="valor_promo")
	@NotNull
	private BigDecimal valorPromo;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String categoria;
	
	private String imagem;
	
	@NotNull
	private Long quantidade;
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	@JsonIgnoreProperties({"nome", "cnpj", "produtos"})
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JsonIgnore
	private Venda vendas;
	

	public Venda getVendas() {
		return vendas;
	}
	public void setVendas(Venda vendas) {
		this.vendas = vendas;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Boolean getPromocao() {
		return promocao;
	}
	public void setPromocao(Boolean promocao) {
		this.promocao = promocao;
	}
	public BigDecimal getValorPromo() {
		return valorPromo;
	}
	public void setValorPromo(BigDecimal valorPromo) {
		this.valorPromo = valorPromo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
	


