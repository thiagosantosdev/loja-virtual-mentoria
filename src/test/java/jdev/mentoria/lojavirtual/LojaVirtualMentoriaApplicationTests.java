package jdev.mentoria.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import jdev.mentoria.lojavirtual.controller.RoleController;
import jdev.mentoria.lojavirtual.model.Roles;
import jdev.mentoria.lojavirtual.repository.RoleRepository;
import jdev.mentoria.lojavirtual.service.RoleService;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class LojaVirtualMentoriaApplicationTests extends TestCase{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleController roleController;
	@Autowired
	private WebApplicationContext wac;
	
	/*Teste do end-point de salvar*/
	@Test
	public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {
		
	    DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
	    MockMvc mockMvc = builder.build();
	    
	    Roles acesso = new Roles();
	    
	    acesso.setDescricao("ROLE_COMPRADOR");
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    
	    ResultActions retornoApi = mockMvc
	    						 .perform(MockMvcRequestBuilders.post("/salvarAcesso")
	    						 .content(objectMapper.writeValueAsString(acesso))
	    						 .accept(MediaType.APPLICATION_JSON)
	    						 .contentType(MediaType.APPLICATION_JSON));
	    
	    System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
	    
	    /*Conveter o retorno da API para um obejto de acesso*/
	    
	    Roles objetoRetorno = objectMapper.
	    					   readValue(retornoApi.andReturn().getResponse().getContentAsString(),
	    							   Roles.class);
	    
	    assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
	    
	    
	    
	    
	}
	
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
