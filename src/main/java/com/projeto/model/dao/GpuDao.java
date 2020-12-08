package com.projeto.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.projeto.model.models.Gpu;

public class GpuDao extends GenericDao<Gpu, Integer> {

	public GpuDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<Gpu> listGpuPaginacao(Integer numeroPagina, Integer defaultPagina) {
		List<Gpu> listaGpu = new ArrayList<Gpu>();
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Gpu u")
											.setFirstResult(numeroPagina)
											.setMaxResults(defaultPagina);
		listaGpu = query.getResultList();
		
		return listaGpu;
		
	}
}
