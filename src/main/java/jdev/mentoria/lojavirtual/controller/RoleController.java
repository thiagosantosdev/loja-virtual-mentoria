package jdev.mentoria.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.model.Roles;
import jdev.mentoria.lojavirtual.repository.RoleRepository;
import jdev.mentoria.lojavirtual.service.RoleService;

@Controller
@RestController
@RequestMapping(value = "/acesso")

public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleRepository roleRepository;

	@ResponseBody /* Pode dar um retorno da API */
	@PostMapping(value = "/salvarAcesso", produces = "application/json")
	public ResponseEntity<Roles> salvarRole(@RequestBody Roles role) { /* Recebe o JSON e converte para objeto */

		Roles roleSalvo = roleRepository.save(role);

		return new ResponseEntity<Roles>(roleSalvo, HttpStatus.OK);
	}
	
	@ResponseBody /* Pode dar um retorno da API */
	@PostMapping(value = "/deleteAcesso")
	public ResponseEntity<?> deleteRole(@RequestBody Roles role) { /* Recebe o JSON e converte para objeto */

		roleRepository.deleteById(role.getId());

		return new ResponseEntity("Acesso removido!",HttpStatus.OK);
	}

}
