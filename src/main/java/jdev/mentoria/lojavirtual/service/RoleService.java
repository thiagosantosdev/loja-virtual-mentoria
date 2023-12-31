package jdev.mentoria.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdev.mentoria.lojavirtual.model.Roles;
import jdev.mentoria.lojavirtual.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Roles save(Roles role) {
		
		return roleRepository.save(role);
	}
	
	
	

}
