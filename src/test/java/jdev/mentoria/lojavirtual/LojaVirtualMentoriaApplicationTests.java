package jdev.mentoria.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jdev.mentoria.lojavirtual.model.Roles;
import jdev.mentoria.lojavirtual.service.RoleService;

@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class LojaVirtualMentoriaApplicationTests {
	
	//@Autowired
	//private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Test
	public void testCadastraAcesso() {
		
		Roles roles = new Roles();
		roles.setDescricao("ROLE_ADMIN");
		roleService.save(roles);
	}

}
