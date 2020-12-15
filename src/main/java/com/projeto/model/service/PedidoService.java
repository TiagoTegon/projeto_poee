package com.projeto.model.service;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.dao.GenericDao;
import com.projeto.model.dao.PedidoDao;
import com.projeto.model.models.Pedido;

public class PedidoService extends ConexaoBancoService {

	private PedidoDao pedidoDao;

	public PedidoService() {
		this.pedidoDao = new PedidoDao(this.getEntityManager());
	}

	public Integer save(Pedido pedido) {
		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		toReturn = validarDigitacao(pedido);

		if(toReturn == VariaveisProjeto.DIGITACAO_OK) {
			try {
				trx.begin();
				this.getPedidoDao().save(pedido);
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

	public Integer update(Pedido pedido) {
		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		toReturn = validarDigitacao(pedido);

		if(toReturn == VariaveisProjeto.DIGITACAO_OK) {
			try {
				trx.begin();
				this.getPedidoDao().update(pedido);
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

	public Integer remove(Pedido pedido) {
		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		try {
			trx.begin();
			Pedido pedidoEncontrado = this.getPedidoDao().findById(pedido.getId());
			this.getPedidoDao().remove(pedidoEncontrado);
			trx.commit();
			toReturn = VariaveisProjeto.EXCLUSAO_REALIZADA;

		} catch(Exception ex) {
			ex.printStackTrace();
			if(trx.isActive()) {
				trx.rollback();
			}
			toReturn = VariaveisProjeto.ERRO_INCLUSAO;
		} finally {
			this.close();
		}
		return toReturn;
	}

	public Pedido findById(Integer id) {
		return this.getPedidoDao().findById(id);
	}

	public List<Pedido> findAll(){
		return this.getPedidoDao().findAll(Pedido.class);
	}

	public PedidoDao getPedidoDao() {
		return pedidoDao;
	}

	public Integer validarDigitacao(Pedido pedido) {
		if(VariaveisProjeto.digitacaoCampo(pedido.getData())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}
		if(VariaveisProjeto.digitacaoCampo(pedido.getPreco_total())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}

		return VariaveisProjeto.DIGITACAO_OK;
	}

	public Integer countTotalRegister() {
		return pedidoDao.countTotalRegister(Pedido.class);
	}

	public List<Pedido> listPedidoPginacao(Integer numeroPagina, Integer defaultPagina){
		return pedidoDao.listPedidoPagincao(numeroPagina, defaultPagina);
	}
}
