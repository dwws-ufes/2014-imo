package com.dwws.imo.dao;

import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Trabalhador;

public interface TrabalhadorDAO {
	
	void criar(Trabalhador trabalhador);
	
	Trabalhador buscar(String usuario, String senha) throws EntidadeNaoEncontradaException;
	
	Trabalhador buscarPorUsuario(String usuario) throws EntidadeNaoEncontradaException;
	
	Trabalhador buscarPorCPF(String cpf) throws EntidadeNaoEncontradaException;

}
