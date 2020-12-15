  package br.com.gft.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.gft.model.Passageiro;
import br.com.gft.model.TipoPassageiro;
import br.com.gft.model.Voo;

public class PrecoPassagemServiceTest {
	
	PrecoPassagemService precoPassagemService;
	
	@BeforeEach
	public void setup() {
		precoPassagemService = new PrecoPassagemService();
	}
	
	private void assertValorPassagem(Passageiro passageiro, Voo voo, double esperado) {
		double valor = precoPassagemService.calcular(passageiro, voo);
		assertEquals(esperado, valor);
	}
	
	@Test
	void deveCalcularValorPassagemParaPassageiroGoldComValorAbaixoDoLimite() throws Exception {
		Passageiro passageiro = new Passageiro("João", TipoPassageiro.GOLD);
		Voo voo = new Voo("São Paulo", "Rio de Janeiro", 100.0);
		assertValorPassagem(passageiro, voo, 90.0);
	}
	
	@Test
	void deveCalcularValorPassagemParaPassageiroGoldComValorAcimaoDoLimite() throws Exception {
		Passageiro passageiro = new Passageiro("João", TipoPassageiro.GOLD);
		Voo voo = new Voo("São Paulo", "Rio de Janeiro", 600.0);
		assertValorPassagem(passageiro, voo, 510.0);
	}
	
	@Test
	void deveCalcularValorPassagemParaPassageiroSilverComValorAbaixoDoLimite() throws Exception {
		Passageiro passageiro = new Passageiro("João", TipoPassageiro.SILVER);
		Voo voo = new Voo("São Paulo", "Rio de Janeiro", 100.0);
		assertValorPassagem(passageiro, voo, 94.0);
	}

	
	
	@Test
	void deveCalcularValorPassagemParaPassageiroSilverComValorAcimaoDoLimite() throws Exception {
		Passageiro passageiro = new Passageiro("João", TipoPassageiro.SILVER);
		Voo voo = new Voo("São Paulo", "Rio de Janeiro", 800.0);
		assertValorPassagem(passageiro, voo, 720.0);
	}

}
