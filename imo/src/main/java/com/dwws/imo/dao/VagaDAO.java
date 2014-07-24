package com.dwws.imo.dao;

import java.util.List;

import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Vaga;

public interface VagaDAO {

	void criar(Vaga vaga);
	
	List<Vaga> buscarLista(Integer escolaridade, Integer cargo) throws EntidadeNaoEncontradaException;
	
	Vaga buscar(Integer id);

	List<Vaga> buscarSubLista(Integer escolaridade, Integer cargo, int indice,
			int tamanho) throws EntidadeNaoEncontradaException;
	
	int obterQuantitativoDisponivel();
	
	int obterQuantitativoDisponivel(Integer escolaridade, Integer cargo);
}
