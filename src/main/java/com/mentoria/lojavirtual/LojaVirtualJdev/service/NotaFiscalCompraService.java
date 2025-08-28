package com.mentoria.lojavirtual.LojaVirtualJdev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.dto.ObjetoRelatorioStatusCompraDTO;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.dto.ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.dto.ObjetoRequisicaoRelatorioProdutoAlertaEstoqueDTO;

@Service
public class NotaFiscalCompraService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<ObjetoRelatorioStatusCompraDTO> relatorioStatusVendaLojaVirtual(ObjetoRelatorioStatusCompraDTO objetoRelatorioStatusCompraDTO){
		
		List<ObjetoRelatorioStatusCompraDTO> retorno = new ArrayList<ObjetoRelatorioStatusCompraDTO>();
		
		String sql = " select p.id_produto as codigoProduto, "
				+ " p.nome as nomeProduto, "
				+ " pf.email as emailCliente, "
				+ " pf.telefone as foneCliente, "
				+ " p.valor_venda as valorVendaProduto, "
				+ " pf.id_pessoa as codigoCliente, "
				+ " pf.nome_pessoa as nomeCliente, "
				+ " p.qtd_estoque as qtdEstoque, "
				+ " cfc.id as codigoVenda, "
				+ " cfc.status_venda_loja_virtual as statusVenda "
				+ " from vd_cp_lj_virt as cfc "
				+ " inner join item_venda_loja as ntp on ntp.venda_compra_loja_virt_id = cfc.id "
				+ " inner join produto as p on p.id_produto = ntp.produto_id "
				+ " inner join pessoa_fisica as pf on pf.id_pessoa = cfc.pessoa_id ";
				
		sql+= " where cfc.data_venda >= '" + objetoRelatorioStatusCompraDTO.getDataInicial()  + "' and cfc.data_venda <= '" + objetoRelatorioStatusCompraDTO.getDataFinal() + "' ";
		
		if(!objetoRelatorioStatusCompraDTO.getNomeProduto().isEmpty()) {
			sql += " and upper(p.nome) like upper('%" + objetoRelatorioStatusCompraDTO.getNomeProduto() + "%') ";
		}
		
		if(!objetoRelatorioStatusCompraDTO.getStatusVenda().isEmpty()) {
			sql+= " and cfc.status_venda_loja_virtual in ('" + objetoRelatorioStatusCompraDTO.getStatusVenda() +  "')  ";
			
		}
		
		if(objetoRelatorioStatusCompraDTO.getNomeCliente().isEmpty()) {
			sql+= " and pf.nome_pessoa like '%" + objetoRelatorioStatusCompraDTO.getNomeCliente() + "%'  ";
		}
		retorno = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ObjetoRelatorioStatusCompraDTO.class));
		
		return retorno;
	}

	/**
	 * Este relatório permite saber todos os produtos comprados para serem vendidos pela loja virtual/todos os produtos tem relação com 
	 * a nota fiscal de compra/venda
	 * @param objetoRequisicaoRelatorioProdCompraNotaFiscalDTO
	 * @return
	 */
	public List<ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO> gerarRelatorioProdCompraNota(ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO objetoRequisicaoRelatorioProdCompraNotaFiscalDTO) {

		List<ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO> retorno = new ArrayList<ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO>();
		String sql = 
			"	SELECT "
			  + " p.id_produto AS codigoProduto, "
			  + " p.nome AS nomeProduto,  "
			  + " p.valor_venda AS valorVendaProduto, " 
			  + " ntp.quantidade AS quantidadeComprada, " 
			  + " pj.id_pessoa AS codigoFornecedor, "
			  + " pj.nome_pessoa AS nomeFornecedor, "
			  + " cfc.data_compra AS dataCompra "
	+	"	FROM "
	+	"	    nota_fiscal_compra AS cfc "
	+	"	INNER JOIN "
	+	"	    nota_item_produto AS ntp ON cfc.id_nota_fiscal_compra = nota_fiscal_compra_id "
	+	"	INNER JOIN "
	+   "   produto AS p ON p.id_produto = ntp.produto_id  " 
	+	"	INNER JOIN "
	+	"	    pessoa_juridica AS pj ON pj.id_pessoa = cfc.pessoa_id  " 
	+	"	WHERE ";
 sql += " cfc.data_compra >= '" +objetoRequisicaoRelatorioProdCompraNotaFiscalDTO.getDataInicial() + "' and ";
 sql += " cfc.data_compra <= '" +objetoRequisicaoRelatorioProdCompraNotaFiscalDTO.getDataFinal() + "' ";
 
 if(!objetoRequisicaoRelatorioProdCompraNotaFiscalDTO.getCodigoNota().isEmpty()) {
	 sql += " and cfc.id =  " + objetoRequisicaoRelatorioProdCompraNotaFiscalDTO.getCodigoNota() + " ";
 }
	
 if(!objetoRequisicaoRelatorioProdCompraNotaFiscalDTO.getCodigoProduto().isEmpty()) {
	 sql += " and p.id_produto =  " + objetoRequisicaoRelatorioProdCompraNotaFiscalDTO.getCodigoProduto() + " ";
 }
	
 if(!objetoRequisicaoRelatorioProdCompraNotaFiscalDTO.getNomeProduto().isEmpty()) {
	 sql += " upper(p.nome) like upper('% "   + objetoRequisicaoRelatorioProdCompraNotaFiscalDTO.getNomeProduto() + "')";
 }
			
 if(!objetoRequisicaoRelatorioProdCompraNotaFiscalDTO.getNomeFornecedor().isEmpty()) {
	 sql += " upper(pj.nome_pessoa) like upper('% " + objetoRequisicaoRelatorioProdCompraNotaFiscalDTO.getNomeFornecedor() + "')";
 }
			
		
		retorno = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO.class));
		
		return retorno;
	}
	
	
	
	public List<ObjetoRequisicaoRelatorioProdutoAlertaEstoqueDTO> 
	gerarRelatorioAlertaEstoque(ObjetoRequisicaoRelatorioProdutoAlertaEstoqueDTO alertaEstoque){
		
		List<ObjetoRequisicaoRelatorioProdutoAlertaEstoqueDTO> retorno = new ArrayList<ObjetoRequisicaoRelatorioProdutoAlertaEstoqueDTO>();
		/*
		String sql = 
				"	SELECT "
				  + " p.id_produto AS codigoProduto, "
				  + " p.nome AS nomeProduto,  "
				  + " p.valor_venda AS valorVendaProduto, " 
				  + " ntp.quantidade AS quantidadeComprada, " 
				  + " pj.id_pessoa AS codigoFornecedor, "
				  + " pj.nome_pessoa AS nomeFornecedor, "
				  + " cfc.data_compra AS dataCompra, "
			      + " p.qtd_estoque  as quantidadeEstoque, p.qtd_alerta_estoque as qtdAlertaEstoque "
		+	"	FROM "
		+	"	    nota_fiscal_compra AS cfc "
		+	"	INNER JOIN "
		+	"	    nota_item_produto AS ntp ON cfc.id_nota_fiscal_compra = nota_fiscal_compra_id "
		+	"	INNER JOIN "
		+   "   produto AS p ON p.id_produto = ntp.produto_id  " 
		+	"	INNER JOIN "
		+	"	    pessoa_juridica AS pj ON pj.id_pessoa = cfc.pessoa_id  " 
		+	"	WHERE ";
	 sql += " cfc.data_compra >= '" +alertaEstoque.getDataInicial() + "' and ";
	 sql += " cfc.data_compra <= '" +alertaEstoque.getDataFinal() + "' ";
	 sql += " and p.alerta_qtd_estoque  = true and p.qtd_estoque  <= p.qtd_alerta_estoque ";
			 
	 if(!alertaEstoque.getCodigoNota().isEmpty()) {
		 sql += " and cfc.id =  " + alertaEstoque.getCodigoNota() + " ";
	 }
		
	 if(!alertaEstoque.getCodigoProduto().isEmpty()) {
		 sql += " and p.id_produto =  " + alertaEstoque.getCodigoProduto() + " ";
	 }
		
	 if(!alertaEstoque.getNomeProduto().isEmpty()) {
		 sql += " upper(p.nome) like upper('% "   + alertaEstoque.getNomeProduto() + "')";
	 }
				
	 if(!alertaEstoque.getNomeFornecedor().isEmpty()) {
		 sql += " upper(pj.nome_pessoa) like upper('% " + alertaEstoque.getNomeFornecedor() + "')";
	 }
				*/
		
		String sql = 
				"	SELECT "
				  + " p.id_produto AS codigoProduto, "
				  + " p.nome AS nomeProduto,  "
				  
				  
				  
				 
				 
			      + " p.qtd_estoque  as quantidadeEstoque, p.qtd_alerta_estoque as qtdAlertaEstoque "
		+	"	FROM "
		+	"	   produto p "
		
		+	"	WHERE ";
	 
	 sql += "  p.alerta_qtd_estoque  = true and p.qtd_estoque  <= p.qtd_alerta_estoque ";
			 
	
		
	 if(!alertaEstoque.getCodigoProduto().isEmpty()) {
		 sql += " and p.id_produto =  " + alertaEstoque.getCodigoProduto() + " ";
	 }
		
	 if(!alertaEstoque.getNomeProduto().isEmpty()) {
		 sql += " upper(p.nome) like upper('% "   + alertaEstoque.getNomeProduto() + "')";
	 }
				
	 
				
			
			retorno = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ObjetoRequisicaoRelatorioProdutoAlertaEstoqueDTO.class));
			
			return retorno;
		
	}
	
	
	
	
	
	
	
	

}
