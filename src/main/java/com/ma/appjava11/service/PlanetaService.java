package com.ma.appjava11.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ma.appjava11.domain.Planeta;
import com.ma.appjava11.repositorio.PlanetaRepository;

@Service
public class PlanetaService {
	
	@Autowired
	PlanetaRepository planetaRepository;
	
	public List<Planeta> listarPlanetas(){
		return planetaRepository.findAll();
	}
	
	public Planeta buscarPorNome(String nome) {
		return planetaRepository.findByNome(nome);
	}
	
	public Planeta buscarPorID(String id) {
		return planetaRepository.findById(id);
	}
	
	public void removerPlaneta(String id) {
		planetaRepository.delete(planetaRepository.findById(id));
	}
	
	public Planeta gravarPlaneta(Planeta planeta) {
		return planetaRepository.insert(planeta);
	}
}
