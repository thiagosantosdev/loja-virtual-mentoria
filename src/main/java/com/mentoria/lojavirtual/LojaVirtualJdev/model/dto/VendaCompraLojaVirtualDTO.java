package com.mentoria.lojavirtual.LojaVirtualJdev.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.Endereco;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.Pessoa;

public class VendaCompraLojaVirtualDTO {
	
	private Long id;
	
	private BigDecimal valorTotal;
	
	private Pessoa pessoa;
	
	Endereco entrega;
	
	Endereco cobranca;
	
	private BigDecimal valor_desc;
	
	private BigDecimal valor_frete;

	private List<ItemVendaDTO> itemVendaLoja = new ArrayList<ItemVendaDTO>();
	
	
	
	public List<ItemVendaDTO> getItemVendaLoja() {
		return itemVendaLoja;
	}

	public void setItemVendaLoja(List<ItemVendaDTO> itemVendaLoja) {
		this.itemVendaLoja = itemVendaLoja;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor_frete() {
		return valor_frete;
	}

	public void setValor_frete(BigDecimal valor_frete) {
		this.valor_frete = valor_frete;
	}

	public BigDecimal getValor_desc() {
		return valor_desc;
	}

	public void setValor_desc(BigDecimal valor_desc) {
		this.valor_desc = valor_desc;
	}

	public Endereco getEntrega() {
		return entrega;
	}

	public Endereco getCobranca() {
		return cobranca;
	}

	public void setEntrega(Endereco entrega) {
		this.entrega = entrega;
	}

	public void setCobranca(Endereco cobranca) {
		this.cobranca = cobranca;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	

}
