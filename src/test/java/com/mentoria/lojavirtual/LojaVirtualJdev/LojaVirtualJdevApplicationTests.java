package com.mentoria.lojavirtual.LojaVirtualJdev;

import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.TestCase;

//@Profile("test")
@SpringBootTest(classes = LojaVirtualJdevApplication.class)
public class LojaVirtualJdevApplicationTests extends TestCase{
	
	/*
	@Autowired
	private AcessoRepository acessoRepository;

	@Autowired
	private AcessoController acessoController;

	@Autowired
	private WebApplicationContext wac;

	Teste do end-point de salvar
	@Test
	public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {
		
	    DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
	    MockMvc mockMvc = builder.build();
	    
	    Acesso acesso = new Acesso();
	    
	    acesso.setDescricao("ROLE_COMPRADOR" + Calendar.getInstance().getTimeInMillis());
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    
	    ResultActions retornoApi = mockMvc
	    						 .perform(MockMvcRequestBuilders.post("/salvarAcesso")
	    						 .content(objectMapper.writeValueAsString(acesso))
	    						 .accept(MediaType.APPLICATION_JSON)
	    						 .contentType(MediaType.APPLICATION_JSON));
	    
	    System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
	    
	    //Conveter o retorno da API para um obejto de acesso
	    
	    Acesso objetoRetorno = objectMapper.
	    					   readValue(retornoApi.andReturn().getResponse().getContentAsString(),
	    					   Acesso.class);
	    
	    assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
	    
	    
	    
	    
	}
	
	
	@Test
	void testRestApiDeleteAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_TEST_DELETE");
		
		acesso = acessoRepository.save(acesso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(
			MockMvcRequestBuilders
				.post("/deleteAcesso")
				.content(mapper.writeValueAsString(acesso))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
		);		
		
		assertEquals("Acesso removido com sucesso!", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
	}
	
	@Test
	void testRestApiDeleteAcessoPorId() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_TEST_DELETE");
		
		acesso = acessoRepository.save(acesso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(
			MockMvcRequestBuilders
				.delete("/deleteAcessoPorId/" + acesso.getId_Acesso())
				.content(mapper.writeValueAsString(acesso))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
		);		
		
		assertEquals("Acesso removido com sucesso!", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
	}
	
	@Test
	void testRestApiObterAcessoPorId() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_OBTER_POR_ID");
		
		acesso = acessoRepository.save(acesso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(
			MockMvcRequestBuilders
				.get("/obterAcessoPorId/" + acesso.getId_Acesso())
				.content(mapper.writeValueAsString(acesso))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
		);		
		
		Acesso acessoRetorno = mapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
		
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());
		assertEquals(acesso.getId_Acesso(), acessoRetorno.getId_Acesso());
	}
	
	@Test
	void testRestApiObterAcessoPorDescricao() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_OBTER_POR_DESC");
		
		acesso = acessoRepository.save(acesso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(
			MockMvcRequestBuilders
				.get("/obterAcessoPorDescricao/OBTER_POR_DESC")
				.content(mapper.writeValueAsString(acesso))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
		);		
		
		List<Acesso> retornoListaApi = mapper.readValue(
			retornoApi.andReturn().getResponse().getContentAsString(), new TypeReference<>(){}
		);
		
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		assertEquals(1, retornoListaApi.size());
		assertEquals(acesso.getDescricao(), retornoListaApi.get(0).getDescricao());
		
		acessoRepository.deleteById(acesso.getId_Acesso());
	}


	@Test
	void testCadastraAcesso() throws ExceptionMentoriaJava {

		Acesso acesso = new Acesso();
		
		String descAcesso = "ROLE_ADMIN" + Calendar.getInstance().getTimeInMillis();
		
		acesso.setDescricao(descAcesso);

		assertEquals(true, acesso.getId_Acesso() == null);

		acesso = acessoController.salvarAcesso(acesso).getBody();

		assertEquals(true, acesso.getId_Acesso() > 0);

		assertEquals(descAcesso, acesso.getDescricao());

		Acesso acesso2 = acessoRepository.findById(acesso.getId_Acesso()).get();

		assertEquals(acesso.getId_Acesso(), acesso2.getId_Acesso());

		acessoRepository.deleteById(acesso2.getId_Acesso());
		acessoRepository.flush();

		Acesso acesso3 = acessoRepository.findById(acesso2.getId_Acesso()).orElse(null);
		assertNull(acesso3);

		acesso = new Acesso();
		acesso.setDescricao("ROLE_ALUNO");
		acesso = acessoController.salvarAcesso(acesso).getBody();

		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim().toUpperCase());

		assertEquals(1, acessos.size());

		acessoRepository.deleteById(acesso.getId_Acesso());

	} 
	
	
	*/
	
}
