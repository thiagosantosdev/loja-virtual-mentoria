package com.mentoria.lojavirtual.LojaVirtualJdev.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String nomeProduto = "";
	
	@NotEmpty(message = "Informe a data inicial")
	private String dataInicial;
	
	@NotEmpty(message = "Informe a data final")
	private String dataFinal;
	
	private String codigoNota = "";
	private String codigoProduto  = "";
	private String valorVendaProduto  = "";
	private String quantidadeComprada  = "";
	private String codigoFornecedor  = "";
	private String nomeFornecedor  = "";
	private String dataCompra  = "";
	
	
	
	public String getValorVendaProduto() {
		return valorVendaProduto;
	}
	public String getQuantidadeComprada() {
		return quantidadeComprada;
	}
	public String getCodigoFornecedor() {
		return codigoFornecedor;
	}
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public String getDataCompra() {
		return dataCompra;
	}
	public void setValorVendaProduto(String valorVendaProduto) {
		this.valorVendaProduto = valorVendaProduto;
	}
	public void setQuantidadeComprada(String quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}
	public void setCodigoFornecedor(String codigoFornecedor) {
		this.codigoFornecedor = codigoFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public String getDataInicial() {
		return dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public String getCodigoNota() {
		return codigoNota;
	}
	public String getCodigoProduto() {
		return codigoProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public void setCodigoNota(String codigoNota) {
		this.codigoNota = codigoNota;
	}
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
	
	
}
