package projeto;

import java.util.List;

import org.junit.Test;

import com.projeto.model.models.Cpu;
import com.projeto.model.service.CpuService;

public class CpuTest {
	
	//@Test(expected = Exception.class)
	public void salvarCpuBancoDadosTeste() {
		
		Cpu cpu = new Cpu();
		
		
		CpuService cpuService = new CpuService();
		
		cpu.setMarca("AMD");
		cpu.setModelo("Ryzen 3 3200G");
		cpu.setQtd_estoque(500);
		cpu.setPreco(750);
		cpu.setNucleos(4);
		cpu.setThreads(4);
		cpu.setCache(4);
		cpu.setVelocidade("3.6GHz");
		
		cpuService.save(cpu);
		
		System.out.println("Gravando usuário no banco de dados");
		
		cpu = new Cpu();
		
		cpu.setMarca("Intel");
		cpu.setModelo("Core i5-9400F");
		cpu.setQtd_estoque(1000);
		cpu.setPreco(1100);
		cpu.setNucleos(6);
		cpu.setThreads(6);
		cpu.setCache(9);
		cpu.setVelocidade("2.9GHz");
		
		cpuService = new CpuService();
		
		cpuService.save(cpu);
		
		System.out.println("Gravando usuário no banco de dados");
		
		//assertTrue(true);
	}
	
	//@Test(expected = Exception.class)
	public void alterarCpuBancoDadosTeste() {
		
		Cpu cpu = new Cpu();
		
		cpu.setId(2);
		
		CpuService cpuService = new CpuService();
		
		cpu = cpuService.findById(cpu.getId());
		
		System.out.println(cpu.toString());
		
		cpu.setModelo("Core i5-9400F Coffe Lake");
		
		cpuService.update(cpu);
		
		System.out.println("Alteração usuário no banco de dados");
		
		//assertTrue(true);
	}
	
	@Test(expected = Exception.class)
	public void listarTodosCpuTabelaUsuario() {
		
		CpuService cpuService = new CpuService();
		
		List<Cpu> listaCpu = cpuService.findAll();
		
		for(Cpu cpu : listaCpu) {
			System.out.println(cpu.toString());
		}
	}
	
	//@Test(expected = Exception.class)
	public void excluirCpuDaTabela() {
		
		Cpu cpu = new Cpu();
		
		cpu.setId(2);
		CpuService cpuService = new CpuService();
		
		cpuService.remove(cpu);
	}
	
}
