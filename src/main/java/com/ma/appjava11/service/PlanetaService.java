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
		return planetaRepository.findByNome(nome).get(0);
	}
	
	public Planeta buscarPorID(String id) {
		return planetaRepository.findById(id).get(0);
	}
	
	public void removerPlaneta(Integer id) {
		planetaRepository.deleteById(id);
	}
}
