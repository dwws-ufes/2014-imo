package com.dwws.imo.dao.jpa;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.dwws.imo.dao.TrabalhadorDAO;
import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Trabalhador;

@Stateless
public class TrabalhadorJPADAO extends IMOJPADAO implements TrabalhadorDAO {

	@Override
	public void criar(Trabalhador trabalhador) {
		this.em.persist(trabalhador);
	}

	@Override
	public Trabalhador buscar(String usuario, String senha)
			throws EntidadeNaoEncontradaException {
		
		try {
			return (Trabalhador) this.em.createNamedQuery(
					IMONamedQuery.BUSCA_TRABALHADOR_POR_USUARIO_SENHA.name())
					.setParameter("usuario", usuario)
					.setParameter("senha", senha)
					.getSingleResult();
		}
		catch (NoResultException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage(), e);
		}
	}

}
