package com.dwws.imo.dao;

import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Candidatura;

public interface CandidaturaDAO {

	void criar(Candidatura candidatura);
	
	Candidatura buscar(Integer idTrabalhador, Integer codigoVaga) throws EntidadeNaoEncontradaException;
}
