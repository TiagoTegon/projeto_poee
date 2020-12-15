package projeto;

import java.util.List;

import org.junit.Test;

import com.projeto.model.models.Cliente;
import com.projeto.model.models.Pedido;
import com.projeto.model.service.PedidoService;

public class PedidoTest {
	
	//@Test(expected = Exception.class)
	public void salvarPedidoBancoDadosTeste() {
		
		Pedido pedido = new Pedido();

		pedido.setData("06/06/2020");
		pedido.setPreco_total(750);
		
		Cliente cliente = new Cliente();
		cliente.setId(2);
		pedido.setCliente(cliente);
	
		
		PedidoService pedidoService = new PedidoService();
		
		pedidoService.save(pedido);
		
		System.out.println("Gravando pedido no banco de dados");
		
	}
	
	//@Test(expected = Exception.class)
	public void alterarPedidoBancoDadosTeste() {
		
		Pedido pedido = new Pedido();
		
		pedido.setId(1);
		
		PedidoService pedidoService = new PedidoService();
		
		pedido = pedidoService.findById(pedido.getId());
		
		System.out.println(pedido.toString());
		
		pedido.setPreco_total(100);
		
		pedidoService.update(pedido);
		
		System.out.println("Alterando pedido no banco de dados");
		
	}
	
	@Test(expected = Exception.class)
	public void listarTodosPedidos() {
		
		PedidoService pedidoService = new PedidoService();
		
		List<Pedido> listaPedido = pedidoService.findAll();
		
		for(Pedido pedido : listaPedido) {
			System.out.println(pedido.toString());
		}
	}
	
	//@Test(expected = Exception.class)
	public void excluirPedidoDaTabela() {
		
		Pedido pedido = new Pedido();
		
		pedido.setId(2);
		
		PedidoService pedidoService = new PedidoService();
		
		pedidoService.remove(pedido);
	}
}
