package br.com.br;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoTest {
	
	private PedidoBuilder pedido;
	
	@BeforeEach
	private void setup() {
		pedido = new PedidoBuilder();
	}
	
	private void assertResumoPedido(double valotTotal, double desconto) {
		ResumoPedido resumoPedido = pedido.construir().resumo();
		assertEquals(valotTotal, resumoPedido.getValorTotal());
		assertEquals(desconto, resumoPedido.getDesconto());
	} 
	
	@Test
	void devePermitrAdicionarUmItemNoPedido() throws Exception {		
//		pedido.adicionarItem(new ItemPedido("Sabonete", 3.0, 10));
		pedido.comItem(3.0, 10);
	}
	
	@Test
	void deveCalcularValorTotalEDescontoParaPedidoVazio() throws Exception {
		assertResumoPedido(0.0, 0.0);
	}
	
	@Test
	void deveCalcularResumoParaUmItemSemDesconto() throws Exception {
		pedido.comItem(5.0, 5);
		
		assertResumoPedido(25.0, 0.0);		
	}
	
	@Test
	void deveCalcularResumoParaDoisItensSemDesconto() throws Exception {
		pedido.comItem(3.0, 3).comItem(7.0, 3);
		
		assertResumoPedido(30.0, 0.0);
	}
	
	@Test
	void deveAplicarDescontoNa1aFaixa() throws Exception {
		pedido.comItem(20.0, 20);
		
		assertResumoPedido(400.0, 16.0);
	}
	
	@Test
	void deveAplicarDescontoNa2aFaixa() throws Exception {
		pedido.comItem(15.0, 30).comItem(15.0, 30);
		
		assertResumoPedido(900.0, 54.0);
	}
	
	@Test
	void deveAplicarDescontoNa3aFaixa() throws Exception {		
		pedido.comItem(15.0, 30).comItem(15.0, 30).comItem(10.0, 30);
		
		assertResumoPedido(1200.0, 96.0);
	}
}
