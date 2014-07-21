package com.dwws.imo.web.beans;

import javax.inject.Inject;

import com.dwws.imo.annotations.CDIBean;
import com.dwws.imo.modelo.Empregador;

@CDIBean
public class EmpregadorBean extends IMOBean {
	
	@Inject
	private Empregador empregador;
	
	public void cadastrarEmpregador() {
		System.out.println(this.empregador.toString());
		this.imoService.cadastrarEmpregador(empregador);
		this.empregador = new Empregador();
	}
	

	public Empregador getEmpregador() {
		return empregador;
	}
	
	public void setEmpregador(Empregador empregador) {
		this.empregador = empregador;
	}
	
	
	

}
