package com.mentoria.lojavirtual.LojaVirtualJdev;

import com.mentoria.lojavirtual.LojaVirtualJdev.util.ValidaCNPJ;
import com.mentoria.lojavirtual.LojaVirtualJdev.util.ValidaCPF;

public class TesteCPFCNPJ {
	
	public static void main(String[] args) {
	
		boolean isCnpj = ValidaCNPJ.isCNPJ("66.347.536/0001-96");
		
		System.out.println("CNPJ válido: " + isCnpj);
		
		boolean isCpf = ValidaCPF.isCPF("255.326.610-30");
		
		System.out.println("CPF válido: " + isCpf);
		
	}

}
