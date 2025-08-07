package com.mentoria.lojavirtual.LojaVirtualJdev.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.VendaCompraLojaVirtual;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.VendaCompraLojaVirtualRepository;

@Service
public class VendaService {
	
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;

	
	
	public void exclusaoLogica(Long id) {
		String sql = "begin; update vd_cp_lj_virt set excluido = true where id = " + id + "; commit;";
		jdbcTemplate.execute(sql);;
	}
	
	public void ativaVendaLogica(Long id) {
		String sql = "begin; update vd_cp_lj_virt set excluido = false where id = " + id + "; commit;";
		jdbcTemplate.execute(sql);;
	}
	
	
	public void exclusaoTotalVendaBanco(Long id) throws SQLException {
		
	
		String value = 
                " begin;"
    			+ " UPDATE nota_fiscal_venda set venda_compra_loja_virt_id = null where venda_compra_loja_virt_id = "+id+"; "
    			+ " delete from nota_fiscal_venda where venda_compra_loja_virt_id = "+id+"; "
    			+ " delete from item_venda_loja where venda_compra_loja_virt_id = "+id+"; "
    			+ " delete from status_rastreio where venda_compra_loja_virt_id = "+id+"; "
    			+ " delete from vd_cp_lj_virt where id = "+id+"; "
    			+ " commit; ";
		/*+ " UPDATE nota_fiscal_venda set venda_compra_loja_virt_id = null where venda_compra_loja_virt_id = "+idVenda+"; "
		      			+ " delete from nota_fiscal_venda where venda_compra_loja_virt_id = "+idVenda+"; "
		      			+ " delete from item_venda_loja where venda_compra_loja_virtu_id = "+idVenda+"; "
		      			+ " delete from status_rastreio where venda_compra_loja_virt_id = "+idVenda+"; "
		      			+ " delete from vd_cp_loja_virt where id = "+idVenda+"; "
		      			+ " commit; ";
		      			*/

jdbcTemplate.execute(value);
		
		
	}

	public List<VendaCompraLojaVirtual> consultaVendaFaixaData(String data1, String data2) throws ParseException {


SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date1 = dateFormat.parse(data1);
		Date date2 = dateFormat.parse(data2);
		
		
		return vendaCompraLojaVirtualRepository.consultaVendaFaixaData(date1, date2);
		
	} 

}
