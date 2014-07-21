package com.dwws.imo.dao.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.dwws.imo.dao.EscolaridadeDAO;
import com.dwws.imo.modelo.Escolaridade;

@Stateless
public class EscolaridadeJPADAO implements EscolaridadeDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Escolaridade> buscarLista() {
		
		CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		
		CriteriaQuery<Escolaridade> query = criteriaBuilder.createQuery(Escolaridade.class);
		Root<Escolaridade> root = query.from(Escolaridade.class);
		query.select(root).orderBy(criteriaBuilder.asc(root.get("codigo")));
		
		return this.em.createQuery(query).getResultList();
	}

}
