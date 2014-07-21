package com.dwws.imo.dao.jpa;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dwws.imo.dao.VagaDAO;
import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Vaga;

@Stateless
public class VagaJPADAO extends IMOJPADAO implements VagaDAO {

	@Override
	public void criar(Vaga vaga) {
		this.em.persist(vaga);
	}

	@Override
	public List<Vaga> buscarLista(Integer escolaridade, Integer cargo) throws EntidadeNaoEncontradaException {
		
		List<Vaga> vagasEncontradas = null;
		
		CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		
		CriteriaQuery<Vaga> query = criteriaBuilder.createQuery(Vaga.class);
		Root<Vaga> root = query.from(Vaga.class);
		query.select(root);
		
		List<Predicate> queryPredicate = new LinkedList<Predicate>();
		
		if (escolaridade != null) {
			queryPredicate.add(criteriaBuilder.equal(root.get("escolaridade").<Integer> get("codigo"), escolaridade));
		}
		if (cargo != null) {
			queryPredicate.add(criteriaBuilder.equal(root.get("cargo").<Integer> get("codigo"), cargo));
		}
		
		query.where(queryPredicate.toArray(new Predicate[queryPredicate.size()]));
		vagasEncontradas = this.em.createQuery(query).getResultList();
		
		if (vagasEncontradas == null || vagasEncontradas.isEmpty())
			throw new EntidadeNaoEncontradaException();
		
		return vagasEncontradas;
	}

	@Override
	public Vaga buscar(Integer id) {
		return this.em.find(Vaga.class, id);
	}

}
