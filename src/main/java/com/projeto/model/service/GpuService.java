package com.projeto.model.service;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.dao.GenericDao;
import com.projeto.model.dao.GpuDao;
import com.projeto.model.models.Gpu;

public class GpuService extends ConexaoBancoService {

	private GpuDao gpuDao;

	public GpuService() {
		this.gpuDao = new GpuDao(this.getEntityManager());
	}

	public Integer save(Gpu gpu) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		toReturn = validarDigitacao(gpu);

		if (toReturn == VariaveisProjeto.DIGITACAO_OK) {

			try {

				trx.begin();
				this.getGpuDao().save(gpu);
				trx.commit();
				toReturn = VariaveisProjeto.INCLUSAO_REALIZADA;

			} catch (Exception ex) {
				ex.printStackTrace();
				if (trx.isActive()) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_INCLUSAO;

			} finally {
				this.close();
			}
		} 
		return toReturn;
	}

	public Integer update(Gpu gpu) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		toReturn = validarDigitacao(gpu);

		if (toReturn == VariaveisProjeto.DIGITACAO_OK) {

			try {

				trx.begin();
				this.getGpuDao().update(gpu);
				trx.commit();
				toReturn = VariaveisProjeto.ALTERACAO_REALIZADA;

			} catch (Exception ex) {
				ex.printStackTrace();
				if (trx.isActive()) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_ALTERACAO;

			} finally {
				this.close();
			}
		} 
		return toReturn;
	}

	public Integer remove(Gpu gpu) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		try {

			trx.begin();
			Gpu gpuEncontrada = this.getGpuDao().findById(gpu.getId());
			this.getGpuDao().remove(gpuEncontrada);
			trx.commit();
			toReturn = VariaveisProjeto.EXCLUSAO_REALIZADA;

		} catch (Exception ex) {
			ex.printStackTrace();
			if (trx.isActive()) {
				trx.rollback();
			}
			toReturn = VariaveisProjeto.ERRO_EXCLUSAO;

		} finally {
			this.close();
		} 
		return toReturn;
	}


	public Gpu findById(Integer id) {
		return this.getGpuDao().findById(id);
	}

	public List<Gpu> findAll(){
		return this.getGpuDao().findAll(Gpu.class);
	}

	private Integer validarDigitacao(Gpu gpu) {

		if (VariaveisProjeto.digitacaoCampo(gpu.getMarca())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(gpu.getModelo())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(gpu.getQtd_estoque())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(gpu.getPreco())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(gpu.getVram())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(gpu.getTipo_memoria())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(gpu.getFabricante())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}
		return VariaveisProjeto.DIGITACAO_OK;
	}

	private GenericDao<Gpu, Integer> getGpuDao() {
		return gpuDao;
	}	

	public Integer countTotalRegister() {
		return gpuDao.countTotalRegister(Gpu.class);
	}

	public List<Gpu> listGpuPaginacao(Integer numeroPagina, Integer defaultPagina){
		return gpuDao.listGpuPaginacao(numeroPagina, defaultPagina);
	}
}
