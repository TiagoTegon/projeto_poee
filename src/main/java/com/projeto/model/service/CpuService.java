package com.projeto.model.service;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.dao.CpuDao;
import com.projeto.model.models.Cpu;

public class CpuService extends ConexaoBancoService {

	private CpuDao cpuDao;

	public CpuService() {
		this.cpuDao = new CpuDao(this.getEntityManager());
	}

	public Integer save(Cpu cpu) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		toReturn = validarDigitacao(cpu);

		if (toReturn == VariaveisProjeto.DIGITACAO_OK) {

			try {

				trx.begin();
				this.getCpuDao().save(cpu);
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

	public Integer update(Cpu cpu) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		toReturn = validarDigitacao(cpu);

		if (toReturn == VariaveisProjeto.DIGITACAO_OK) {

			try {

				trx.begin();
				this.getCpuDao().update(cpu);
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

	public Integer remove(Cpu cpu) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		try {

			trx.begin();
			Cpu cpuEncontrada = this.getCpuDao().findById(cpu.getId());
			this.getCpuDao().remove(cpuEncontrada);
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

	public Cpu findById(Integer id) {
		return this.getCpuDao().findById(id);
	}

	public List<Cpu> findAll(){
		return this.getCpuDao().findAll(Cpu.class);
	}

	private Integer validarDigitacao(Cpu cpu) {

		if (VariaveisProjeto.digitacaoCampo(cpu.getMarca())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(cpu.getModelo())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(cpu.getQtd_estoque())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(cpu.getPreco())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(cpu.getNucleos())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(cpu.getThreads())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(cpu.getCache())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		if (VariaveisProjeto.digitacaoCampo(cpu.getVelocidade())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}
		return VariaveisProjeto.DIGITACAO_OK;
	}

	private CpuDao getCpuDao() {
		return cpuDao;
	}

	public Integer countTotalRegister() {

		return cpuDao.countTotalRegister(Cpu.class);
	}

	public List<Cpu> listCpuPaginacao(Integer numeroPagina, Integer defaultPagina) {

		return cpuDao.listCpuPaginacao(numeroPagina, defaultPagina);
	}	

}
