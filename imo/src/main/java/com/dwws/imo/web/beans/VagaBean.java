package com.dwws.imo.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.dwws.imo.exceptions.CandidaturaException;
import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Cargo;
import com.dwws.imo.modelo.Escolaridade;
import com.dwws.imo.modelo.Vaga;
import com.dwws.imo.service.LinkedDataService;
import com.dwws.imo.web.qualifiers.SelectMenuItem;
import com.dwws.imo.web.qualifiers.SelectMenuItemType;

@SuppressWarnings("cdi-ambiguous-dependency")
@Named
@SessionScoped
public class VagaBean extends IMOBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private LinkedDataService linkedDataService;

	@Inject @SelectMenuItem(SelectMenuItemType.CARGO)
	private List<SelectItem> listaSelectCargos;
	
	@Inject	@SelectMenuItem(SelectMenuItemType.ESCOLARIDADE)
	private List<SelectItem> listaSelectEscolaridade;
	
	@Inject
	private Vaga novaVaga;
	
	private String descricaoCargo;
	
	/*
	 * Dados passados à tela
	 */
	private List<Vaga> vagasEncontradas;
	private Vaga vagaRecuperada;
	/*
	 * Controles de estado de componentes 
	 */
	private boolean indicadorNovoCargo;
	private boolean exibirVagasDisponiveis;
	private boolean exibirBuscaVagas;
	private boolean exibirDetalhesVaga;
	
	/*
	 * Parâmetros recebidos da tela
	 */
	private Integer codigoCargoConsulta;
	private Integer codigoEscolaridadeConsulta;
	private Integer codigoVagaProcurada;
	private String usuarioTrabalhador;
	private String senhaTrabalhador;
	

	@PostConstruct
	public void inicializar() {
		setExibirBuscaVagas(true);
	}
	
	public void cadastrarVaga() {
		
		try {
			this.imoService.cadastrarVaga(novaVaga);
			this.novaVaga = new Vaga();
			this.indicadorNovoCargo = false;
			gerarListaSelectCargos();
		} 
		catch (EntidadeNaoEncontradaException e) {
			disponibilizarMensagemErro("Falha no cadastro da vaga: Empregador não encontrado no sistema.");
		}
	}
	
	
	public void buscarVagas() {
		
		try {
			this.vagasEncontradas = 
					this.imoService.buscarVagas(codigoEscolaridadeConsulta, codigoCargoConsulta);
			this.exibirVagasDisponiveis = true;
		} 
		catch (EntidadeNaoEncontradaException e) {
			this.exibirVagasDisponiveis = false;
			disponibilizarMensagemErro("Nenhuma vaga encontrada para os parâmetros informados.");
		}
	}
	
	
	public void buscarVaga() {
		this.vagaRecuperada = this.imoService.buscarVaga(this.codigoVagaProcurada);
		setExibirDetalhesVaga(true);
	}
	
	
	public void registrarCandidatura() {
		
		try {
			this.imoService.registrarCandidatura(
					usuarioTrabalhador, senhaTrabalhador, codigoVagaProcurada);
			setExibirBuscaVagas(true);
		} 
		catch (CandidaturaException e) {
			disponibilizarMensagemErro("Falha no registro da candidatura: " + e.getMessage());
		}
	}
	
	public void buscarDescricaoCargo() {
		this.descricaoCargo = 
				this.linkedDataService.buscarDescricaoCargo(this.vagaRecuperada.getCargo().getNome());
	}
	
	
	public void cancelarCandidatura() {
		this.descricaoCargo = null;
		setExibirDetalhesVaga(false);
	}
	
	public List<SelectItem> getListaSelectCargos() {
		return listaSelectCargos;
	}

	public Vaga getNovaVaga() {
		return novaVaga;
	}

	public List<SelectItem> getListaSelectEscolaridade() {
		return listaSelectEscolaridade;
	}

	@Produces @SelectMenuItem(SelectMenuItemType.CARGO)
	public List<SelectItem> gerarListaSelectCargos() {
		this.listaSelectCargos = new ArrayList<SelectItem>();
		for (Cargo cargo : imoService.obterCargosDisponiveis()) 
			this.listaSelectCargos.add(new SelectItem(cargo.getCodigo(), cargo.getNome()));
		return this.listaSelectCargos;
	}
	
	@Produces @SelectMenuItem(SelectMenuItemType.ESCOLARIDADE)
	public List<SelectItem> gerarListaSelectEscolaridade() {
		this.listaSelectEscolaridade = new ArrayList<SelectItem>();
		for (Escolaridade escolaridade : imoService.obterEscolaridadesDisponiveis()) 
			this.listaSelectEscolaridade.add(new SelectItem(escolaridade.getCodigo(), escolaridade.getDescricao()));
		return this.listaSelectEscolaridade;
	}

	protected void disponibilizarMensagemErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
	}

	public boolean isIndicadorNovoCargo() {
		return indicadorNovoCargo;
	}

	public void setIndicadorNovoCargo(boolean indicadorNovoCargo) {
		this.indicadorNovoCargo = indicadorNovoCargo;
	}

	public Integer getCodigoCargoConsulta() {
		return codigoCargoConsulta;
	}

	public void setCodigoCargoConsulta(Integer codigoCargoConsulta) {
		this.codigoCargoConsulta = codigoCargoConsulta;
	}

	public Integer getCodigoEscolaridadeConsulta() {
		return codigoEscolaridadeConsulta;
	}

	public void setCodigoEscolaridadeConsulta(Integer codigoEscolaridadeConsulta) {
		this.codigoEscolaridadeConsulta = codigoEscolaridadeConsulta;
	}

	public List<Vaga> getVagasEncontradas() {
		return vagasEncontradas;
	}

	public boolean isExibirVagasDisponiveis() {
		return exibirVagasDisponiveis;
	}

	public void setExibirVagasDisponiveis(boolean exibirVagasDisponiveis) {
		this.exibirVagasDisponiveis = exibirVagasDisponiveis;
	}

	public boolean isExibirBuscaVagas() {
		return exibirBuscaVagas;
	}

	public void setExibirBuscaVagas(boolean exibirBuscaVagas) {
		this.exibirBuscaVagas = exibirBuscaVagas;
		this.exibirDetalhesVaga = !exibirBuscaVagas;
	}

	public boolean isExibirDetalhesVaga() {
		return exibirDetalhesVaga;
	}

	public void setExibirDetalhesVaga(boolean exibirDetalhesVaga) {
		this.exibirDetalhesVaga = exibirDetalhesVaga;
		this.exibirBuscaVagas = !exibirDetalhesVaga;
	}

	public void setCodigoVagaProcurada(Integer codigoVagaProcurada) {
		this.codigoVagaProcurada = codigoVagaProcurada;
	}

	public Vaga getVagaRecuperada() {
		return vagaRecuperada;
	}

	public String getUsuarioTrabalhador() {
		return usuarioTrabalhador;
	}

	public void setUsuarioTrabalhador(String usuarioTrabalhador) {
		this.usuarioTrabalhador = usuarioTrabalhador;
	}

	public String getSenhaTrabalhador() {
		return senhaTrabalhador;
	}

	public void setSenhaTrabalhador(String senhaTrabalhador) {
		this.senhaTrabalhador = senhaTrabalhador;
	}

	public String getDescricaoCargo() {
		return descricaoCargo;
	}

	
}
