package com.dwws.imo.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dwws.imo.dao.EmpregadorDAO;
import com.dwws.imo.dao.TrabalhadorDAO;
import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Empregador;
import com.dwws.imo.modelo.Trabalhador;


@Stateless
public class ValidatorService {

	@EJB 
	private EmpregadorDAO empregadorDao;
	
	@EJB
	private TrabalhadorDAO trabalhadorDao;
	
	public Empregador buscarEmpregadorPorCNPJ(String cnpj) {
		
		try {
			return this.empregadorDao.buscarPorCNPJ(cnpj);
		} 
		catch (EntidadeNaoEncontradaException e) {
			return null;
		}
	}
	
	public Empregador buscarEmpregadorPorUsuario(String usuario) {
		
		try {
			return this.empregadorDao.buscarPorUsuario(usuario);
		} 
		catch (EntidadeNaoEncontradaException e) {
			return null;
		}
	}
	
	public Trabalhador buscarTrabalhadorPorCPF(String cpf) {
		
		try {
			return this.trabalhadorDao.buscarPorCPF(cpf);
		} 
		catch (EntidadeNaoEncontradaException e) {
			return null;
		}
	}
	
	public Trabalhador buscarTrabalhadorPorUsuario(String usuario) {
		
		try {
			return this.trabalhadorDao.buscarPorUsuario(usuario);
		} 
		catch (EntidadeNaoEncontradaException e) {
			return null;
		}
	}
}
