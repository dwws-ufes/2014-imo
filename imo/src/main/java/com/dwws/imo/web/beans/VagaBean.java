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

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.dwws.imo.exceptions.CandidaturaException;
import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Cargo;
import com.dwws.imo.modelo.Escolaridade;
import com.dwws.imo.modelo.Vaga;
import com.dwws.imo.service.LinkedDataFacade;
import com.dwws.imo.web.components.VagaLazyList;
import com.dwws.imo.web.qualifiers.SelectMenuItem;
import com.dwws.imo.web.qualifiers.SelectMenuItemType;

@SuppressWarnings("cdi-ambiguous-dependency")
@Named
@SessionScoped
public class VagaBean extends IMOBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private LinkedDataFacade linkedDataFacade;

	@Inject @SelectMenuItem(SelectMenuItemType.CARGO)
	private List<SelectItem> listaSelectCargos;
	
	@Inject	@SelectMenuItem(SelectMenuItemType.ESCOLARIDADE)
	private List<SelectItem> listaSelectEscolaridade;
	
	@Inject
	private Vaga novaVaga;
	
	// Descrição do cargo obtida via Linked Data
	private String descricaoCargo;
	
	/*
	 * Dados passados à tela
	 */
	@Inject
	private LazyDataModel<Vaga> dataModelVagas;
	private Vaga vagaSelecionada;
	/*
	 * Controles de estado de componentes 
	 */
	private boolean indicadorNovoCargo;
	private boolean exibirTabelasVagas;
	
	/*
	 * Parâmetros recebidos da tela
	 */
	private Integer codigoCargoConsulta;
	private Integer codigoEscolaridadeConsulta;
	private String usuarioTrabalhador;
	private String senhaTrabalhador;
	

	@PostConstruct
	public void inicializar() {
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
		this.exibirTabelasVagas = true;
		((VagaLazyList) this.dataModelVagas).setCodigoCargoConsulta(codigoCargoConsulta);
		((VagaLazyList) this.dataModelVagas).setCodigoEscolaridadeConsulta(codigoEscolaridadeConsulta);
	}
	
	
	public void buscarVaga(SelectEvent event) {
		this.vagaSelecionada = this.imoService.buscarVaga(((Vaga) event.getObject()).getCodigo());
	}
	
	
	public void registrarCandidatura() {
		
		try {
			this.imoService.registrarCandidatura(
					usuarioTrabalhador, senhaTrabalhador, this.vagaSelecionada.getCodigo());
			RequestContext.getCurrentInstance().addCallbackParam("sucesso", true);
			showDialogInfoMessage("Resultado de Candidatura", "Candidatura efetuada com sucesso!");
		} 
		catch (CandidaturaException e) {
			RequestContext.getCurrentInstance().addCallbackParam("sucesso", false);
			disponibilizarMensagemErro("Falha no registro da candidatura: " + e.getMessage());
		}
	}
	
	public void cancelarCandidatura() {
		this.descricaoCargo = null;
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
	
	protected void showDialogInfoMessage(String header, String text) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, header, text);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
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

	public LazyDataModel<Vaga> getVagasEncontradas() {
		return dataModelVagas;
	}

	public LazyDataModel<Vaga> getDataModelVagas() {
		return dataModelVagas;
	}

	public void setDataModelVagas(LazyDataModel<Vaga> dataModelVagas) {
		this.dataModelVagas = dataModelVagas;
	}

	public Vaga getVagaSelecionada() {
		return vagaSelecionada;
	}

	public void setVagaSelecionada(Vaga vagaSelecionada) {
		this.vagaSelecionada = vagaSelecionada;
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

	public void setDescricaoCargo(String descricaoCargo) {
		this.descricaoCargo = descricaoCargo;
	}

	public boolean isExibirTabelasVagas() {
		return exibirTabelasVagas;
	}

	public void setExibirTabelasVagas(boolean exibirTabelasVagas) {
		this.exibirTabelasVagas = exibirTabelasVagas;
	}
	
}
