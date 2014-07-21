package com.dwws.imo.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CandidaturaId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer vagaId;
	
	private Integer trabalhadorId;

	public Integer getVagaId() {
		return vagaId;
	}

	public void setVagaId(Integer vagaId) {
		this.vagaId = vagaId;
	}

	public Integer getTrabalhadorId() {
		return trabalhadorId;
	}

	public void setTrabalhadorId(Integer trabalhadorId) {
		this.trabalhadorId = trabalhadorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((trabalhadorId == null) ? 0 : trabalhadorId.hashCode());
		result = prime * result + ((vagaId == null) ? 0 : vagaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidaturaId other = (CandidaturaId) obj;
		if (trabalhadorId == null) {
			if (other.trabalhadorId != null)
				return false;
		} else if (!trabalhadorId.equals(other.trabalhadorId))
			return false;
		if (vagaId == null) {
			if (other.vagaId != null)
				return false;
		} else if (!vagaId.equals(other.vagaId))
			return false;
		return true;
	}
	

}
