package br.com.br;

import br.com.br.desconto.CalculadoraDescontoPrimeiraFaixa;
import br.com.br.desconto.CalculadoraDescontoSegundaFaixa;
import br.com.br.desconto.CalculadoraDescontoTerceiraFaixa;
import br.com.br.desconto.CalculadoraFaixaDesconto;
import br.com.br.desconto.SemDesconto;

public class PedidoBuilder {

	private Pedido instancia;
	
	public PedidoBuilder() {
		CalculadoraFaixaDesconto calculadoraFaixaDesconto = 
				new CalculadoraDescontoTerceiraFaixa(
					new CalculadoraDescontoSegundaFaixa(
						new CalculadoraDescontoPrimeiraFaixa(
							new SemDesconto(null))));
		instancia = new Pedido(calculadoraFaixaDesconto);
	}
	
	public PedidoBuilder comItem(double valorUnitario, int quantidade) {
		instancia.adicionarItem(new ItemPedido("Gerado", valorUnitario, quantidade));
		return this;
	}
	
	public Pedido construir() {
		return instancia;
	}
}
