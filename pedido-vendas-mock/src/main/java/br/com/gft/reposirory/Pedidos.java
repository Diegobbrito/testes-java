package br.com.gft.reposirory;

import br.com.gft.model.Pedido;
import br.com.gft.service.AcaoLancamendoPedido;

public class Pedidos implements AcaoLancamendoPedido{

	@Override
	public void executar(Pedido pedido) {
		System.out.println("Salvando no bando de dados...");	
	}
	
	public Pedido buscarPeloCodigo(Long codigo) {
		return new Pedido();
	}

}
