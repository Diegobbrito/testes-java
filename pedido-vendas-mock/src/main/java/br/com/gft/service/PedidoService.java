package br.com.gft.service;

import java.util.List;

import br.com.gft.model.Pedido;
import br.com.gft.model.StatusPedido;
import br.com.gft.reposirory.Pedidos;

public class PedidoService {
	
	private List<AcaoLancamendoPedido> acoes;
	private Pedidos pedidos;
	
	public PedidoService(Pedidos pedidos, List<AcaoLancamendoPedido> acoes) {
		this.pedidos = pedidos;
		this.acoes = acoes;
	}

	public double lancar(Pedido pedido) {
		double imposto = pedido.getValor() * 0.1;
//		acoes.forEach(a -> a.executar(pedido));
		
		for (AcaoLancamendoPedido acao : acoes) {
			acao.executar(pedido);
		}
		return imposto;
	}

	public Pedido pagar(Long codigo) {
		
		Pedido pedido = pedidos.buscarPeloCodigo(codigo);
		
		if(!pedido.getStatus().equals(StatusPedido.PENDENTE))
			throw new StatusPedidoInvalidoException();
		
		pedido.setStatus(StatusPedido.PAGO);
		
		return pedido;
	}

}
