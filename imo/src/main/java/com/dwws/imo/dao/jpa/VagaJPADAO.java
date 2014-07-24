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
		
		query.where(queryPredicate.toArray(new Predicate[queryPredicate.size()]))
			 .orderBy(criteriaBuilder.asc(root.get("codigo")));
		
		vagasEncontradas = this.em.createQuery(query).getResultList();
		
		if (vagasEncontradas == null || vagasEncontradas.isEmpty())
			throw new EntidadeNaoEncontradaException();
		
		return vagasEncontradas;
	}
	
	
	@Override
	public List<Vaga> buscarSubLista(Integer escolaridade, Integer cargo, int indice, int tamanho) throws EntidadeNaoEncontradaException {
		
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
		
		query.where(queryPredicate.toArray(new Predicate[queryPredicate.size()]))
			 .orderBy(criteriaBuilder.asc(root.get("codigo")));
		
		vagasEncontradas = this.em.createQuery(query).
				setFirstResult(indice).setMaxResults(tamanho).getResultList();
		
		if (vagasEncontradas == null || vagasEncontradas.isEmpty())
			throw new EntidadeNaoEncontradaException();
		
		return vagasEncontradas;
	}

	@Override
	public Vaga buscar(Integer id) {
		return this.em.find(Vaga.class, id);
	}

	@Override
	public int obterQuantitativoDisponivel() {
		return ((Integer) this.em.createNamedQuery(
					IMONamedQuery.CONTAGEM_VAGAS_DISPONIVEIS.name())
						.getSingleResult()).intValue();
	}

	@Override
	public int obterQuantitativoDisponivel(Integer escolaridade, Integer cargo) {
		
		CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Vaga> fromBuilder = criteriaQuery.from(Vaga.class);
		criteriaQuery.select(criteriaBuilder.count(fromBuilder));
		
		List<Predicate> queryPredicate = new LinkedList<Predicate>();
		
		if (escolaridade != null) {
			queryPredicate.add(criteriaBuilder.equal(fromBuilder.get("escolaridade").<Integer> get("codigo"), escolaridade));
		}
		if (cargo != null) {
			queryPredicate.add(criteriaBuilder.equal(fromBuilder.get("cargo").<Integer> get("codigo"), cargo));
		}
		
		criteriaQuery.where(queryPredicate.toArray(new Predicate[queryPredicate.size()]));
		
		return this.em.createQuery(criteriaQuery).getSingleResult().intValue();
	}

}
