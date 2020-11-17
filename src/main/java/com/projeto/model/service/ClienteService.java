package com.projeto.model.service;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.dao.ClienteDao;
import com.projeto.model.models.Cliente;

public class ClienteService extends ConexaoBancoService {
	
	private ClienteDao clienteDao;
	
	public ClienteService() {
		this.clienteDao = new ClienteDao(this.getEntityManager());
	}
	
	public Integer save(Cliente cliente) {
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		if(validarDigitacao(cliente) == VariaveisProjeto.DIGITACAO_OK) {
			try {
				trx.begin();
				this.getClienteDao().save(cliente);
				trx.commit();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				if(trx.isActive()) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_INCLUSAO;
			} finally {
				this.close();
			}
		} else {
			toReturn = VariaveisProjeto.CAMPO_VAZIO;
		}
		return toReturn;
	}
	
	public Integer update(Cliente cliente) {
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		if(validarDigitacao(cliente) == VariaveisProjeto.DIGITACAO_OK) {
			try {
				trx.begin();
				this.getClienteDao().update(cliente);
				trx.commit();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				if(trx.isActive()) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_ALTERACAO;
			} finally {
				this.close();
			}
		} else {
			toReturn = VariaveisProjeto.CAMPO_VAZIO;
		}
		return toReturn;
	}
	
	public Integer remove(Cliente cliente) {
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
			try {
				trx.begin();
				Cliente clienteEncontrado = this.getClienteDao().findById(cliente.getId());
				this.getClienteDao().remove(clienteEncontrado);
				trx.commit();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				if(trx.isActive()) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_EXCLUSAO;
			} finally {
				this.close();
			}
		return toReturn;
	}
	
	public Cliente findById(Integer id) {
		return this.getClienteDao().findById(id);
	}
	
	public List<Cliente> findAll(){
		return this.getClienteDao().findAll(Cliente.class);
	}
	
	public Integer validarDigitacao(Cliente cliente) {
		if(VariaveisProjeto.digitacaoCampo(cliente.getCpf())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}
		return VariaveisProjeto.DIGITACAO_OK;
	}
	
	public ClienteDao getClienteDao() {
		return clienteDao;
	}
}
