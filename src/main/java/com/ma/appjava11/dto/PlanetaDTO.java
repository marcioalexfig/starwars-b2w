package com.ma.appjava11.dto;

public class PlanetaDTO {
	private String nome;
	private String terreno;
	private String clima;
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
	public PlanetaDTO(String nome, String terreno, String clima) {
		super();
		this.nome = nome;
		this.terreno = terreno;
		this.clima = clima;
	}
	
	
}
