package jdev.mentoria.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.Roles;
import jdev.mentoria.lojavirtual.repository.RoleRepository;
import jdev.mentoria.lojavirtual.service.RoleService;

//@CrossOrigin(origins = "https://www.jdevtreinamento.com.br")
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
	public ResponseEntity<Roles> salvarRole(@RequestBody Roles role) throws ExceptionMentoriaJava { /* Recebe o JSON e converte para objeto */

		if (role.getId() == null) {
			 
			List<Roles> acessos = roleRepository.buscarAcessoDesc(role.getDescricao().toUpperCase());
			  
			  if (!acessos.isEmpty()) {
				  throw new ExceptionMentoriaJava("Já existe Acesso com a descrição: " + role.getDescricao());
			  }
			}
			
		
		Roles roleSalvo = roleRepository.save(role);

		return new ResponseEntity<Roles>(roleSalvo, HttpStatus.OK);
	}
	
	//@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody /* Pode dar um retorno da API */
	@PostMapping(value = "/deleteAcesso")
	public ResponseEntity<?> deleteRole(@RequestBody Roles role) { /* Recebe o JSON e converte para objeto */

		roleRepository.deleteById(role.getId());

		return new ResponseEntity("Acesso removido!",HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "/obterAcesso/{id}")
	public ResponseEntity<Roles> obterAcesso(@PathVariable("id") Long id) throws ExceptionMentoriaJava { 
		
		Roles acesso = roleRepository.findById(id).orElse(null);
		
		if (acesso == null) {
			throw new ExceptionMentoriaJava("Não encontrou Acesso com código: " + id);
		}
		
		return new ResponseEntity<Roles>(acesso, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorDesc/{desc}")
	public ResponseEntity<List<Roles>> buscarPorDesc(@PathVariable("desc") String desc) { 
		
		List<Roles> acesso = roleRepository.buscarAcessoDesc(desc.toUpperCase());
		
		return new ResponseEntity<List<Roles>>(acesso,HttpStatus.OK);
	}
	
	
	
}
