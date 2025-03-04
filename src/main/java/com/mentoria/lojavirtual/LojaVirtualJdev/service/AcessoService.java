package com.mentoria.lojavirtual.LojaVirtualJdev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.Acesso;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.AcessoRepository;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepository;
	
	public Acesso save(Acesso acesso) {

		return acessoRepository.save(acesso);
	}
	
	
	
	

	
}
