package com.dwws.imo.web.beans;

import javax.inject.Inject;

import com.dwws.imo.annotations.CDIBean;
import com.dwws.imo.modelo.Empregador;

@CDIBean
public class EmpregadorBean extends IMOBean {
	
	@Inject
	private Empregador empregador;

	
	public void cadastrarEmpregador() {
		this.imoService.cadastrarEmpregador(empregador);
		showDialogInfoMessage("Resultado de Cadastro", "Cadastro de Empregador efetuado com sucesso!");
		this.empregador = new Empregador();
	}

	public Empregador getEmpregador() {
		return empregador;
	}
	
	public void setEmpregador(Empregador empregador) {
		this.empregador = empregador;
	}
	
	
	

}
