package br.com.gft.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.gft.builder.PedidoBuilder;
import br.com.gft.email.NotificadorEmail;
import br.com.gft.model.Pedido;
import br.com.gft.model.StatusPedido;
import br.com.gft.reposirory.Pedidos;
import br.com.gft.sms.NotificadorSms;

public class PedidoServiceTest {

	private PedidoService pedidoService;
	private Pedido pedido;

	@Mock
	private Pedidos pedidos;

	@Mock
	private NotificadorEmail notificadorEmail;

	@Mock
	private NotificadorSms notificadorSms;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		List<AcaoLancamendoPedido> acoes = Arrays.asList(pedidos, notificadorEmail, notificadorSms);
		pedidoService = new PedidoService(pedidos, acoes);
		pedido = new PedidoBuilder().comValor(100.0).para("Diego", "diego@gft.com", "9999-9999").construir();
	}

	@Test
	void deveCalcularOImposto() throws Exception {
		double imposto = pedidoService.lancar(pedido);
		assertEquals(10.0, imposto);
	}

	@Test
	void deveSalvarPedidoNoBancoDeDados() throws Exception {
		pedidoService.lancar(pedido);
		Mockito.verify(pedidos).executar(pedido);
	}

	@Test
	void deveNotificarPorEmail() throws Exception {
		pedidoService.lancar(pedido);
		Mockito.verify(notificadorEmail).executar(pedido);
	}

	@Test
	void deveNotificarPorSms() throws Exception {
		pedidoService.lancar(pedido);
		Mockito.verify(notificadorSms).executar(pedido);
	}

	@Test
	public void devePagarPedidoPendente() throws Exception {

		Long codigoPedido = 135L;
		Pedido pedidoPendente = new Pedido();
		pedidoPendente.setStatus(StatusPedido.PENDENTE);
		Mockito.when(pedidos.buscarPeloCodigo(codigoPedido)).thenReturn(pedidoPendente);

		Pedido pedidoPago = pedidoService.pagar(codigoPedido);

		assertEquals(StatusPedido.PAGO, pedidoPago.getStatus());
	}

	@Test
	public void deveNegarPagamento() throws Exception {

		Long codigoPedido = 135L;
		Pedido pedidoPendente = new Pedido();
		pedidoPendente.setStatus(StatusPedido.PAGO);
		Mockito.when(pedidos.buscarPeloCodigo(codigoPedido)).thenReturn(pedidoPendente);

		assertThrows(StatusPedidoInvalidoException.class, () -> {
			pedidoService.pagar(codigoPedido);
		});
	}

}
