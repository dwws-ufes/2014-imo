package com.dwws.imo.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dwws.imo.dao.CandidaturaDAO;
import com.dwws.imo.dao.CargoDAO;
import com.dwws.imo.dao.EmpregadorDAO;
import com.dwws.imo.dao.EscolaridadeDAO;
import com.dwws.imo.dao.TrabalhadorDAO;
import com.dwws.imo.dao.VagaDAO;
import com.dwws.imo.exceptions.CandidaturaException;
import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Candidatura;
import com.dwws.imo.modelo.Cargo;
import com.dwws.imo.modelo.Empregador;
import com.dwws.imo.modelo.Escolaridade;
import com.dwws.imo.modelo.Trabalhador;
import com.dwws.imo.modelo.Vaga;

@Stateless
public class IMOService {
	
	@EJB private EmpregadorDAO empregadorDao;
	@EJB private VagaDAO vagaDao;
	@EJB private EscolaridadeDAO escolaridadeDao;
	@EJB private CargoDAO cargoDao;
	@EJB private TrabalhadorDAO trabalhadorDao;
	@EJB private CandidaturaDAO candidaturaDao;
	
	
	public List<Cargo> obterCargosDisponiveis() {
		return this.cargoDao.buscarLista();
	}
	
	
	public List<Escolaridade> obterEscolaridadesDisponiveis() {
		return this.escolaridadeDao.buscarLista();
	}
	
	
	public void cadastrarEmpregador(Empregador empregador) {
		this.empregadorDao.criar(empregador);
	}
	
	public void cadastrarVaga(Vaga novaVaga) throws EntidadeNaoEncontradaException {
		
		// Verificando se o empregador está cadastrado na base
		Empregador empregadorCadastrado = this.empregadorDao.buscar(
				novaVaga.getUsuarioEmpregador(), novaVaga.getSenhaEmpregador());
		novaVaga.setEmpregador(empregadorCadastrado);
		
		// Verificando se o cargo da vaga existe na base
		if (novaVaga.pertenceNovoCargo()) {
			this.cargoDao.criar(novaVaga.getCargo());
		}
		
		this.vagaDao.criar(novaVaga);
	}
	
	public List<Vaga> buscarVagas(Integer codigoEscolaridade, Integer codigoCargo) 
			throws EntidadeNaoEncontradaException {
		return this.vagaDao.buscarLista(codigoEscolaridade, codigoCargo);
	}
	
	
	public List<Vaga> buscarVagas(Integer codigoEscolaridade, Integer codigoCargo, int indice, int quantidade) 
			throws EntidadeNaoEncontradaException {
		return this.vagaDao.buscarSubLista(codigoEscolaridade, codigoCargo, indice, quantidade);
	}
	
	public Vaga buscarVaga(Integer id) {
		return this.vagaDao.buscar(id);
	}
	
	public int obterQuantitativoVagas(Integer codigoEscolaridade, Integer codigoCargo) {
		return this.vagaDao.obterQuantitativoDisponivel(codigoEscolaridade, codigoCargo);
	}
	
	public void cadastrarTrabalhador(Trabalhador trabalhador) {
		this.trabalhadorDao.criar(trabalhador);
	}
	
	public Trabalhador buscaTrabalhador(String usuario, String senha) 
			throws EntidadeNaoEncontradaException {
		return this.trabalhadorDao.buscar(usuario, senha);
	}
	
	public void registrarCandidatura(String usuario, String senha, Integer codigoVaga) 
			throws CandidaturaException {
		
		Trabalhador trabalhador;
		
		try {
			trabalhador = this.trabalhadorDao.buscar(usuario, senha);
		} 
		catch (EntidadeNaoEncontradaException e) {
			throw new CandidaturaException("Trabalhador não encontrado no sistema.");
		}
		
		Vaga vaga = this.vagaDao.buscar(codigoVaga);
		
		Candidatura candidatura;
		try {
			candidatura = this.candidaturaDao.buscar(trabalhador.getId(), vaga.getCodigo());
			if (candidatura != null) 
				throw new CandidaturaException("Candidatura já existente no sistema.");
		} 
		catch (EntidadeNaoEncontradaException e) {
			candidatura = new Candidatura();
		}
		
		candidatura.setTrabalhador(trabalhador);
		candidatura.setVaga(vaga);
		
		this.candidaturaDao.criar(candidatura);
	}

}