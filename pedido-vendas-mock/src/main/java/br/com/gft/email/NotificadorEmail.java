package br.com.gft.email;

import br.com.gft.model.Pedido;
import br.com.gft.service.AcaoLancamendoPedido;

public class NotificadorEmail implements AcaoLancamendoPedido{
	
	@Override
	public void executar(Pedido pedido) {
		System.out.println("Enviado o email...");	
	}

}
