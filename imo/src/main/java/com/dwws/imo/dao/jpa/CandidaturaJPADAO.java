package com.dwws.imo.dao.jpa;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.dwws.imo.dao.CandidaturaDAO;
import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Candidatura;

@Stateless
public class CandidaturaJPADAO extends IMOJPADAO implements CandidaturaDAO {

	@Override
	public void criar(Candidatura candidatura) {
		this.em.persist(candidatura);
	}

	@Override
	public Candidatura buscar(Integer idTrabalhador, Integer codigoVaga)
			throws EntidadeNaoEncontradaException {
		
		try {
			return (Candidatura) this.em.createNamedQuery(
					IMONamedQuery.BUSCA_CANDIDATURA_POR_IDTRABALHADOR_CODIGOVAGA.name())
					.setParameter("idTrabalhador", idTrabalhador)
					.setParameter("codigoVaga", codigoVaga)
					.getSingleResult();
		}
		catch (NoResultException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage(), e);
		}
	}

}
