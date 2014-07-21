package com.dwws.imo.dao;

import java.util.List;

import com.dwws.imo.modelo.Cargo;

public interface CargoDAO {

	void criar(Cargo cargo);
	
	List<Cargo> buscarLista();
}
