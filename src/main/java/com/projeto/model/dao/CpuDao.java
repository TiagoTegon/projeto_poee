package com.projeto.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.projeto.model.models.Cpu;

public class CpuDao extends GenericDao<Cpu, Integer>{

	public CpuDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cpu> listCpuPaginacao(Integer numeroPagina, Integer defaultPagina) {
		List<Cpu> listaCpu = new ArrayList<Cpu>();
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Cpu u")
											.setFirstResult(numeroPagina)
											.setMaxResults(defaultPagina);
		listaCpu = query.getResultList();
		
		return listaCpu;
		
	}
}
