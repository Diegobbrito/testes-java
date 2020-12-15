package br.com.gft.model;

import br.com.gft.service.CalculadoraPrecoPassagem;
import br.com.gft.service.PrecoPassagemGold;
import br.com.gft.service.PrecoPassagemSilver;

public enum TipoPassageiro {
	
	GOLD(new PrecoPassagemGold()),
	SILVER(new PrecoPassagemSilver());
	
	CalculadoraPrecoPassagem calculadoraPrecoPassagem;
	
	private TipoPassageiro(CalculadoraPrecoPassagem calculadoraPrecoPassagem) {
		this.calculadoraPrecoPassagem = calculadoraPrecoPassagem;
	}

	public CalculadoraPrecoPassagem getCalculadora() {
		return calculadoraPrecoPassagem;
	}
	
}
