package com.dwws.imo.modelo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cargo", schema="imo")
public class Cargo {
	
	@Id
	@GeneratedValue
	@Column(name = "codigo", unique = true, nullable = false)
	private Integer codigo;
	
	@Basic
	@Column(name="nome", nullable=false, length=30)
	private String nome;
	
	@Basic
	@Column(name="descricao", nullable=true, length=500)
	private String descricao;
	
	public Cargo() {
	}
	
	public Cargo(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public boolean ehNovo() {
		return this.codigo == null;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Cargo [codigo=" + codigo + ", nome=" + nome + ", descricao="
				+ descricao + "]";
	}

}
