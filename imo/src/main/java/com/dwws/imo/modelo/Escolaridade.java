package com.dwws.imo.modelo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="escolaridade", schema="imo")
public class Escolaridade {
	
	@Id
	@GeneratedValue
	@Column(name = "codigo", unique = true, nullable = false)
	private Integer codigo;
	
	@Basic
	@Column(name="descricao", nullable=false, length=20)
	private String descricao;
	
	public Escolaridade() {
	}
	
	public Escolaridade(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Escolaridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Escolaridade [codigo=" + codigo + ", descricao=" + descricao
				+ "]";
	}
	
}
