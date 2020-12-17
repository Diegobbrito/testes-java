package br.com.gft.sms;

import br.com.gft.model.Pedido;
import br.com.gft.service.AcaoLancamendoPedido;

public class NotificadorSms implements AcaoLancamendoPedido{

	@Override
	public void executar(Pedido pedido) {
		System.out.println("Enviado o sms...");
		// TODO Auto-generated method stub
		
	}

}
