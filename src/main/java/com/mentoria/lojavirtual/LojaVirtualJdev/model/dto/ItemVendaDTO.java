package com.mentoria.lojavirtual.LojaVirtualJdev.model.dto;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.Produto;

public class ItemVendaDTO {
	
	private Double quantidade;
	
	private Produto produto;

	
	
	public Double getQuantidade() {
		return quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	

}
