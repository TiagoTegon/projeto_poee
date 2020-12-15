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

		toReturn = validarDigitacao(cliente);

		if(toReturn == VariaveisProjeto.DIGITACAO_OK) {
			try {
				trx.begin();
				this.getClienteDao().save(cliente);
				trx.commit();
				toReturn = VariaveisProjeto.INCLUSAO_REALIZADA;

			} catch(Exception ex) {
				ex.printStackTrace();
				if(trx.isActive()) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_INCLUSAO;
			} finally {
				this.close();
			}
		} 
		return toReturn;
	}

	public Integer update(Cliente cliente) {
		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		toReturn = validarDigitacao(cliente);

		if(toReturn == VariaveisProjeto.DIGITACAO_OK) {
			try {
				trx.begin();
				this.getClienteDao().update(cliente);
				trx.commit();
				toReturn = VariaveisProjeto.ALTERACAO_REALIZADA;

			} catch(Exception ex) {
				ex.printStackTrace();
				if(trx.isActive()) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_ALTERACAO;
			} finally {
				this.close();
			}
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
			toReturn = VariaveisProjeto.EXCLUSAO_REALIZADA;

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
			return VariaveisProjeto.CLIENTE_CPF;
		}
		if(VariaveisProjeto.digitacaoCampo(cliente.getNome())) {
			return VariaveisProjeto.CLIENTE_NOME;
		}

		if(VariaveisProjeto.digitacaoCampo(cliente.getEmail())) {
			return VariaveisProjeto.CLIENTE_EMAIL;
		}

		if(VariaveisProjeto.digitacaoCampo(cliente.getTelefone())) {
			return VariaveisProjeto.CLIENTE_TELEFONE;
		}

		return VariaveisProjeto.DIGITACAO_OK;
	}

	public ClienteDao getClienteDao() {
		return clienteDao;
	}

	public Integer countTotalRegister() {
		return clienteDao.countTotalRegister(Cliente.class);
	}

	public List<Cliente> listClientePaginacao(Integer numeroPagina, Integer defaultPagina){
		return clienteDao.listClientePaginacao(numeroPagina, defaultPagina);
	}
}
