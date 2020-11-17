package projeto;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.projeto.model.models.Cliente;
import com.projeto.model.service.ClienteService;

public class ClienteTest {
	
	//@Test(expected = Exception.class)
	public void salvarClienteBancoDadosTeste() {
		
		Cliente cliente = new Cliente();
		
		cliente.setNome("Jovi Rone");
		cliente.setEmail("jovijovi@gmail.com");
		cliente.setCpf("12345674956");
		cliente.setTelefone("18997123987");
		
		ClienteService clienteService = new ClienteService();
		
		clienteService.save(cliente);
		
		System.out.println("Gravando cliente no banco de dados");
		
		cliente = new Cliente();
		
		cliente.setNome("Rodrigo Santoro");
		cliente.setEmail("santoro@rodrigo.br");
		cliente.setCpf("10987654321");
		cliente.setTelefone("16991246879");
		
		clienteService = new ClienteService();
		
		clienteService.save(cliente);
		
		System.out.println("Gravando cliente no banco de dados");
		
	}
	
	//@Test(expected = Exception.class)
	public void alterarClienteBancoDadosTeste() {
		
		Cliente cliente = new Cliente();
		
		cliente.setId(2);
		
		ClienteService clienteService = new ClienteService();
		
		cliente = clienteService.findById(cliente.getId());
		
		System.out.println(cliente.toString());
		
		cliente.setEmail("rodrigo_santoro@rodrigo.br");
		
		clienteService.update(cliente);
		
		System.out.println("Alterando cliente no banco de dados");
	}
	
	@Test(expected = Exception.class)
	public void listarTodosClientes() {
		
		ClienteService clienteService = new ClienteService();
		
		List<Cliente> listaCliente = clienteService.findAll();
		
		for(Cliente cliente : listaCliente) {
			System.out.println(cliente.toString());
		}
	}
	
	//@Test(expected = Exception.class)
	public void excluirClienteBancoDados() {
		
		Cliente cliente = new Cliente();
		
		cliente.setId(4);
		ClienteService clienteService = new ClienteService();
		
		clienteService.remove(cliente);
	
	}
	
	
}
