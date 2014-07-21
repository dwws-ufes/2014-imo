package com.dwws.imo.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="trabalhador", schema="imo")
@NamedQueries({
	@NamedQuery(name="BUSCA_TRABALHADOR_POR_USUARIO_SENHA", 
			query="SELECT trab FROM Trabalhador trab WHERE trab.usuario = :usuario AND trab.senha = :senha")
})
public class Trabalhador {
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Basic
	@Column(name="nome", nullable=false, length=100)
	private String nome;
	
	@Basic
	@Column(name="sobrenome", nullable=false, length=100)
	private String sobrenome;
	
	@Basic
	@Column(name="data_nascimento", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Basic
	@Column(name="cpf", nullable=false, length=11)
	private String cpf;
	
	@OneToOne
	private Escolaridade escolaridade;
	
	@Basic
	@Column(name="email", nullable=false, length=50)
	private String email;
	
	@Basic
	@Column(name="telefone", nullable=false, length=9)
	private String telefone;
	
	@Basic
	@Column(name="endereco", nullable=false, length=300)
	private String endereco;
	
	@Basic
	@Column(name="cidade", nullable=false, length=50)
	private String cidade;
	
	@Basic
	@Column(name="estado", nullable=false)
	private Estado estado;
	
	@Basic
	@Column(name="usuario", nullable=false, length=8)
	private String usuario;
	
	@Basic
	@Column(name="senha", nullable=false, length=6)
	private String senha;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="trabalhador")
	private List<Candidatura> candidaturas;
	
	public Trabalhador() {
		this.escolaridade = new Escolaridade();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
	
	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

	@Override
	public String toString() {
		return "Trabalhador [id=" + id + ", nome=" + nome + ", sobrenome="
				+ sobrenome + ", dataNascimento=" + dataNascimento + ", cpf="
				+ cpf + ", escolaridade=" + escolaridade.toString() + ", endereço="
				+ endereco + ", cidade=" + cidade + ", estado=" + estado
				+ ", usuario=" + usuario + ", senha=" + senha + "]";
	}
	
}
