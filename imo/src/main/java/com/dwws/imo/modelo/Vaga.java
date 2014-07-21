package com.dwws.imo.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vaga", schema="imo")
public class Vaga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "codigo", unique = true, nullable = false)
	private Integer codigo;
	
	@ManyToOne(optional=false)
	private Cargo cargo;
	
	@ManyToOne(optional=false)
	private Escolaridade escolaridade;
	
	@Basic
	@Column(name="qtd_postos", nullable=false, length=3)
	private Integer numeroPostos;
	
	@Basic
	@Column(name="meses_experiencia", nullable=false, length=3)
	private Integer mesesExperiencia;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	private Empregador empregador;
	
	public Vaga() {
		this.cargo = new Cargo();
		this.escolaridade = new Escolaridade();
		this.empregador = new Empregador();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Integer getNumeroPostos() {
		return numeroPostos;
	}

	public void setNumeroPostos(Integer numeroPostos) {
		this.numeroPostos = numeroPostos;
	}

	public Integer getMesesExperiencia() {
		return mesesExperiencia;
	}

	public void setMesesExperiencia(Integer mesesExperiencia) {
		this.mesesExperiencia = mesesExperiencia;
	}
	
	public Empregador getEmpregador() {
		return empregador;
	}

	public void setEmpregador(Empregador empregador) {
		this.empregador = empregador;
	}
	
	public String getUsuarioEmpregador() {
		return this.empregador.getUsuario();
	}
	
	public String getSenhaEmpregador() {
		return this.empregador.getSenha();
	}
	
	public boolean pertenceNovoCargo() {
		return this.cargo.ehNovo();
	}

	@Override
	public String toString() {
		return "Vaga [codigo=" + codigo + ", cargo=" + cargo.toString()
				+ ", escolaridade=" + escolaridade.toString() + ", numeroPostos="
				+ numeroPostos + ", mesesExperiencia=" + mesesExperiencia
				+ ", empregador=" + empregador.getUsuario() + "]";
	}
	
	
}
