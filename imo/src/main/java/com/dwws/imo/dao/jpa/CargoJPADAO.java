package com.dwws.imo.dao.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.dwws.imo.dao.CargoDAO;
import com.dwws.imo.modelo.Cargo;

@Stateless
public class CargoJPADAO extends IMOJPADAO implements CargoDAO {

	
	@Override
	public void criar(Cargo cargo) {
		this.em.persist(cargo);
	}
	
	@Override
	public List<Cargo> buscarLista() {
		
		CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		
		CriteriaQuery<Cargo> query = criteriaBuilder.createQuery(Cargo.class);
		Root<Cargo> root = query.from(Cargo.class);
		query.select(root).orderBy(criteriaBuilder.asc(root.get("codigo")));
		
		return this.em.createQuery(query).getResultList();
	}

}
