package com.mentoria.lojavirtual.LojaVirtualJdev.enums;

public enum StatusVendaLojaVirtual {
	
	FINALIZADA("Finalizada"),
	CANCELADA("Cancelada"),
	ABANDONOU_CARRINHO("Abandonou carrinho");
	
	private StatusVendaLojaVirtual(String valor) {
		this.descricao = valor;
	}

	private String descricao = "";

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	@Override
	public String toString() {

		return this.descricao;
	}
	

}
