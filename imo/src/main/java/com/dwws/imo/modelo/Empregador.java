package com.dwws.imo.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="empregador", schema="imo")
@NamedQueries({
	@NamedQuery(name="BUSCA_EMPREGADOR_POR_USUARIO_SENHA", 
			query="SELECT emp FROM Empregador emp WHERE emp.usuario = :usuario AND emp.senha = :senha")
})
public class Empregador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static String QUERY_BUSCA_POR_USUARIO_SENHA = "adsfadsf";
	
	@Id
	@GeneratedValue
	private long id;

	@Basic
	@Column(name="razao_social", nullable=false, length=100)
	private String razaoSocial;
	
	@Basic
	@Column(name="cnpj", nullable=false, length=14)
	private String cnpj;
	
	@Basic
	@Column(name="email", nullable=false, length=50)
	private String email;
	
	@Basic
	@Column(name="usuario", nullable=false, length=8)
	private String usuario;
	
	@Basic
	@Column(name="senha", nullable=false, length=6)
	private String senha;

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Empregador [razaoSocial=" + razaoSocial + ", cnpj=" + cnpj
				+ ", email=" + email + ", usuario=" + usuario + ", senha="
				+ senha + "]";
	}

}
