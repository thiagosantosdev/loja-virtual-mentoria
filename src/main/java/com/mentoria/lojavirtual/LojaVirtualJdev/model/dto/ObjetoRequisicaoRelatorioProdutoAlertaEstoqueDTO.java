package com.mentoria.lojavirtual.LojaVirtualJdev.model.dto;

import java.io.Serializable;

public class ObjetoRequisicaoRelatorioProdutoAlertaEstoqueDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String codigoProduto  = "";
	private String nomeProduto = "";
	private String qtdEstoque;
	private String qtdAlertaEstoque;
	
	
	
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	
	public String getCodigoProduto() {
		return codigoProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public String getQtdEstoque() {
		return qtdEstoque;
	}
	public String getQtdAlertaEstoque() {
		return qtdAlertaEstoque;
	}
	public void setQtdEstoque(String qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public void setQtdAlertaEstoque(String qtdAlertaEstoque) {
		this.qtdAlertaEstoque = qtdAlertaEstoque;
	}
	
	
	
}
