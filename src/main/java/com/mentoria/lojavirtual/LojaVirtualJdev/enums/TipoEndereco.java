package com.mentoria.lojavirtual.LojaVirtualJdev.enums;

public enum TipoEndereco {
	
	COBRANCA("Cobranca"),
	ENTREGA("Entrega");
	
	private String descricao;
	
	private TipoEndereco(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	
	
	
	
	
	
}
