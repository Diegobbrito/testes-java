package br.com.gft.service;

import br.com.gft.model.Passageiro;
import br.com.gft.model.Voo;

public class PrecoPassagemService {

	public double calcular(Passageiro passageiro, Voo voo) {
		
		return passageiro.getTipo().getCalculadora().calcular(voo);
	}

}
