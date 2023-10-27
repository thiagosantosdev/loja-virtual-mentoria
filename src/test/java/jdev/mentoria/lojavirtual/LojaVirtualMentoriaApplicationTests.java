package jdev.mentoria.lojavirtual;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jdev.mentoria.lojavirtual.controller.RoleController;
import jdev.mentoria.lojavirtual.model.Roles;
import jdev.mentoria.lojavirtual.repository.RoleRepository;
import jdev.mentoria.lojavirtual.service.RoleService;

@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class LojaVirtualMentoriaApplicationTests {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleController roleController;
	
	@Test
	public void testCadastraAcesso() {
		
Roles role = new Roles();
		
		role.setDescricao("ROLE_TESTEREAL");
		
		assertEquals(true, role.getId() == null);

		/*Gravou no banco de dados*/
		role = roleController.salvarRole(role).getBody();
		
		assertEquals(true, role.getId() > 0);
		
		/*Validar dados salvos da forma correta*/
		assertEquals("ROLE_TESTEREAL", role.getDescricao());
		
		/*Teste de carregamento*/
		
		Roles role2 = roleRepository.findById(role.getId()).get();
		
		assertEquals(role.getId(), role2.getId());
		
		
		/*Teste de delete*/
		
		roleRepository.deleteById(role2.getId());
		
		roleRepository.flush(); /*Roda esse SQL de delete no banco de dados*/
		
		Roles acesso3 = roleRepository.findById(role2.getId()).orElse(null);
		
		assertEquals(true, acesso3 == null);
	}

}
