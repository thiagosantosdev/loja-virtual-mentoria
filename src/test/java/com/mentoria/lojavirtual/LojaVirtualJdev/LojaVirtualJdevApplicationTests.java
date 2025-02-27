package com.mentoria.lojavirtual.LojaVirtualJdev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.Acesso;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.AcessoRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.service.AcessoService;

@SpringBootTest(classes = LojaVirtualJdevApplication.class)
public class LojaVirtualJdevApplicationTests {
	
	@Autowired
	private AcessoService acessoService;

	@Autowired
	private AcessoRepository acessoRepository;
	
	public void testCadastraAcesso() {
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN");
		acessoRepository.save(acesso);
		
		
	}
	
	
	
	
}
