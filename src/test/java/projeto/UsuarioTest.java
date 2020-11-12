package projeto;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.projeto.model.models.Usuario;
import com.projeto.model.service.UsuarioService;

public class UsuarioTest {
	
	//@Test(expected = Exception.class)
	public void salvarUsuarioBancoDadosTeste() {
		
		Usuario usuario = new Usuario();
		
		//usuario.setId(2);
		usuario.setUsername("Maria Fernanda");
		usuario.setPassword("2468");
		usuario.setEmail("maria@maria.br");
		usuario.setAtivo(false);
		usuario.setAdmin(false);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.save(usuario);
		
		System.out.println("Gravando usuário no banco de dados");
		
		usuario = new Usuario();
		
		//usuario.setId(2);
		usuario.setUsername("Clara Vieria");
		usuario.setPassword("1379");
		usuario.setEmail("clara@clara.br");
		usuario.setAtivo(false);
		usuario.setAdmin(false);
		
		usuarioService = new UsuarioService();
		
		usuarioService.save(usuario);
		
		System.out.println("Gravando usuário no banco de dados");
		
		//assertTrue(true);
	}
	
	//@Test(expected = Exception.class)
	public void alterarUsuarioBancoDadosTeste() {
		
		Usuario usuario = new Usuario();
		
		usuario.setId(2);
		
		//usuario.setUsername("Roberto Carlos da Silva");
		//usuario.setPassword("123456");
		//usuario.setEmail("roberto@carlos.com.br");
		//usuario.setAtivo(false);
		//usuario.setAdmin(false);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuario = usuarioService.findById(usuario.getId());
		
		System.out.println(usuario.toString());
		
		usuario.setEmail("joao@joao.com.br");
		
		usuarioService.update(usuario);
		
		System.out.println("Alteração usuário no banco de dados");
		
		//assertTrue(true);
	}
	
	@Test(expected = Exception.class)
	public void listarTodosUsuarioTabelaUsuario() {
		
		UsuarioService usuarioService = new UsuarioService();
		
		List<Usuario> listaUsuario = usuarioService.findAll();
		
		for(Usuario usuario : listaUsuario) {
			System.out.println(usuario.toString());
		}
	}
	
	//@Test(expected = Exception.class)
	public void excluirUsuarioDaTabela() {
		
		Usuario usuario = new Usuario();
		
		usuario.setId(5);
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.remove(usuario);
	}
}
