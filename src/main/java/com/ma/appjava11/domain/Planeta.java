package com.ma.appjava11.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ma.appjava11.repositorio.PlanetaRepository;



public class Planeta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	PlanetaRepository planetaRepository;
	
	private String nome;
	private String terreno;
	private String clima;
	
	public Planeta() {}
	
	public void addNomeClimaTerreno(String nome, String clima, String terrene) {
		this.setClima(clima);
		this.setNome(nome);
		this.setTerreno(terrene);	
	}
	
	public List<Planeta> listarPlanetas(){
		return planetaRepository.findAll();
	}
	
	public Optional<Planeta> buscarPorNome(String nome) {
		return Optional.of(planetaRepository.findByNome(nome).get(0));
	}
	public Optional<Planeta> buscarPorID(Integer id) {
		return planetaRepository.findById(id);
	}
	public void removerPlaneta(Integer id) {
		planetaRepository.deleteById(id);
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clima == null) ? 0 : clima.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((terreno == null) ? 0 : terreno.hashCode());
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
		Planeta other = (Planeta) obj;
		if (clima == null) {
			if (other.clima != null)
				return false;
		} else if (!clima.equals(other.clima))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (terreno == null) {
			if (other.terreno != null)
				return false;
		} else if (!terreno.equals(other.terreno))
			return false;
		return true;
	}
	
	
	
}
