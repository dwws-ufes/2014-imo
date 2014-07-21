package com.dwws.imo.dao.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.dwws.imo.dao.EmpregadorDAO;
import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Empregador;

@Stateless
public class EmpregadorJPADAO implements EmpregadorDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void criar(Empregador empregador) {
		this.em.persist(empregador);
	}

	@Override
	public Empregador buscar(String usuario, String senha) throws EntidadeNaoEncontradaException {
		
		try {
			return (Empregador) this.em.createNamedQuery(
					IMONamedQuery.BUSCA_EMPREGADOR_POR_USUARIO_SENHA.name())
					.setParameter("usuario", usuario)
					.setParameter("senha", senha)
					.getSingleResult();
		}
		catch (NoResultException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage(), e);
		}
	}
	
	
}
