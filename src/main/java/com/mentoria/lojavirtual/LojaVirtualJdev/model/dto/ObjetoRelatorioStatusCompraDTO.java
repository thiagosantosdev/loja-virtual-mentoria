package com.mentoria.lojavirtual.LojaVirtualJdev.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class ObjetoRelatorioStatusCompraDTO implements Serializable{

	private static final long serialVersionUID = 1L;


	@NotEmpty(message = "Informe a data inicial")
	private String dataInicial;
	
	@NotEmpty(message = "Informe a data final")
	private String dataFinal;
	
	private String codigoProduto  = "";
	private String nomeProduto = "";
	private String emailCliente = "";
	private String foneCliente = "";
	private String valorVendaProduto  = "";
    private String codigoCliente = "";
    private String nomeCliente ="";
    private String qtdEstoque ="";
    private String codigoVenda ="";
    private String statusVenda = "";
    
    
    
	public String getDataInicial() {
		return dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public String getCodigoProduto() {
		return codigoProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public String getFoneCliente() {
		return foneCliente;
	}
	public String getValorVendaProduto() {
		return valorVendaProduto;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public String getQtdEstoque() {
		return qtdEstoque;
	}
	public String getCodigoVenda() {
		return codigoVenda;
	}
	public String getStatusVenda() {
		return statusVenda;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public void setFoneCliente(String foneCliente) {
		this.foneCliente = foneCliente;
	}
	public void setValorVendaProduto(String valorVendaProduto) {
		this.valorVendaProduto = valorVendaProduto;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public void setQtdEstoque(String qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public void setCodigoVenda(String codigoVenda) {
		this.codigoVenda = codigoVenda;
	}
	public void setStatusVenda(String statusVenda) {
		this.statusVenda = statusVenda;
	}
    
		
	
}
