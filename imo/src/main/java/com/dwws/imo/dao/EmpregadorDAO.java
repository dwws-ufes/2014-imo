package com.dwws.imo.dao;

import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Empregador;

public interface EmpregadorDAO {
	
	void criar(Empregador empregador); 
	
	Empregador buscar(String usuario, String senha) throws EntidadeNaoEncontradaException;
	
	Empregador buscarPorCNPJ(String cnpj) throws EntidadeNaoEncontradaException;
	
	Empregador buscarPorUsuario(String usuario) throws EntidadeNaoEncontradaException;
}
