package com.mentoria.lojavirtual.LojaVirtualJdev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.dto.ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO;

@Service
public class NotaFiscalCompraService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

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

}
