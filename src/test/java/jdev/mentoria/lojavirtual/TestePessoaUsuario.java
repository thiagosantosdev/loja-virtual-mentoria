package jdev.mentoria.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.repository.PessoaJuridicaRepository;
import jdev.mentoria.lojavirtual.service.PessoaUserService;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class TestePessoaUsuario  extends TestCase{
	
	@Autowired
	private PessoaUserService pessoaUserService;
	
	@Autowired
	private PessoaJuridicaRepository pessoaRepository;
	
	@Test
	public void testCadPessoaFisica() {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		
		pessoaJuridica.setCnpj("4646626822935");
		pessoaJuridica.setNome("José Emp");
		pessoaJuridica.setEmail("email@email.com");
		pessoaJuridica.setTelefone("58523128994");
		pessoaJuridica.setInsEstadual("estadual"); 
		pessoaJuridica.setInsMunicipal("85000000");
		pessoaJuridica.setNomeFantasia("Empresa-online");
		pessoaJuridica.setRazaoSocial("Empresa");
		
		
		pessoaRepository.save(pessoaJuridica);
		
	/*	PessoaFisica pessoaFisica = new PessoaFisica();
		
		pessoaFisica.setCpf("4646626822935");
		pessoaFisica.setNome("José");
		pessoaFisica.setEmail("email@email.com");
		pessoaFisica.setTelefone("58523128994");
		pessoaFisica.setEmpresa(pessoaFisica); */
		
	}
	

}
