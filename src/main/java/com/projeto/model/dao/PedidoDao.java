package com.projeto.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.projeto.model.models.Pedido;

public class PedidoDao extends GenericDao<Pedido, Integer> {

	public PedidoDao(EntityManager entityManager) {
		super(entityManager);
	}

	public List<Pedido> listPedidoPagincao(Integer numeroPagina, Integer defaultPagina) {
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Pedido u")
																				.setFirstResult(numeroPagina)
																				.setMaxResults(defaultPagina);
		listaPedido = query.getResultList();
		return listaPedido;
	}

}
