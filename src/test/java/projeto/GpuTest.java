package projeto;

import java.util.List;

import org.junit.Test;

import com.projeto.model.models.Gpu;
import com.projeto.model.service.GpuService;

public class GpuTest {

	//@Test(expected = Exception.class)
	public void salvarGpuBancoDadosTeste() {
		
		Gpu gpu = new Gpu();
		
		GpuService gpuService = new GpuService();
		
		gpu.setMarca("AMD");
		gpu.setModelo("RX 580");
		gpu.setQtd_estoque(200);
		gpu.setPreco(1531);
		gpu.setVram(8);
		gpu.setTipo_memoria("GDDR5");
		gpu.setFabricante("Sapphire");

		gpuService.save(gpu);
		
		System.out.println("Gravando usuário no banco de dados");
		
		gpu = new Gpu();
		
		gpu.setMarca("NVIDIA");
		gpu.setModelo("GTX 1650");
		gpu.setQtd_estoque(300);
		gpu.setPreco(1300);
		gpu.setVram(4);
		gpu.setTipo_memoria("GDDR6");
		gpu.setFabricante("Gigabyte");
		
		gpuService = new GpuService();
		
		gpuService.save(gpu);
		
		System.out.println("Gravando usuário no banco de dados");
		
		gpu = new Gpu();
		
		gpu.setMarca("NVIDIA");
		gpu.setModelo("GTX 1660 super");
		gpu.setQtd_estoque(350);
		gpu.setPreco(2200);
		gpu.setVram(6);
		gpu.setTipo_memoria("GDDR6");
		gpu.setFabricante("ASUS");
		
		gpuService = new GpuService();
		
		gpuService.save(gpu);
		
		System.out.println("Gravando usuário no banco de dados");
	}
	
	//@Test(expected = Exception.class)
	public void alterarGpuBancoDadosTeste() {
		
		Gpu gpu = new Gpu();
		
		gpu.setId(2);
		
		GpuService gpuService = new GpuService();
		
		gpu = gpuService.findById(gpu.getId());
		
		System.out.println(gpu.toString());
		
		gpu.setModelo("GTX 1650 WINDFORCE OC");
		
		gpuService.update(gpu);
		
		System.out.println("Alteração usuário no banco de dados");
		
		//assertTrue(true);
	}
	
	@Test(expected = Exception.class)
	public void listarTodosGpus() {
		
		GpuService gpuService = new GpuService();
		
		List<Gpu> listaGpu = gpuService.findAll();
		
		for(Gpu gpu : listaGpu) {
			System.out.println(gpu.toString());
		}
	}
	
	//@Test(expected = Exception.class)
	public void excluirGpuDaTabela() {
		
		Gpu gpu = new Gpu();
		
		gpu.setId(2);
		GpuService gpuService = new GpuService();
		
		gpuService.remove(gpu);
	}
}
