package io.github.andregois.cad;

import io.github.andregois.cad.model.Cep;
import io.github.andregois.cad.service.ViaCepService;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cep cep = ViaCepService.consultaCep("61942250");
		System.out.println("OK");
		
	}

}
