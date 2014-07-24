package com.dwws.imo.web.components;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Vaga;
import com.dwws.imo.service.IMOService;

public class VagaLazyList extends LazyDataModel<Vaga> {

	private static final long serialVersionUID = -577018872478775557L;
	
	@EJB
	private IMOService imoService;
	
	private List<Vaga> vagas;
	private Integer codigoCargoConsulta;
	private Integer codigoEscolaridadeConsulta;

	@Override
	public Vaga getRowData(String codigoVaga) {
		for (Vaga vaga : this.vagas) 
			if (vaga.getCodigo().equals(Integer.valueOf(codigoVaga)))
				return vaga;
		return null;
	}

	@Override
	public Object getRowKey(Vaga vaga) {
		return vaga.getCodigo();
	}

	@Override
	public List<Vaga> load(int indicePrimero, int quantidadePorPagina, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		
		if (getRowCount() <= 0) 
			setRowCount(this.imoService.obterQuantitativoVagas(
					codigoEscolaridadeConsulta, codigoCargoConsulta));
		
		setPageSize(quantidadePorPagina);
		
		try {
			this.vagas = this.imoService.buscarVagas(
					codigoEscolaridadeConsulta, codigoCargoConsulta, indicePrimero, quantidadePorPagina);
		} 
		catch (EntidadeNaoEncontradaException e) {
			this.vagas = null;
		}
		
		return this.vagas;
	}

	public void setCodigoCargoConsulta(Integer codigoCargoConsulta) {
		this.codigoCargoConsulta = codigoCargoConsulta;
	}

	public void setCodigoEscolaridadeConsulta(Integer codigoEscolaridadeConsulta) {
		this.codigoEscolaridadeConsulta = codigoEscolaridadeConsulta;
	}

}
