package com.mentoria.lojavirtual.LojaVirtualJdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LojaVirtualJdevApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualJdevApplication.class, args);
		System.out.println("Rodouu");
		System.out.println(new BCryptPasswordEncoder().encode("123"));

	}
}
