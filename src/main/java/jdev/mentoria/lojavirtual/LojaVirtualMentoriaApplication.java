
package jdev.mentoria.lojavirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LojaVirtualMentoriaApplication {

	public static void main(String[] args) {
		
		
		SpringApplication.run(LojaVirtualMentoriaApplication.class, args);
		
		System.out.println(new BCryptPasswordEncoder().encode("123"));

	}

}
