package com.dwws.imo.modelo;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="candidatura", schema="imo")
@NamedQueries({
	@NamedQuery(name="BUSCA_CANDIDATURA_POR_IDTRABALHADOR_CODIGOVAGA", 
			query="SELECT cand FROM Candidatura cand WHERE cand.id.trabalhadorId = :idTrabalhador AND cand.id.vagaId = :codigoVaga")
})
public class Candidatura implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CandidaturaId id;
	
	@MapsId("vagaId")
	@JoinColumn(name="codigo_vaga")
	@ManyToOne
	private Vaga vaga;
	
	@MapsId("trabalhadorId")
	@JoinColumn(name="id_trabalhador")
	@ManyToOne
	private Trabalhador trabalhador;
	
	public Candidatura() {
		this.id = new CandidaturaId();
	}

	public CandidaturaId getId() {
		return id;
	}

	public void setId(CandidaturaId id) {
		this.id = id;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
		this.id.setVagaId(vaga.getCodigo());
	}

	public Trabalhador getTrabalhador() {
		return trabalhador;
	}

	public void setTrabalhador(Trabalhador trabalhador) {
		this.trabalhador = trabalhador;
		this.id.setTrabalhadorId(trabalhador.getId());
	}
	
}
